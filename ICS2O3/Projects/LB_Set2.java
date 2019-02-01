/*Bing Li
Sept 10, 2014
ICS2O3
Menu Demo using if and else ifs
*/
import java.awt.*;
import hsa.Console;

public class LB_Set2 {
  static Console c;
  
  //too lazy to write c.print();
  public static void p(String s) {
    c.print(s);
  }
  
  //*******************************FieldWidths*********************************
  
  public static void FieldWidths() { 
    p("1234567890");
    p("12345689012345\n");
    c.print("Hello",6);
    c.print("Hello",6);
    c.print("Hello",6);
    p("Hello");
    p("\n\n");
    p("1234567890123456789012345678901234567890\n");
    c.print("Hello",12);
    c.print("Hello",12);
    p("Hello");
  }
  
  //*******************************Music*********************************
  
  public static void Music() { 
    p("123456789012345\n");
    p("  Rock and Roll\n");
    p("      Metal\n\n");
    p("    The Blues\n");
  }
  
  //*******************************NameAndAddress*********************************
  
  public static void NameAndAddress() { 
    p("\t\t\t\t\t\t\tNAME AND ADDRESS\n");
    p("\t\t\t\t\t\t\tBing Li\n");
    p("\t\t\t\t\t\t\t18 Lovedale Crescent\n");
    p("\t\t\t\t\t\t\tRietfontein\n");
    p("\t\t\t\t\t\t\tNorthen Province\n");
    p("\t\t\t\t\t\t\t0036\n");
  }
  
  //*******************************PrintName*********************************
  
  public static void PrintName() { 
    p("My name is Bing\n");
    p("I am in Grade 10\nI am attending Marc Garneau\nI am from China\n");
  }
  
  //*******************************PrintOutput*********************************
  
  public static void PrintOutput() { 
    p("12345678901234567890 ");
    p("Hello ");
    p("How are you?");
  }
  
  //*******************************PrintOutputb*********************************
  
  public static void PrintOutputb() { 
    p("Hello. "+"How are you today?");
  }
  
  //*******************************Shopping*********************************
  
  public static void Shopping() { 
    p("I am going");
    p("to the\n");
    p("shoppng mall.\n\n");
    p("There is no bus\n");
    p("May I have a lift?");
  }
  
  //*******************************VirusWarning*********************************
  
  public static void VirusWarning() { 
    p("\t"+"Warning"+"\n\n");
    p("\tPossible virus detected \n\tReboot and run antivirus software\n");
  }
  
  //*******************************Main*********************************
  
  public static void main(String[] args) { 
    c = new Console();
  
    int choice;
    
    do {
    p("\nPlease choose from the following menu\n\n");
    p("Enter a number from 1 to 8:\n");
    p("1 - FieldWidth\n");
    p("2 - Music\n");
    p("3 - NameAndAddress\n");
    p("4 - PrintName\n");
    p("5 - PrintOutput\n");
    p("6 - PrintOutputb\n");
    p("7 - Shopping\n");
    p("8 - VirusWarning\n");
    p("\nEnter 0 to exit\n");
    
    choice = c.readInt();
    
    c.clear();
    if(choice==1) FieldWidths();
    else if(choice==2) Music();
    else if(choice==3) NameAndAddress();
    else if(choice==4) PrintName();
    else if(choice==5) PrintOutput();
    else if(choice==6) PrintOutputb();
    else if(choice==7) Shopping();
    else if(choice==8) VirusWarning();
    
    p("\n\nPress Enter to continue");
    c.getChar();
    c.clear();
    
    } while(choice!=0);
    
    p("Program terminated\n");
    
  }
  
}
