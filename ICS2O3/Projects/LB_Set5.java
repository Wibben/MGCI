/*
Bing Li
Sept 23, 2014
ICS2O3
Set 5 in a menu
*/
import java.awt.*;
import hsa.Console;
import java.util.*;

public class LB_Set5
{
    //*******************************InputReals*********************************
  
    public static void InputReals() { 
	double num1,num2;
	
	c.print("Type in a real number and <Enter>:\n");
	num1 = c.readDouble();
	c.print("Type in a real number and <Enter>:\n");
	num2 = c.readDouble();
	c.print("The first real number entered was ");
	c.print(num1,0,3);
	c.print("\nThe first second number entered was ");
	c.print(num2,0,3);
	c.print("\nThe sum of the 2 numbers is ");
	c.print((num1+num2),0,1);
	c.print("\nThe difference of " + num1 + " subtracted by " + num2 + " is ");
	c.print((num1-num2),0,1);
	c.print("\nThe product of the 2 numbers is ");
	c.print((num1*num2),0,1);
    }
    
    //*******************************Division*********************************
  
    public static void Division() { 
	int num1,num2;
	
	c.print("INTEGER DIVISION\n\n");
	c.print("Type in an integer and <Enter>:\n");
	num1 = c.readInt();
	c.print("Type in an integer and <Enter>:\n");
	num2 = c.readInt();
	c.print("The quotient of " + num1 + " divided by " + num2 + " is " + (num1/num2));
    }
	
    //*******************************Remainder*********************************
  
    public static void Remainder() { 
	double num1,num2;
	
	c.print("FINDING THE REMAINDER\n\n");
	c.print("Type in a real number and <Enter>:\n");
	num1 = c.readDouble();
	c.print("Type in a real number and <Enter>:\n");
	num2 = c.readDouble();
	c.print("The remainder of " + num1 + " divided by " + num2 + " is ");
	c.print((num1%num2),0,3);
    }
    
    //*******************************Division2*********************************
  
    public static void Division2() { 
	double num1,num2;
	
	c.print("REAL NUMBER DIVISION\n\n");
	c.print("Type in a real number and <Enter>:\n");
	num1 = c.readDouble();
	c.print("Type in a real number and <Enter>:\n");
	num2 = c.readDouble();
	c.print("The quotient of " + num1 + " divided by " + num2 + " is ");
	c.print((num1/num2),0,3);
    }
    
    //*******************************Incrementing*********************************
  
    public static void Incrementing() { 
	int num1,num2;
	
	num1 = 7;
	num2 = 10;
	c.print("The first integer is " + num1++);
	c.print("\nThe second integer is " + num2++);
	c.print("\nAfter incremeting:\n");
	c.print("The first integer is now " + num1);
	c.print("\nThe second integer is now " + num2);
    }
    
    //*******************************Exercise5D*********************************
  
    public static void Exercise5D() throws InterruptedException { 
	String x,y,z;
	
	c.print("Part A: ThreeWords\n\n");
	c.print("Please enter 3 words and <Enter>:\n");
	x = c.readString();
	y = c.readString();
	z = c.readString();
	c.clear();
	c.print("Your first word was: " + x);
	c.print("\nIt has " + x.length() + " digits.\n");
	c.print("Your second word was: " + y);
	c.print("\nIt has " + y.length() + " digits.\n");
	c.print("Your third word was: " + z);
	c.print("\nIt has " + z.length() + " digits.\n");
	c.print("They have a total of " + (x.length()+y.length()+z.length()) + " digits.\n\n"); 
	c.print("Please wait:  ...");
	for(int i=5; i>0; i--) {
	    c.setCursor(9,14);
	    c.print(i);
	    Thread.sleep(2000);
	}
	
	c.clear();
	
	String number;
	int num,tempNum,digits = 0;
	
	c.print("Part B: NumberOfDigits\nPart B-1: using a String\n\n");
	c.print("Please enter a number and <Enter>:\n");
	number = c.readString();
	c.print("Your number was: " + number);
	c.print("\nIt has " + number.length() + " digits.\n");
	c.print("---------------------------------------------------\n");
	c.print("Part B-2: using an int\n\n");
	c.print("Please enter a number and <Enter>:\n");
	num = c.readInt();
	tempNum = num;
	while(tempNum!=0) {
	    tempNum /= 10;
	    digits++;
	}
	c.print("Your number was: " + num);
	c.print("\nIt has " + digits + " digits.\n\n");
    }
    
    //*******************************Marks*********************************
  
    public static void Marks() { 
	String pupils[] = new String[3];
	int marks[] = new int[3];
	
	for(int i=0; i<3; i++) {
	    c.print("Please enter the pupil's name and <Enter>, then their mark and <Enter>:\n");
	    pupils[i] = c.readLine();
	    marks[i] = c.readInt();
	}
	
	c.clear();
	c.print("123456789012345678901234567890\n");
	for(int i=0; i<3; i++) {
	    c.print(pupils[i]);
	    c.print(marks[i],30 - pupils[i].length());
	    c.print("\n");
	}
    }
    
    //*******************************ShoppingList*********************************
  
    public static void ShoppingList() { 
	String items[] = new String[100];
	double price[] = new double[100];
	double sum = 0;
	int itemCount;
	
	c.print("Please enter the number of items: ");
	itemCount = c.readInt();
	for(int i=0; i<itemCount; i++) {
	    c.print("Please enter the item and <Enter>: ");
	    items[i] = c.readLine();
	    c.print("Please enter the price and <Enter>: ");
	    price[i] = c.readDouble();
	    sum += price[i];
	}
	
	c.clear();
	c.print("123456789012345678901234567890\n");
	c.print("     SHOPPING LIST\n\n");
	for(int i=0; i<itemCount; i++) {
	    c.print(items[i]);
	    c.print(price[i],30 - items[i].length(),2);
	    c.print("\n");
	}
	c.print("\t\t    ----------\n");
	c.print("TOTAL");
	c.print(sum,25,2);
    }
    
    //*******************************CentreDisplay*********************************
  
    public static void CentreDisplay() { 
	String sent;
	
	c.print("Please enter a sentence:\n");
	sent = c.readLine();
	c.clear();
	for(int i=0; i<8; i++)
	    c.print("1234567890");
	c.print("\n");
	c.print(" ", 40 - sent.length()/2);
	c.print(sent);
    }
    
    //*******************************Flyer*********************************
  
    public static void Flyer() { 
	for(int i=0; i<22; i++)
	    c.print("/\\");
	c.print("\n ",19);
	c.print("ETHNIK\n");
	c.print(" ",19);
	c.print("AFRIKA\n");
	c.print(" ",9);
	c.print("Ethnic gear at low prices\n");
	for(int i=0; i<22; i++)
	    c.print("/\\");
	c.print("\nWEDNESDAY",34);
	c.print(" Fleamarket\n");
	c.print("SATURDAY",34);
	c.print("Bruma Lake\n");
	c.print("SUNDAY",34);
	c.print("  Stand 43\n");
	for(int i=0; i<22; i++)
	    c.print("/\\");
    }
    
    //*******************************ComputerMenu*********************************
  
    public static void ComputerMenu() { 
	String items[] = new String[8];
	
	items[1] = "Adapter - start up your meal with this innovative dish\n";
	items[2] = "Mouse - nacho chips with a surprise dip\n";
	items[3] = "Numpad - moves your tastebuds in all directions\n";
	items[4] = "RAM - rotissarie quarter chicken\n";
	items[5] = "Shift - top things up with this dessert\n";
	items[6] = "Hard drive - frozen yogurt with chocolate sauce and sprinkles\n";
	items[7] = "LED - sprite-cola blend of happiness\n";
	c.print(" ",32);
	c.print("MOTHERBOARD MENU\n\n");
	c.print(" ",36);
	c.print("STARTERS\n");
	c.print(" ",40-items[1].length()/2);
	c.print(items[1] + "\n");
	c.print(" ",40-items[2].length()/2);
	c.print(items[2] + "\n\n");
	c.print(" ",30);
	c.print("MAIN COURSE\n");
	c.print(" ",40-items[3].length()/2);
	c.print(items[3] + "\n");
	c.print(" ",40-items[4].length()/2);
	c.print(items[4] + "\n\n");
	c.print(" ",37);
	c.print("DESSERT\n");
	c.print(" ",40-items[5].length()/2);
	c.print(items[5] + "\n");
	c.print(" ",40-items[6].length()/2);
	c.print(items[6] + "\n\n");
	c.print(" ",37);
	c.print("DRINKS\n");
	c.print(" ",40-items[7].length()/2);
	c.print(items[7] + "\n");
    }
    
    //*******************************VarValues*********************************
  
    public static void VarValues() { 
	int num1,num2,num3,sum;
	
	num1 = 7;
	num2 = 15;
	
	c.print("Type in any integer and <Enter>: ");
	num3 = c.readInt();
	
	c.print("The first integer is " + num1);
	c.print("\nThe second integer is " + num2);
	c.print("\nThe third integer is " + num3);
	sum = num1+num2+num3;
	c.print("\nThe sum of the 3 integers is " + sum);
    }
    
    //*******************************Exercise5G*********************************
  
    public static void Exercise5G() throws InterruptedException { 
	int eggs,year,a,b;
    
	c.print("Part A: Eggs\n\n");
	c.print("Please enter the number of eggs in the box and <Enter>: ");
	eggs = c.readInt();
	c.print("You have " + eggs/12 + " dozen eggs in the box.\n");
	c.print("Please wait:  ...");
	for(int i=5; i>0; i--) {
	    c.setCursor(5,14);
	    c.print(i);
	    Thread.sleep(1500);
	}
	
	c.clear();
	
	c.print("Part B: Date100\n\n");
	c.print("Please enter a year in the form of YYYY: ");
	year = c.readInt();
	b = year%100;
	a = (year-b)/100;
	a++;
	c.print("In 100 years, the year will be " + a + b);
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
    
	int choice;
	
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 13:\n");
	    c.print("1 - InputReals\n");
	    c.print("2 - Division\n");
	    c.print("3 - Remainder\n");
	    c.print("4 - Division2\n");
	    c.print("5 - Incrementing\n");
	    c.print("6 - Exercise5D\n");
	    c.print("7 - Marks\n");
	    c.print("8 - ShoppingList\n");
	    c.print("9 - CentreDisplay\n");
	    c.print("10 - Flyer\n");
	    c.print("11 - ComputerMenu\n");
	    c.print("12 - VarValues\n");
	    c.print("13 - Exercise5G\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    if(choice==1) InputReals();
	    else if(choice==2) Division();
	    else if(choice==3) Remainder();
	    else if(choice==4) Division2();
	    else if(choice==5) Incrementing();
	    else if(choice==6) Exercise5D();
	    else if(choice==7) Marks();
	    else if(choice==8) ShoppingList();
	    else if(choice==9) CentreDisplay();
	    else if(choice==10) Flyer();
	    else if(choice==11) ComputerMenu();
	    else if(choice==12) VarValues();
	    else if(choice==13) Exercise5G();
	
	    c.setCursor(25,1);
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
    } // main method
} // LB_Set5 class
