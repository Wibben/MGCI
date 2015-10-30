/* Recursion Fun
 * Bing Li and Frederick Ngo
 * Oct 8, 2015
 * ICS4U1
*/
import java.util.*;

public class FloorPlan
{
    // Variable declaration
    public static int r;
    public static StringBuffer[] floorplan;
    
    public static int findArea(int row, int col, char room) throws InterruptedException
    {
        // If the tile has been visited or is out of bounds the area is 0
        if(row<0 || row>=r || col<0 || col>=floorplan[row].length() || floorplan[row].charAt(col)!='.') return 0;
        
        // Show current progress
        floorplan[row].setCharAt(col,'o');
        for(int i=0; i<r; i++)
    	   System.out.printf("%s\n",floorplan[i]);
    	System.out.printf("\no = current position");
        Thread.sleep(250);
      	System.out.printf("\u000c");
        
      	// Mark current location as visited and get area of the rooms of the 4 adjacent tiles
      	// Adding 1 to account for the current tile
        floorplan[row].setCharAt(col,room);
        return 1+findArea(row+1,col,room)+findArea(row-1,col,room)+findArea(row,col+1,room)+findArea(row,col-1,room);
    }
    
    public static int main() throws InterruptedException
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        char choice;
        char room;
        ArrayList areas;
        
        do {
            // Variable initialization
            room = '1';
            areas = new ArrayList();
            
            // Get User input
            System.out.printf("\u000c");
            System.out.printf("How long is the floor plan (rows)? ");
            r = in.nextInt();
            in.nextLine();
            
            floorplan = new StringBuffer[r];
            
            System.out.printf("Please input your floorplan:\n");
            for(int i=0; i<r; i++)
                floorplan[i] = new StringBuffer(in.nextLine());
            
            System.out.printf("\u000c");
            
            // Find an empty room and find the area of that room
            for(int i=0; i<r; i++)
        		for(int j=0; j<floorplan[i].length(); j++)
        			if(floorplan[i].charAt(j)=='.') areas.add(findArea(i,j,room++));
            
        	// Output and displaying final configuration and final numbers
        	for(int i=0; i<r; i++)
        	   System.out.printf("%s\n",floorplan[i]);
        	System.out.printf("\n");
        	for(int i=0; i<areas.size(); i++)
        	   System.out.printf("Room %d = %d\n", i+1, areas.get(i));
        	
        	System.out.printf("\nDo you want to go again? (y or n) ");
        	choice = in.nextLine().charAt(0);
        } while(choice=='y');
        
        return 0;
    }
}
