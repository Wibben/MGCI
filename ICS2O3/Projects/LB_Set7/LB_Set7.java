/*
Bing Li
Oct 6, 2014
ICS2O3
Set 7 programs in a menu
*/
import java.awt.*;
import hsa.Console;

public class LB_Set7
{
    //*******************************CheckDivision*********************************
  
    public static void CheckDivision() { 
	double a,b,result;
	
	c.print("Enter a value for a\n");
	a = c.readDouble();
	c.print("Enter a value for b\n");
	b = c.readDouble();
	
	if(b!=0) {
	    result = a/b;
	    c.print("The result is ");
	    c.print(result,0,2);
	} else c.print("This calculation cannot be performed!");
    }
    
    //*******************************NumChars*********************************
  
    public static void NumChars() { 
	String wrd;
	int num,posn;
	
	c.print("Type any word and <Enter>\n");
	wrd = c.readString();
	num = wrd.length();
	
	if(num%2!=0) {
	    c.print(wrd + " has an odd number of letters.\n");
	    posn = num/2+1;
	    c.print("The middle letter is at position " + posn);
	} else c.print(wrd + " has an even number of letters.\n");
    }
	
    //*******************************MaxMinNum*********************************
  
    public static void MaxMinNum() { 
	int max=60,min=21,num;
	
	c.print("Enter any whole number: ");
	num = c.readInt();
	
	if(num>0) c.print("Your number is greater than zero\n");
	else c.print("Your number is negative or zero\n");
	
	if(num>max) c.print("Your number " + num + " is larger than " + max);
	if(num<min) c.print("Your number " + num + " is less than " + min);
    }
    
    //*******************************CheckDivision2*********************************
  
    public static void CheckDivision2() { 
	int basicSalary,sales;
	double salary;
	
	basicSalary = 3000;
	c.print("Enter the number of sales you made this month: ");
	sales = c.readInt();
	
	if(sales <= 10) salary = basicSalary;
	else {
	    salary = basicSalary + basicSalary*sales/100;
	    c.print("Well done!\n");
	}
	
	c.print("Salary: $");
	c.print(salary,5,2);
    }
    
    //*******************************EvenOddNum*********************************
  
    public static void EvenOddNum() { 
	int num,multiple;
	
	c.print("Please enter a whole number to be checked: ");
	num = c.readInt();
	c.print("Please enter the multiple used in the check: ");
	multiple = c.readInt();
	
	if(num%multiple==0) c.print(num + " is a multiple of " + multiple);
	else c.print(num + " is not a multiple of " + multiple);
    }
    
    //*******************************Discount*********************************
  
    public static void Discount() { 
	double total;
	
	c.print("Please enter the amount spent: ");
	total = c.readDouble();
	
	c.print("The final price is: $");
	if(total>100) c.print(total*0.9,0,2);
	else c.print(total,0,2);
    }
    
    //*******************************TestNumber*********************************
  
    public static void TestNumber() { 
	int num;
	
	c.print("Please enter a whole number: ");
	num = c.readInt();
	
	if(num>0) c.print(num + " is a positive number.\n");
	else if(num<0) c.print(num + " is a negative number.\n");
	else c.print(num + " is neither positive nor negative.\n");
	
	if(num%2==0) c.print(num + " is even.\n");
	else c.print(num + " is odd.\n");
	
	if(num%7==0) c.print(num + " is divisible by 7.\n");
	else c.print(num + " is not divisible by 7.\n");
	
	if(num*num>100) c.print(num + " squared is greater than 100.\n");
	else c.print(num + " squared is less than 100.'n");
    }
    
    //*******************************Driving*********************************
  
    public static void Driving() { 
	int age;
	
	c.print("Please enter your age: ");
	age = c.readInt();
	
	if(age>75) c.print("Sorry you are over the legal age limit for driving.\n");
	else if(age<18) c.print("Yoo young to drive.\n");
	else c.print("Age OK. Have you got a driver's licence?\n");
    }
    
    //*******************************MarSymbol*********************************
  
    public static void MarkSymbol() { 
	int mark;
	
	c.print("Please enter the mark: ");
	mark = c.readInt();
	
	c.print("The letter grade for a mark of " + mark + " is: ");
	if(mark>=80) c.print("A\n");
	else if(mark<80 && mark>=70) c.print("B\n");
	else if(mark<70 && mark>=60) c.print("C\n");
	else if(mark<60 && mark>=50) c.print("D\n");
	else c.print("E\n");
    }
    
    //*******************************CompareNumChars*********************************
  
    public static void CompareNumChars() { 
	String wrd1,wrd2;
	
	c.print("Please enter a word and <Enter>: ");
	wrd1 = c.readString();
	c.print("Please enter another word and <Enter>: ");
	wrd2 = c.readString();
	
	c.print("The words in order of shortest to longest is: ");
	if(wrd1.length()<=wrd2.length()) c.print(wrd1 + ", " + wrd2);
	else c.print(wrd2 + ", " + wrd1);
    }
    
    //*******************************SportingEvents*********************************
  
    public static void SportingEvents() { 
	int year;
	
	c.print("Please enter the year: ");
	year = c.readInt();
	
	if(year%4==0) c.print("The Olympics are held this year.\n");
	else if(year%4==2) c.print("The cricket world cup is held this year.\n");
	else if(year%4==3) c.print("The rugby world cup is held this year.\n");
	else c.print("There is no event this year.\n");
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
    
	int choice;
	
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 11:\n");
	    c.print("1 - CheckDivision\n");
	    c.print("2 - NumChars\n");
	    c.print("3 - MaxMinNum\n");
	    c.print("4 - CheckDivision2\n");
	    c.print("5 - EvenOddNum\n");
	    c.print("6 - Discount\n");
	    c.print("7 - TestNumber\n");
	    c.print("8 - Driving\n");
	    c.print("9 - MarkSymbol\n");
	    c.print("10 - CompareNumChars\n");
	    c.print("11 - SportingEvents\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    if(choice==1) CheckDivision();
	    else if(choice==2) NumChars();
	    else if(choice==3) MaxMinNum();
	    else if(choice==4) CheckDivision2();
	    else if(choice==5) EvenOddNum();
	    else if(choice==6) Discount();
	    else if(choice==7) TestNumber();
	    else if(choice==8) Driving();
	    else if(choice==9) MarkSymbol();
	    else if(choice==10) CompareNumChars();
	    else if(choice==11) SportingEvents();
	
	    c.setCursor(25,1);
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
    } // main method
} // LB_Set7 class
