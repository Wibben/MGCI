/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

#include <Servo.h> 
 
Servo myservo;  // Create servo objects to control a servo 
Servo continuous;
int pos = 0;    // Variable to store the servo position 
 
void setup() 
{ 
  myservo.attach(9);  // Attaches the servo on pin 9 to the servo object 
  continuous.attach(3);
} 
 
void loop() 
{ 
  // Reglar servo
  for(pos = 0; pos <= 180; pos += 1) // Goes from 0 degrees to 180 degrees 
  {                                  // In steps of 1 degree 
    myservo.write(pos);              // Tell servo to go to position in variable 'pos' 
    delay(15);                       // Waits 15ms for the servo to reach the position 
  } 
  for(pos = 180; pos>=0; pos-=1)     // Goes from 180 degrees to 0 degrees 
  {                                
    myservo.write(pos);              // Tell servo to go to position in variable 'pos' 
    delay(15);                       // Waits 15ms for the servo to reach the position 
  }

  // Continous servo
  for(int spd=0; spd<=180; spd+=90) { // Backwards, stops,then forwards
    continuous.write(spd);
    delay(2000);
  }
  continuous.write(90); // Stop servo after demo
} 

