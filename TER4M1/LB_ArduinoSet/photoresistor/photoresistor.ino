/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

int lightPin = 9; // LED is to be put on pin 9
int photoresistor = 0; // Photoresistor is to be put on ANALOG 0
const int lowBound=0,highBound=900; // The bounds of the temperature sensor to make changes more obvious

void setup() {
  // Setting up pins and serial output
  Serial.begin(9600);
  pinMode(lightPin,OUTPUT);
}

void loop() {
  int light = analogRead(photoresistor); // Get the reading from the temperature sensor
  Serial.println(light); // Output the temperature reading, can be used as a reference to adjust the bounds

  int intensity = map(light,lowBound,highBound,0,255); // Map the temperature reading to analog output signal

  analogWrite(lightPin,intensity); // Change the red intensity on the LED

  delay(500); // Put in a delay to better show changes
}
