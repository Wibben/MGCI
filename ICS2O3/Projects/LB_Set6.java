/*
Bing Li
Sept 27, 2014
ICS2O3
Set 6 programs in a menu
*/
import java.awt.*;
import hsa.Console;
import java.security.*;

public class LB_Set6
{
    //*******************************NameAge*********************************
  
    public static void NameAge() { 
	String name;
	int birthYear;
	
	c.print("Please type your name and <Enter>:\n");
	name = c.readLine();
	c.print("Please type your year of birth and <Enter>:\n");
	birthYear = c.readInt();
	c.print("\n" + name + ", you will be " + (2014-birthYear) + " years old this year.\n");
    }
    
    //*******************************Chocs*********************************
  
    public static void Chocs() { 
	String name;
	int quantity;
	
	c.print("Please type the name of the chocolate bar and <Enter>:\n");
	name = c.readLine();
	c.print("Please type the number of chocolate bars and <Enter>:\n");
	quantity = c.readInt();
	c.print("\nCHOC BAR",15);
	c.print("QUANTITY",15);
	c.print("COST\n");
	c.print(name,15);
	c.print(quantity);
	c.setCursor(7,30);
	c.print("$");
	c.print((0.99*quantity),0,2);
    }
	
    //*******************************Supermarket*********************************
  
    public static void Supermarket() { 
	String food[] = new String[2];
	double price[] = new double[2];
	
	c.print("Please enter the first item: ");
	food[0] = c.readLine();
	c.print("Please enter its price: ");
	price[0] = c.readDouble();
	c.print("Please enter the second item: ");
	food[1] = c.readLine();
	c.print("Please enter its price: ");
	price[1] = c.readDouble();
	c.print("\nBing's Supermarket\n\n");
	c.print(food[0],15);
	c.println(price[0],0,2);
	c.print(food[1],15);
	c.println(price[1],0,2);
	c.print("               ------\n");
	c.print("TOTAL",15);
	c.print((price[0]+price[1]),0,2);
    }
    
    //*******************************RoundedMarks*********************************
  
    public static void RoundedMarks() { 
	double mark;
	String name;
	
	c.print("Please enter the pupil's name and <Enter>: ");
	name = c.readLine();
	c.print("Please enter the pupil's mark and <Enter>: ");
	mark = c.readDouble();
	c.print("\nName",15);
	c.print("Mark out of 43",20);
	c.print("Rounded %\n");
	c.print(name,14);
	c.print((int)(mark));
	c.setCursor(5,35);
	c.print((int)(mark*100/43));
    }
    
    //*******************************PlayCosts*********************************
  
    public static void PlayCosts() { 
	int adults[] = new int[2];
	int pupils[] = new int[2];
	
	for(int i=1; i<3; i++) {
	    c.print("Please enter the information for night " + i + "\n");
	    c.print("Number of adults: ");
	    adults[i-1] = c.readInt();
	    c.print("Number of pupils: ");
	    pupils[i-1] = c.readInt();
	}
	
	c.print("Total number of adults: " + (adults[0]+adults[1]));
	c.print("\nTotal cost for adults: " + (adults[0]+adults[1])*15);
	c.print("\nTotal number of pupils: " + (pupils[0]+pupils[1]));
	c.print("\nTotal cost for pupils: " + (pupils[0]+pupils[1])*10);
	c.print("\nTotal proceeds: " + ((adults[0]+adults[1])*15+(pupils[0]+pupils[1])*10));
	c.print("\nProfit made: " + ((adults[0]+adults[1])*15+(pupils[0]+pupils[1])*10-9000));
    }
    
    //*******************************Swimmers*********************************
  
    public static void Swimmers() { 
	String name[] = new String[2];
	double time[] = new double[2];
	
	for(int i=1; i<3; i++) {
	    c.print("Please enter the information for swimmer " + i + "\n");
	    c.print("Name: ");
	    name[i-1] = c.readLine();
	    c.print("Time: ");
	    time[i-1] = c.readDouble();
	}
	
	c.print("\n100M Breaststroke Final\n\n");
	c.print("Name",15);
	c.print("Time\n");
	c.print(name[0],15);
	c.println(time[0],0,2);
	c.print(name[1],15);
	c.println(time[1],0,2);
	c.print("\n\nDifference",17);
	c.print((time[1]-time[0]),0,2);
	c.print(" seconds");
    }
    
    //*******************************Rings*********************************
  
    public static void Rings() { 
	Color rings[] = {Color.blue,Color.yellow,Color.black,Color.green,Color.red};
	
	for(int i=0; i<5; i++) {
	    int x = 0+30*i;
	    int y = 0+30*(i%2);
	    c.setColor(rings[i]);
	    c.drawOval(x,y,50,50);
	}
    }
    
    //*******************************MarkAverage*********************************
  
    public static void MarkAverage() { 
	int mark1,mark2;
	String name;
	
	c.print("Please enter the pupil's name: ");
	name = c.readLine();
	c.print("Please enter the mark for the first test(/25): ");
	mark1 = c.readInt();
	c.print("Please enter the mark for the second test(/55): ");
	mark2 = c.readInt();
	c.print("\nJane got " + mark1 + " out of 25 and " + mark2 + "out of 55");
	c.print(", so her final persentage is " + (mark1+mark2)*100/80 + "%");
    }
    
    //*******************************Foodstall*********************************
  
    public static void Foodstall() { 
	int coke,chips;
	double cash;
	
	c.print("Number of cokes: ");
	coke = c.readInt();
	c.print("Number of chips: ");
	chips = c.readInt();
	c.print("Cash paid($): ");
	cash = c.readDouble();
	c.print("Total cost: ");
	c.print((coke*3.00+chips*2.50),0,2);
	c.print("\nChange due: ");
	c.print((cash-(coke*3.00+chips*2.50)),0,2);
    }
    
    //*******************************Starbox*********************************
  
    public static void Starbox() { 
	c.setColor(Color.red);
	c.fillStar(170,100,300,300);
	c.setColor(Color.blue);
	c.drawRect(170,100,300,300);
    }
    
    //*******************************ColdDrinks*********************************
  
    public static void ColdDrinks() { 
	int drinks;
	double cash;
	
	c.print("Number of drinks: ");
	drinks = c.readInt();
	c.print("Cash paid($): ");
	cash = c.readDouble();
	c.print("You wanted " + drinks + " drinks.\n");
	c.print("Total cost:",18);
	c.print((drinks*1.89),8,2);
	c.print("\nChange due:",19);
	c.print((cash-(drinks*1.89)),8,2);
    }
    
    //*******************************DiceThrow*********************************
  
    public static void DiceThrow() { 
	SecureRandom rand = new SecureRandom();
	int random = rand.nextInt(6)+1;
	c.print(random);
    }
    
    //*******************************RandomwShapesD*********************************
  
    public static void RandomwShapesD() { 
	SecureRandom rand = new SecureRandom();
	int length = rand.nextInt(200);
	int height = rand.nextInt(200);
	int x = rand.nextInt(540-length)+50;
	int y = rand.nextInt(400-height)+50;
	Color col[] = {Color.red,Color.blue,Color.green,Color.yellow,Color.magenta,Color.orange};
	int rect = rand.nextInt(6);
	int circ = rand.nextInt(6);
	c.setColor(col[rect]);
	c.fillRect(x,y,length,height);
	c.setColor(col[circ]);
	c.drawOval(x-50,y-50,length+100,height+100);
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
    
	int choice;
	
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 13:\n");
	    c.print("1 - NameAge\n");
	    c.print("2 - Chocs\n");
	    c.print("3 - Supermarket\n");
	    c.print("4 - RoundedMarks\n");
	    c.print("5 - PlayCosts\n");
	    c.print("6 - Swimmers\n");
	    c.print("7 - Rings\n");
	    c.print("8 - MarkAverage\n");
	    c.print("9 - Foodstall\n");
	    c.print("10 - Starbox\n");
	    c.print("11 - ColdDrinks\n");
	    c.print("12 - DiceThrow\n");
	    c.print("13 - RandomwShapesD\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    if(choice==1) NameAge();
	    else if(choice==2) Chocs();
	    else if(choice==3) Supermarket();
	    else if(choice==4) RoundedMarks();
	    else if(choice==5) PlayCosts();
	    else if(choice==6) Swimmers();
	    else if(choice==7) Rings();
	    else if(choice==8) MarkAverage();
	    else if(choice==9) Foodstall();
	    else if(choice==10) Starbox();
	    else if(choice==11) ColdDrinks();
	    else if(choice==12) DiceThrow();
	    else if(choice==13) RandomwShapesD();
	
	    c.setCursor(25,1);
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
    } // main method
} // LB_Set6 class
