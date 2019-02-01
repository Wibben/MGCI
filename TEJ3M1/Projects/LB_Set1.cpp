/*
Bing Li
TEJ3M1
Feb 3, 2015
Set 1
*/

#include <iostream>
#include <windows.h>
#include <conio.h>

using namespace std;

//-------------------------------fileSizeCalc-------------------------------

void fileSizeCalc()
{
	int kb;
 
    // Getting Input
	cout << "How many kB is the file? ";
	cin >> kb;

	// Output
    cout << "The file can store " << kb*1024 << " characters.\n\nThis program has terminated.\n\n";
}

//-------------------------------rectAreaCalc-------------------------------

void rectAreaCalc()
{
	int l,w;

	// Getting input
    cout << "Please enter the length of the rectangle: ";
	cin >> l;
	cout << "Please enter the width of the rectangle: ";
	cin >> w;

	// Output
    cout << "The area of the rectangle is " << l*w << " units squared.\n\n";
}

//-------------------------------eggCartonCalc-------------------------------

void eggCartonCalc()
{
	int eggs;

	// Getting input
    cout << "How many eggs in this order? ";
	cin >> eggs;

	// Output
    cout << "This is " << eggs/12 << " dozens.\n\n";
	cout << "Nice doing business with you.\nHave a nice day!\n\nTake care of those chickens!\n\n";
}

//-------------------------------main-------------------------------

int main()
{
	char choice;

	// Regular menu stuff
    do {
		cout << "Please choose from the following programs: \n";
		cout << "1 - File size calculator\n";
		cout << "2 - Rectangle area calculator\n";
		cout << "3 - Egg carton calculator\n";
		cout << "x - Exit\n\n";
		cout << "Please press the corresponding key.";

		choice = getch();

		system("cls");

        if(choice=='1') fileSizeCalc();
        else if(choice=='2') rectAreaCalc();
        else if(choice=='3') eggCartonCalc();

		cout << "Press enter to continue...";
		getch();
		system("cls");
	} while(choice!='x');

    cout << "Program Terminated\n";

	return 0;
}
