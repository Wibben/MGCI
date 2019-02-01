/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

int motorPin = 3; // Set the pin on which the motor is connected
int spd;
  
void setup() {
  // Set motor pin to output
  pinMode(motorPin,OUTPUT);
}

void loop() {
  int delayTime = 50; // Milliseconds between each speed step
  
  // Accelerates the motor
  for(int i = 125; i < 256; i++){ // Goes through each speed from 0 to 255
    analogWrite(motorPin, i);   // Sets the new speed
    delay(delayTime);           // Waits for delayTime milliseconds
  }
  
  //Decelerates the motor
  for(int i = 125; i >= 100; i--){ // Goes through each speed from 255 to 0
    analogWrite(motorPin, i);   // Sets the new speed
    delay(delayTime);           // Waits for delayTime milliseconds
  }
}
