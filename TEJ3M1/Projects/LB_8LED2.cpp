/*Bing Li TEJ3M
8 LED Program
Controls 8 Leds with various programs.
Modified Version of Program by Sean Purcell*/

#include <iostream>
#include <conio.h>
#include <windows.h>
#include <time.h>

using namespace std;
int numInput;
//int numInput(string str)
//{
//       cout << str;
//       int in;
//       
//       cin >> in;
//       return in;    
//}

/* Definitions in the build of inpout32.dll are:            */
/*   short _stdcall Inp32(short PortAddress);               */
/*   void _stdcall Out32(short PortAddress, short data);    */

/* prototype (function typedef) for DLL function Inp32: */

     typedef short (_stdcall *inpfuncPtr)(short portaddr);
     typedef void (_stdcall *oupfuncPtr)(short portaddr, short datum);

#define PPORT_BASE 0x378
// Prototypes for Test functions
void test_read8(void);
void test_write(void);
void test_write_datum(short datum);

/* After successful initialization, these 2 variables
   will contain function pointers.
 */
     inpfuncPtr inp32fp;
     oupfuncPtr oup32fp;
/* Wrapper functions for the function pointers
    - call these functions to perform I/O.
 */
     short  Inp32 (short portaddr)
     {
          return (inp32fp)(portaddr);
     }

     void  Out32 (short portaddr, short datum)
     {
          (oup32fp)(portaddr,datum);
     }     

void cnt()
{
	int i = 1;
	while(!kbhit()) {
		if(i==256) break;
		else cout << i++ << endl;
		Out32(PPORT_BASE,i);
		Sleep(100);
	} getch();

	cout << "\nLet's go in reverse now!\nPress enter to continue...\n\n";
	getch();

    i=255;
	while(!kbhit()) {
		if(i==0) break;
		else cout << i-- << endl;
		Out32(PPORT_BASE,i);
		Sleep(100);
	} getch();

	cout << "\nThat's it!\n";
}

void powers()
{
	while(!kbhit()) {
		for(int i=1; i<=128; i*=2) {
			cout << i << " ";
			Out32(PPORT_BASE,i);
			Sleep(100);
        }
		cout << endl;
	} getch();

	cout << "\nLet's go in reverse now!\nPress enter to continue...\n\n";
	getch();

	while(!kbhit()) {
		for(int i=128; i>=1; i/=2) {
			cout << i << " ";
			Out32(PPORT_BASE,i);
			Sleep(100);
		}
		cout << endl;
	} getch();

	cout << "\nLet's go up and down now!\nPress enter to continue...\n\n";
	getch();

	while(!kbhit()) {
		for(struct {int i;bool up;} s = {1,1}; s.i>=1 || s.up==1; s.i=(s.up==1 ? s.i*2:s.i/2)) {
			if(s.i==128) s.up = 0;
			cout << s.i << " ";
			Out32(PPORT_BASE,s.i);
			Sleep(100);
		} cout << endl;
	} getch();

	cout << "\nThat's it!\n";
}

void binary()
{
    int dec;

    cout << "Please enter a number to be converted: ";
    cin >> dec;

    cout << "\nThe number in binary is: ";
    for(int i=0,j=128; i<=7; i++,j/=2) {
        if(j<=dec) {
            dec -= j;
            cout << 1;
        } else cout << 0;
        Out32(PPORT_BASE,i);
    } cout << endl << endl;
}

void rnd()
{
    int generated,mi,ma;
    
    srand(time(NULL));
    
    cout << "Please enter the min and max of the number you want: ";
    cin >> mi >> ma;
    if(mi>ma) {
        int temp = mi;
        mi = ma;
        ma = temp;
    }
    
    cout << "The number generated was: " << rand()%(ma-mi+1)+mi << endl;
}

int main()
{
     HINSTANCE hLib;
     short x;
     int i;
     /* Load the library */
     hLib = LoadLibrary("inpout32.dll");
     if (hLib == NULL) {
          fprintf(stderr,"LoadLibrary Failed.\n");
          getch();
          return -1;                      }
    /* get the address of the function */
     inp32fp = (inpfuncPtr) GetProcAddress(hLib, "Inp32");
     if (inp32fp == NULL) {
          fprintf(stderr,"GetProcAddress for Inp32 Failed.\n");
          getch();
          return -1;
     }
     oup32fp = (oupfuncPtr) GetProcAddress(hLib, "Out32");
     if (oup32fp == NULL) {
          fprintf(stderr,"GetProcAddress for Oup32 Failed.\n");
          getch();
          return -1;
     }
    
	char choice;

	// Regular menu stuff
    do {
		cout << "Please choose from the following programs: \n";
		cout << "1 - Count to 255\n";
		cout << "2 - Powers of 2\n";
		cout << "3 - Binary conversion\n";
		cout << "4 - Random Number Generator\n";
		cout << "x - Exit\n\n";
		cout << "Please press the corresponding key.";

		choice = getch();

		system("cls");

        if(choice=='1') cnt();
        else if(choice=='2') powers();
        else if(choice=='3') binary();
        else if(choice=='4') rnd();
		cout << "Press enter to continue...";
		getch();
		system("cls");
	} while(choice!='x');

    cout << "Program Terminated\n";
    Out32(PPORT_BASE,0);//This resets the 8 leds to off or zero
    FreeLibrary(hLib);
    
	return 0;
}
