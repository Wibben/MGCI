/* Arduino Activities
 * Bing Li
 * April 17, 2017
 * TER3M1
 */

int onBtn=0,offBtn=1;
int speakerPin = 9;
bool play = 0;

// Setting up the song
// Intro
//int songLength = 34; // the number of notes
//char notes[] = "deaefAgfdeaaaabdbdeaefAgfdeadd dd "; // a space represents a rest
//double beats[] = { 1.5, 1.5, 1, 1.5, 1.5, 0.25, 0.25, 0.25, 2, 1.5, 2.5, 1, 0.25, 0.25, 0.25, 0.25, 0.5, 1.5, 1.5, 1, 1.5, 1.5, 0.25, 0.25, 0.25, 2, 1.5, 3, 0.25, 0.25, 0.25, 0.25, 0.25, 1.25 };
// Chorus
int songLength = 61;
char notes[] = "abdbffeabcaeedcbabdbdecba aed abdbffeabcaAcdcbabdbdecba aedd ";
double beats[] = { 0.25, 0.25, 0.25, 0.25, 0.75, 0.75, 1.5, 0.25, 0.25, 0.25, 0.25, 0.75, 0.75, 0.75, 0.25, 0.5, 0.25, 0.25, 0.25, 0.25, 1, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 1, 1, 1, 0.25, 0.25, 0.25, 0.25, 0.75, 0.75, 1.5, 0.25, 0.25, 0.25, 0.25, 1, 0.5, 0.75, 0.25, 0.5, 0.25, 0.25, 0.25, 0.25, 1, 0.5, 0.75, 0.25, 0.5, 0.5, 0.5, 0.5, 0.5, 2, 1 };

int tempo = 400;

// Used to play a single note
void playTone(int t, int duration) {
  for (long i = 0; i < duration * 1000L; i += t * 2) {
    digitalWrite(speakerPin, HIGH);
    delayMicroseconds(t);
    digitalWrite(speakerPin, LOW);
    delayMicroseconds(t);
  }
}

// Used to convert note to corresponding frequency
void playNote(char note, int duration) {
  char names[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'B', 'A', 'C' };
  //int tones[] = { 1915, 1700, 1519, 1432, 1275, 1136, 1014, 956};
  int tones[] = { 4186, 3951, 3520, 3136, 2793, 2637, 2349, 2093, 1975, 1760};
  
  // play the tone corresponding to the note name
  for (int i = 0; i < 10; i++) {
    if (names[i] == note) {
      playTone(tones[i], duration);
    }
  }
}

void setup() {
  // Setup speaker pin for output
  pinMode(speakerPin,OUTPUT);
}

void loop() {
  // Get readings from the buttons
  int on = analogRead(onBtn);
  if(on!=0) play = 1; // If you takt this out for some reason it doesn't work
  on = 0;
  if(analogRead(offBtn)!=0) play = 0;

  // Plays the song
  for (int i=0; i<songLength && play; i++) {
    if (notes[i] == ' ') {
      delay(beats[i] * tempo); // Rest
    } else {
      playNote(notes[i], beats[i] * tempo);
    }
    
    // Pause between notes
    delay(50); 

    // Read to see if the off button is being pressed
    if(analogRead(offBtn)!=0) play = 0;
  }
}
