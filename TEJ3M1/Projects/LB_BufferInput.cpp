#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include <iostream>

using namespace std;

typedef short (_stdcall *inpfuncPtr)(short portaddr);
typedef void (_stdcall *oupfuncPtr)(short portaddr, short datum);

#define PPORT_BASE 0x378
#define PPORT_INP (PPORT_BASE+1)

// Prototypes for Test functions
void test_read8(void);
void test_write(void);
void test_write_datum(short datum);

inpfuncPtr inp32fp;
oupfuncPtr oup32fp;

short  Inp32 (short portaddr)
{
    return (inp32fp)(portaddr);
}

void  Out32 (short portaddr, short datum)
{
    (oup32fp)(portaddr,datum);
}

void input()
{
	int in;

	cout << "Press any key to stop." << endl << endl;

	while(!kbhit()) {
		in = Inp32(PPORT_INP);
		cout << in << " - ";
		Sleep(1000);
	}
}

void pin()
{
	int in;

	cout << "Press any key to stop." << endl << endl;

	while(!kbhit()) {
		in = Inp32(PPORT_INP);

		cout << in << " - ";
		if(in==120) cout << "No pins were pressed\n";
		else if(in==112) cout << "Pin 15 was pressed\n";
		else if(in==104) cout << "Pin 13 was pressed\n";
		else if(in==96) cout << "Pins 13 and 15 were pressed\n";
		else if(in==88) cout << "Pin 12 was pressed\n";
		else if(in==80) cout << "Pins 12 and 15 were pressed\n";
		else if(in==72) cout << "Pins 12 and 13 were pressed\n";
		else if(in==64) cout << "Pins 12, 13, and 15 were pressed\n";
		else if(in==248) cout << "Pin 11 was pressed\n";
		else if(in==240) cout << "Pins 11 and 15 were pressed\n";
		else if(in==232) cout << "Pins 11 and 13 were pressed\n";
		else if(in==224) cout << "Pins 11, 13 and 15 were pressed\n";
		else if(in==216) cout << "Pins 11 and 12 were pressed\n";
		else if(in==208) cout << "Pins 11, 12 and 15 were pressed\n";
		else if(in==200) cout << "Pins 11, 12 and 13 were pressed\n";
		else if(in==192) cout << "Pins 11, 12, 13, and 15 were pressed\n";
		else if(in==56) cout << "Pin 10 was pressed\n";
		else if(in==48) cout << "Pins 10 and 15 were pressed\n";
		else if(in==40) cout << "Pins 10 and 13 were pressed\n";
		else if(in==32) cout << "Pins 10, 13 and 15 were pressed\n";
		else if(in==24) cout << "Pins 10 and 12 were pressed\n";
		else if(in==16) cout << "Pins 10, 12 and 15 were pressed\n";
		else if(in==8) cout << "Pins 10, 12 and 13 were pressed\n";
		else if(in==0) cout << "Pins 10, 12, 13, and 15 were pressed\n";
		else if(in==184) cout << "Pins 10 and 11 were pressed\n";
		else if(in==176) cout << "Pins 10, 11 and 15 were pressed\n";
		else if(in==168) cout << "Pins 10, 11 and 13 were pressed\n";
		else if(in==160) cout << "Pins 10, 11, 13 and 15 were pressed\n";
		else if(in==152) cout << "Pins 10, 11 and 12 were pressed\n";
		else if(in==144) cout << "Pins 10, 11, 12 and 15 were pressed\n";
		else if(in==136) cout << "Pins 10, 11, 12 and 13 were pressed\n";
		else if(in==128) cout << "Pins 10, 11, 12, 13, and 15 were pressed\n";
		else cout << "Invalid pin combination\n";

		Sleep(1000);
	}
}

void menu()
{
	char choice;

	do {
		cout << "Welcome to the Buffer Chip Input Program\nPlease select what you want to do:\n\n";
		cout << "1 - Input Display" << endl;
		cout << "2 - PIN Display" << endl;
		cout << "0 - Quit Program" << endl;

		choice = getch();

		system("cls");

		if(choice==1) input();
		else if(choice==2) pin();

		cout << "\n\nPress any key to return to menu...";
		getch();
		system("cls");
	} while(choice!='0');

	cout << "Program Terminated. Press any key to exit.";
	getch();
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
        return -1;
	}
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

    menu();

	FreeLibrary(hLib);

	return 0;
}
