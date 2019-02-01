#include <SD.h>
#include <SPI.h>
#include "TMRpcm.h"

#define SD_ChipSelectPin 4

TMRpcm tmrpcm;
int digital[14] = {0,1,2,3,-1,4,5,6,7,-1,-1,8,9,10};
int analog[6] = {11,12,13,14,15,16};
char* files[17] = {"0.wav","1.wav","2.wav","3.wav","4.wav","5.wav","6.wav","7.wav","8.wav","9.wav","10.wav","11.wav","12.wav","13.wav","14.wav","15.wav","16.wav"};

int buttonInput()
{
  int choice=-1;
  
  for(int i=2; i<14; i++) {
    if(digital[i]!=-1) {
      if(digitalRead(i)==HIGH) {
        choice = digital[i];
      }
    }
  }
  for(int i=0; i<6; i++) {
    if(analogRead(i)>500) {
      choice = analog[i];
    }
  }

  return choice;
}

void setup() 
{
  for(int i=0; i<14; i++) {
    if(i!=10 && i!=4) pinMode(i,INPUT);
  }
  
  tmrpcm.speakerPin = 10;
  //Serial.begin(9600);
  if (!SD.begin(SD_ChipSelectPin)) {
    //Serial.println("SD fail");
    return;
  }

  tmrpcm.setVolume(5); // 0 to 7
}

void loop() 
{
  if(!tmrpcm.isPlaying()) {
    int choice = buttonInput();
  
    //Serial.println(choice);
  
    if(choice!=-1) tmrpcm.play(files[choice]);
  }

  delay(500);
}
