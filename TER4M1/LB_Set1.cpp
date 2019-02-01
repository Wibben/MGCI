/* Problem Set 1
 * Bing Li
 * February 27, 2017
 * TER4M1
*/
#include <iostream>
#include <string>
#include <cstdio>
#include <conio.h>
#include <cstdlib>

using namespace std;

void arithmetic()
{
    // Variable declaration
    int op1,op2;

    // Get user input
    cout << "Operand #1: ";
    cin >> op1;
    cout << "Operand #2: ";
    cin >> op2;

    // Compute sum, difference, product, and quotient
    cout << endl << op1 << " + " << op2 << " = " << op1+op2 << endl;
    cout << op1 << " - " << op2 << " = " << op1-op2 << endl;
    cout << op1 << " x " << op2 << " = " << op1*op2 << endl;
    cout << op1 << " / " << op2 << " = ";
    if(op2==0) cout << "undefined" << endl; // Quotient can be undefined
    else printf("%.2f\n",1.0*op1/op2); // Quotient can be decimals so formatting needed
}

void equation()
{
    // Variable declaration
    int a,b;

    // Get user input
    cout << "Enter values for Ax + B = 0\nA: ";
    cin >> a;
    cout << "B: ";
    cin >> b;

    // Solve Ax+B=0 step by step
    cout << a << "x + " << b << " = 0" << endl;
    cout << a << "x = " << -b << endl;
    cout << "x = " << -b << "/" << a << endl;
    cout << "x = ";
    if(a==0) cout << "undefined" << endl; // No solution
    else printf("%.3f\n",1.0*-b/a); // Decimal answer formatted
}

void paycheque()
{
    // Variable declaration
    double wage,hours;

    // Get use input
    cout << "Enter Hourly Wage ($): ";
    cin >> wage;
    cout << "Enter Hours Worked: ";
    cin >> hours;

    // Display regular, overtime, and total pay, overtime applied when hours worked >40
    // Overtime pay is multiplied by 1.5 and everything must be formatted to 2 decimals
    printf("Regular Pay: %.2f\nOvertime Pay: %.2f\nTotal Pay: %.2f\n",min(40.0,hours)*wage,max(0.0,hours-40)*wage*1.5,min(40.0,hours)*wage+max(0.0,hours-40)*wage*1.5);
}

void pizza()
{
    // Variable declaration
    int number;
    char pizzaSize;

    // Get user input
    cout << "Enter the number of pizzas: ";
    cin >> number;
    cout << "Enter the size of the pizzas (S,M,L): ";
    cin >> pizzaSize;

    // Output number of pizzas, price per pizza, and total cost
    cout << "You have ordered " << number;
    if(toupper(pizzaSize)=='S') cout << " SMALL pizzas at $6.99 per pizza." << endl << "Your total cost is: $" << 6.99*number << endl;
    else if(toupper(pizzaSize)=='M') cout << " MEDIUM pizzas at $8.99 per pizza." << endl << "Your total cost is: $" << 8.99*number << endl;
    else if(toupper(pizzaSize)=='L') cout << " LARGE pizzas at $10.99 per pizza." << endl << "Your total cost is: $" << 10.99*number << endl;
}

void sortFn()
{
    // Variable declaration
    int a,b,c;

    // Get user input
    cout << "Please enter the three integers: ";
    cin >> a >> b >> c;

    // Sort from high to low and output
    cout << "From high to low:" << endl;
    if(a>b && b>c) cout << a << endl << b << endl << c << endl;
    else if(a>c && c>b) cout << a << endl << c << endl << b << endl;
    else if(b>a && a>c) cout << b << endl << a << endl << c << endl;
    else if(b>c && c>a) cout << b << endl << c << endl << a << endl;
    else if(c>a && a>b) cout << c << endl << a << endl << b << endl;
    else if(c>b && b>a) cout << c << endl << b << endl << a << endl;
}

int main()
{
	//menu to access programs
	char choice;
	do {
		system("cls"); //clear screen
		cout<<"Problem Set #5\n";
		cout<<"--------------\n";
		cout<<"1. Arithmetic\n";
		cout<<"2. Equation\n";
		cout<<"3. Paycheque\n";
		cout<<"4. Pizza\n";
		cout<<"5. Sort\n";
		cout<<"0. Quit\n";

		cin>> choice;

		system("cls"); //clear screen

		if(choice == '1') arithmetic();
        else if(choice == '2') equation();
        else if(choice == '3') paycheque();
        else if(choice == '4') pizza();
        else if(choice == '5') sortFn();

        cout << "\nPress any key to continue...";
        cin.ignore();
        cin.ignore();
	} while (choice != '0'); //exit when 0

	system("cls");
	cout<<"End";

	return 0;
}
