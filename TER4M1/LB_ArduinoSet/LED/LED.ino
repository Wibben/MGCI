/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

const int pins[] = {3,5,6,9,10,11}; // All the analog output pins
int multiplier=1; // For going up and down
int spd,bright;
  
void setup() {
  // Set all pins to output
  for(int i=0; i<6; i++) {
    pinMode(pins[i],OUTPUT);
  }
  
  // Set initial delay and brightness
  spd = 1000;
  bright = 25;
}

void loop() {
  // Output to the LEDs
  for(int i=0; i<6; i++) {
    analogWrite(pins[i],bright);
    delay(spd);
  }
  
  // Turn everything off after completion of an entire loop
  for(int i=0; i<6; i++) {
    analogWrite(pins[i],0);
  }
  
  // Increment brightness and speed
  spd = spd-multiplier*100;
  bright = bright+multiplier*25;
  
  // Reverse direction of increments if reached the upper/lower limit
  if(bright==25 || bright==250) multiplier*=-1;
}
