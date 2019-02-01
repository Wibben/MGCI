/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

#include <Servo.h>

Servo myservo;  // Create servo object to control a servo

int potpin = 0;  // Analog pin used to connect the potentiometer
int val;    // Variable to read the value from the analog pin

void setup()
{
  myservo.attach(9);  // Attaches the servo on pin 9 to the servo object
}

void loop() 
{ 
  val = analogRead(potpin);            // Reads the value of the potentiometer (value between 0 and 1023) 
  val = map(val, 0, 1023, 0, 180);     // Scale it to use it with the servo (value between 0 and 180) 
  myservo.write(val);                  // Sets the servo position according to the scaled value 
  delay(15);                           // Waits for the servo to get there 
} 

