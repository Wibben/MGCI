/*
Bing Li Sept 22, 2014 
ICS3O3
Set 4 problems in a menu
*/

import java.awt.*;
import hsa.Console;

public class LB_Set4
{
    //*******************************MakeLabels*********************************
  
    public static void MakeLabels() { 
	String name,subject;
	
	c.print("Type in the name and <Enter>:\n");
	name = c.readLine();
	c.print("Type in the subject and <Enter>:\n");
	subject = c.readLine();
	c.print("\n******************************\n");
	c.print("Name: " + name);
	c.print("\nSubject: " + subject);
	c.print("\n******************************\n");
    }
    
    //*******************************InOutWord*********************************
  
    public static void InOutWord() { 
	String word;
	
	c.print("Type in any word, then <Enter>\n");
	word = c.readString();
	c.print("\nThe word that was input is " + word);
    }
    
    //*******************************InputRect*********************************
  
    public static void InputRect() { 
	int length,width;
	
	c.print("RECTANGLE PROGRAM\n");
	c.print("Type in the length of the rectangle and <Enter>: ");
	length = c.readInt();
	c.print("Type in the width of the rectangle and <Enter>: ");
	width = c.readInt();
	c.setColor(Color.green);
	c.fillRect(100,100,length,width);
    }
    
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
    }
    
    //*******************************PersonalDetails*********************************
  
    public static void PersonalDetails() { 
	String name,telephone,classroom;
	int birthyear,height;
	
	c.print("Please enter your name:\n");
	name = c.readLine();
	c.print("Please enter your telephone number:\n");
	telephone = c.readString();
	c.print("Please enter your year of birth:\n");
	birthyear = c.readInt();
	c.print("Please enter your height in meters:\n");
	height = c.readInt();
	c.print("Please enter your class this year:\n");
	classroom = c.readString();
	c.clear();
	c.print("PERSONAL DETAILS\n\n");
	c.print("GRADE " + classroom);
	c.print("\nNAME: " + name);
	c.print("\nYEAR OF BIRTH: " + birthyear);
	c.print("\nHEIGHT(m): ");
	c.print(height,0,1);
	c.print("\nTELEPHONE NUMBER: " + telephone);
	
    }
    
    //*******************************ColoredCircles*********************************
  
    public static void ColoredCircles() { 
	int dRed,dBlue;
	
	c.print("This program will draw one red and one blue circle\n");
	c.print("Please enter the diameter for the red circle (100-300): ");
	dRed = c.readInt();
	c.print("Please enter the diameter for the blue circle (50-150): ");
	dBlue = c.readInt();
	c.setColor(Color.red);
	c.fillOval(0,100,dRed,dRed);
	c.setColor(Color.blue);
	c.fillOval(390,100,dBlue,dBlue);
    }
    
    //*******************************Prices*********************************
  
    public static void Prices() { 
	String[] product = new String[3];
	double[] price = new double[3];
	
	for(int i=0; i<3; i++) {
	    c.print("Please enter a product and on the next line, its price:\n");
	    product[i] = c.readLine();
	    price[i] = c.readDouble();
	}
	c.print("\nSHOPPING LIST");
	for(int i=0; i<3; i++) {
	    c.print("\n" + product[i],20);
	    c.print(price[i],7,2);
	}
    }
    
    //*******************************InputChars*********************************
  
    public static void InputChars() { 
	char ch1,ch2,ch3;
	c.print("Type in any three characters on the keyboard\n");
	//c.print("Press <Enter> after each.\n");
	ch1 = c.getChar();
	ch2 = c.getChar();
	ch3 = c.getChar();
	c.print("Together these 3 letters spell: " + ch1 + ch2 + ch3);
    }
    
    //*******************************InputChars2*********************************
  
    public static void InputChars2() { 
	String ch1,ch2,ch3;
	c.print("Type in any three characters on the keyboard\n");
	c.print("Press <Enter> after each.\n");
	ch1 = c.readString();
	ch2 = c.readString();
	ch3 = c.readString();
	c.print("Together these 3 letters spell: " + ch1 + ch2 + ch3);
    }
    
    //*******************************InputChars3*********************************
  
    public static void InputChars3() { 
	char ch1,ch2,ch3,garb;
	int x,y,z;
	c.print("Type in any three characters on the keyboard\n");
	c.print("Press <Enter> after each.\n");
	ch1 = c.readChar();
	garb = c.readChar();
	ch2 = c.readChar();
	garb = c.readChar();
	ch3 = c.readChar();
	garb = c.readChar();
	c.print("Together these 3 letters spell: " + ch1 + ch2 + ch3);
	x = ch1;
	y = ch2;
	z = ch3;
	c.print("\nFor letter " + ch1 + " is " + x);
	c.print("\nFor letter " + ch2 + " is " + y);
	c.print("\nFor letter " + ch3 + " is " + z);
	c.print("\nLetters add up to " + (x+y+z));
    }
    
    //*******************************Overwrite*********************************
  
    public static void Overwrite() { 
	String name;
	
	c.print("Type in a name and <Enter>\n");
	name = c.readLine();
	c.print("The name is " + name);
	c.print("\nType in another name and <Enter>\n");
	name = c.readLine();
	c.print("The name is " + name);
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
    
	int choice;
	
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 10:\n");
	    c.print("1 - MakeLabels\n");
	    c.print("2 - InOutWord\n");
	    c.print("3 - InputRect\n");
	    c.print("4 - InputReals\n");
	    c.print("5 - PersonalDetails\n");
	    c.print("6 - ColoredCircles\n");
	    c.print("7 - Prices\n");
	    c.print("8 - InputChars\n");
	    c.print("9 - InputChars2\n");
	    c.print("10 - InputChars3\n");
	    c.print("11 - Overwrite\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    if(choice==1) MakeLabels();
	    else if(choice==2) InOutWord();
	    else if(choice==3) InputRect();
	    else if(choice==4) InputReals();
	    else if(choice==5) PersonalDetails();
	    else if(choice==6) ColoredCircles();
	    else if(choice==7) Prices();
	    else if(choice==8) InputChars();
	    else if(choice==9) InputChars2();
	    else if(choice==10) InputChars3();
	    else if(choice==11) Overwrite();
	
	    c.setCursor(25,1);
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
    } // main method
}
