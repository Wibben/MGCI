/* Bing Li
 * ICS4U1
 * Conway's Game of Life
 */
import java.awt.*;
import java.io.*;

class Colony
{
    private boolean grid[][];

    public Colony(double density)
    {
        grid = new boolean[100][100];
        for(int row=0; row<grid.length; row++)
            for(int col=0; col<grid[0].length; col++)
                grid[row][col] = Math.random()<density;
    }

    public void show(Graphics g)
    {
        for(int row=0; row<grid.length; row++) {
            for(int col=0; col<grid[0].length; col++) {
                if (grid[row][col]) g.setColor (Color.black); // Life
                else g.setColor (Color.white);
                g.fillRect (col * 5, row * 5, 5, 5); // Draw life form
            }
        }
        
        // Draw box to indicate affected area
        g.setColor(new Color(50,50,50,50));
        for(int i=0; i<100; i++) {
            g.drawLine(0,i*5,500,i*5);
            g.drawLine(i*5,0,i*5,500);
        }
    }

    public void advance()
    {
        boolean[][] temp = new boolean[grid.length][grid[0].length]; // Make a temporary grid

        // Go through each cell and check if the cell is alive or dead next iteration
        for(int i=0; i<grid.length; i++) 
            for(int j=0; j<grid[0].length; j++) 
                temp[i][j] = live(i,j);

        grid = temp; // Make the temporary grid the actual grid
    }
    
    public void populate(int row, int col, int endrow, int endcol)
    {
        for(int i=Math.max(Math.min(row,endrow),0); i<Math.min(Math.max(row,endrow)+1,grid.length); i++) 
            for(int j=Math.max(Math.min(col,endcol),0); j<Math.min(Math.max(col,endcol)+1,grid[0].length); j++) 
                if(Math.random()<0.8) grid[i][j] = true; // 80% chance of population
    }
    
    public void eradicate(int row, int col, int endrow, int endcol)
    {
        for(int i=Math.max(Math.min(row,endrow),0); i<Math.min(Math.max(row,endrow)+1,grid.length); i++) 
            for(int j=Math.max(Math.min(col,endcol),0); j<Math.min(Math.max(col,endcol)+1,grid[0].length); j++) 
                if(Math.random()<0.8) grid[i][j] = false; // 80% chance of eradication
    }

    private boolean live(int row, int col)
    {
        int l = 0; // Number of live neighbours

        for(int i=-1; i<=1; i++)
            for(int j=-1; j<=1; j++)
                if((i!=0 || j!=0) && row+i>=0 && row+i<grid.length && col+j>=0 && col+j<grid[0].length && grid[row+i][col+j]) l++; // Increment live if the cell is alive

        if(l<2 || l>3) return false; // If there are less than 2 neightbours or mor ehtan 3 neights the cell dies
        else if(!grid[row][col] && l==3) return true; // If the cell is dead and has 3 neighbours it will come to life
        else if(grid[row][col]) return true; // The only case left (2<=lives<=3 and cell is alive) wil stay alive
        else return false; // Last option is the cell is dead and stays dead
    }

    public void save(String filename)
    {
        try {
            FileOutputStream w = new FileOutputStream(filename+".txt");
            
            for(int i=0; i<grid.length; i++)
                for(int j=0; j<grid[0].length; j++)
                    w.write((grid[i][j] ? '1':'0')); // Write a 1 if alive, 0 if dead
            
            w.close(); // Close the file
        } catch(IOException e) {
            System.err.println("File not found\n");
        }
    }

    public void load(String filename)
    {
        try {
            FileInputStream in = new FileInputStream(filename+".txt");

            // Write to the file continously with no line breaks
            for(int i=0; i<grid.length; i++)
                for(int j=0; j<grid[0].length; j++)
                    grid[i][j] = (in.read()=='1' ? true:false); // Reads a 1 if alive, 0 if dead
                    
            in.close(); // close the file
        } catch(Exception e) {
            System.out.printf("Cannot open file\n");
        }
    }
}