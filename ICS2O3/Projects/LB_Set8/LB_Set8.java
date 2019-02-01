/*
Bing Li
Oct 21, 2014
ICS2O3
Set 8 programs in a menu with new specs
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;

public class LB_Set8
{
    //*******************************PrintChars*********************************
  
    public static void PrintChars() { 
	char choice;
	
	// loop until input is 'x'
	do {
	    // Prompt for input
	    c.print("Please select a subprogram:\n");
	    c.print("a - Letters from A to Z\n");
	    c.print("b - Letters from Z to A\n");
	    c.print("c - ASCII characters with values from 32 to 126\n");
	    c.print("x - Exit\n");
	    
	    choice = c.getChar();
	    
	    // Check choice, switch statements are quick to use, with no curly brackets
	    // but syntax errors occurr when checking either/or cases such as ('a'||'A') 
	    switch(choice) {
		case 'a': // Display letters from A to Z
		    for(int i=0; i<26; i++)
			c.print((char)('A'+i) + " ");
		    break;
		case 'b': // Display letters from Z to A
		    for(int i=0; i<26; i++)
			c.print((char)('Z'-i) + "   ");
		    break;
		case 'c': // Display 32nd to 126th ASCII characters 
		    for(int i=32; i<=126; i++)
			c.print(i + " " + (char)(i) + "\t");
		    break;
		case 'x': // Should not display error
		    break;
		default: // Error message
		    c.print("Invalid Input\n");
		    break;
	    }
	    
	    c.print("\nPress Enter to continue...");
	    c.getChar();
	    c.clear();
	} while(choice!='x');
    }
    
    //*******************************PrintLbl*********************************
  
    public static void PrintLbl() { 
	// Top border
	c.setCursor(9,32);
	for(int i=0; i<16; i++)
	    c.print("=");
	
	// Words in the middle, right aligned with setCurcor()
	c.setCursor(11,32);
	c.print("realcoolclothing");
	c.setCursor(12,44);
	c.print("from");
	c.setCursor(13,35);
	c.print("ETHNIK AFRIKA");
	
	//Bottom Border
	c.setCursor(15,32);
	for(int i=0; i<16; i++)
	    c.print("=");
    }
	
    //*******************************PrintDiag*********************************
  
    public static void PrintDiag() throws InterruptedException{ 
	// Display 6 to 1 on a line with slope 1
	c.print("Pattern A:\n");
	for(int i=6; i>0; i--)
	    c.println(i,i);
	Thread.sleep(3000);
	c.clear();
	
	// Display 1 to 6 on a line with slope 1
	c.print("Pattern B:\n");
	for(int i=6; i>0; i--)
	    c.println(7-i,i);
	Thread.sleep(3000);
	c.clear();
	
	// Display 6 to 1 on a line with slope -1
	c.print("Pattern C:\n");
	for(int i=1; i<=6; i++)
	    c.println(7-i,i);
	Thread.sleep(3000);
	c.clear();
	
	// Display A to F on a line with slope -1
	c.print("Pattern D:\n");
	for(int i=1; i<7; i++) {
	    if(i!=5) {
		c.print(" ",i);
		c.println((char)('A'-1+i));
	    } else { // Special case: display a row of E's
		c.print(" ");
		for(int j=0; j<6; j++)
		    c.print("E");
		c.println();
	    }
	}
    }
    
    //*******************************ConvertTemp*********************************
  
    public static void ConvertTemp() {         
	// Converts the range of 100C to 280C into Fahrenheit
	c.print("The format is Centigrade: Fahrenheit\n");
	for(int i=100; i<=280; i++) {
	    c.print(i + ": ");
	    c.print((9/(5*i)+32) + "   ");
	}
    }
    
    //*******************************MaxNums*********************************
  
    public static void MaxNums() { 
	// Make max num small as possible and min num large as possible
	int nums,num,max = -2147483647,min = 2147483647;
	
	// Prompt for number of numbers
	c.print("Please enter the number of numbers: ");
	nums = c.readInt();
	
	// Pronpt for the numbers and update max and min accordingly after each input
	c.print("Please enter each number and <Enter>:\n");
	for(int i=0; i<nums; i++) {
	    num = c.readInt();
	    if(num>max) max = num;
	    if(num<min) min = num;
	}
	
	// Display Results
	c.print("The largest number was: " + max);
	c.print("\nThe smallest number was: " + min);
    }
    
    //*******************************BakedBeans*********************************
  
    public static void BakedBeans() { 
	String brand[] = new String[100];
	double price[] = new double[100];
	int NoB;
	int best = 0;
	
	// Prompt for number of brands
	c.print("Please enter the number of brands: ");
	NoB = c.readInt();
	
	// Prompt for each brand and price, store it into the array, and update the cheapest brand accordingly
	c.print("Please enter each brand's name and <Enter>, then its price in $ and <Enter>:\n");
	for(int i=0; i<NoB; i++) {
	    brand[i] = c.readLine();
	    price[i] = c.readDouble();
	    if(price[i]<=price[best]) best = i;
	}
	
	// Display all brands and prices
	c.print("Brand",20);
	c.print("Price($)");
	for(int i=0; i<NoB; i++) {
	    c.print("\n" + brand[i],20);
	    c.print(price[i],9,2);
	}
	
	// Display best brand and price
	c.print("\nThe best deal is " + brand[best] + " at $");
	c.print(price[best],0,2);
    }
    
    //*******************************FivePatterns*********************************
  
    public static void FivePatterns() throws InterruptedException { 
	c.print("Pattern A:\n");
	
	for(int i=1; i<=4; i++) { // i-th row count 1 to 4
	    for(int j=1; j<=i; j++) // j-th columns count 1 to i
		c.print(j); // Display j
	    c.println();
	}
	Thread.sleep(3000);
	c.clear();
	
	c.print("Pattern B:\n");
	for(int i=1; i<=4; i++) { // i-th row count 1 to 4
	    for(int j=1; j<=i; j++) // j-th columns count 1 to i
		c.print(i); // Display i
	    c.println();
	}
	Thread.sleep(3000);
	c.clear();
	
	c.print("Pattern C:\n");
	for(int i=1; i<=4; i++) { // i-th row count 1 to 4
	    for(int j=i; j>0; j--) // j-th columns count i to 1
		c.print(j); // Display j
	    c.println();
	}
	Thread.sleep(3000);
	c.clear();
	
	c.print("Pattern D:\n");
	int cnt = 1;
	for(int i=1; i<=4; i++) { // i-th row count 1 to 4
	    for(int j=1; j<=i; j++)  // j-th columns count 1 to i
		c.print(cnt++); // Display cnt, then cnt increments
	    c.println();
	}
	Thread.sleep(3000);
	c.clear();
	
	c.print("Pattern E:\n");
	for(int i=0; i<5; i++) { // i-th row count 0 to 5
	    for(int j=1; j<=5; j++) // j-th columns count 1 to 5
		c.print(j+i); // Display j+i
	    c.println();
	}
	Thread.sleep(3000);
	c.clear();
    }
    
    //*******************************Triangle*********************************
  
    public static void Triangle() { 
	for(int i=4; i>=0; i--) { // 5 rows count from 4 to 0
	    c.print("",i); // Display i blank spaces            
	    for(int j=0; j<9-2*i; j++) // Display 9-2*i '%' signs
		c.print("%");
	    c.println();
	}
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
    
	int choice;
	
	do {
	    // Prompt for choice
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 8:\n");
	    c.print("1 - PrintChars\n");
	    c.print("2 - PrintLbl\n");
	    c.print("3 - PrintDiag\n");
	    c.print("4 - ConvertTemp\n");
	    c.print("5 - MaxNums\n");
	    c.print("6 - BakedBeans\n");
	    c.print("7 - FivePatterns\n");
	    c.print("8 - Triangle\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    // Match choice with correct program
	    if(choice==1) PrintChars();
	    else if(choice==2) PrintLbl();
	    else if(choice==3) PrintDiag();
	    else if(choice==4) ConvertTemp();
	    else if(choice==5) MaxNums();
	    else if(choice==6) BakedBeans();
	    else if(choice==7) FivePatterns();
	    else if(choice==8) Triangle();
	
	    c.setCursor(25,1);
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	// Termination message
	c.print("Program terminated\n");
	
    } // main method
} // LB_Set8 class
