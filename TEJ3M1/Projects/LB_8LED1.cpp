#include <iostream>
#include <windows.h>
#include <conio.h>

using namespace std;

void cnt()
{
	int i = 1;
	while(!kbhit()) {
		if(i==256) break;
		else cout << i++ << endl;
		Sleep(100);
	} getch();

	cout << "\nLet's go in reverse now!\nPress enter to continue...\n\n";
	getch();

    i=255;
	while(!kbhit()) {
		if(i==0) break;
		else cout << i-- << endl;
		Sleep(100);
	} getch();

	cout << "\nThat's it!\n";
}

void powers()
{
	while(!kbhit()) {
		for(int i=1; i<=128; i*=2) {
			cout << i << " ";
			Sleep(100);
        }
		cout << endl;
	} getch();

	cout << "\nLet's go in reverse now!\nPress enter to continue...\n\n";
	getch();

	while(!kbhit()) {
		for(int i=128; i>=1; i/=2) {
			cout << i << " ";
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
    } cout << endl << endl;
}

int main()
{
	char choice;

	// Regular menu stuff
    do {
		cout << "Please choose from the following programs: \n";
		cout << "1 - Count to 255\n";
		cout << "2 - Powers of 2\n";
		cout << "3 - Binary conversion\n";
		cout << "x - Exit\n\n";
		cout << "Please press the corresponding key.";

		choice = getch();

		system("cls");

        if(choice=='1') cnt();
        else if(choice=='2') powers();
        else if(choice=='3') binary();
		cout << "Press enter to continue...";
		getch();
		system("cls");
	} while(choice!='x');

    cout << "Program Terminated\n";

	return 0;
}
