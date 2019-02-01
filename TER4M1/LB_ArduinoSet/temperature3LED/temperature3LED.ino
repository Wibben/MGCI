/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

int redPin = 9; // The transistor (and thus red pin) is put on pin 9
const int lowBound=154,highBound=170; // The bounds of the temperature sensor to make changes more obvious

void setup() {
  // Setting up pinmodes and serial output
  Serial.begin(9600);
  pinMode(redPin,OUTPUT);
}

void loop() {
  int temp = analogRead(0); // Get the reading from the temperature sensor
  Serial.println(temp); // Output the temperature reading, can be used as a reference to adjust the bounds

  int redIntensity = map(temp,lowBound,highBound,0,255); // Map the temperature reading to analog output signal

  analogWrite(redPin,redIntensity); // Change the red intensity on the 3LED

  delay(500); // Put in a delay to better show changes
}
