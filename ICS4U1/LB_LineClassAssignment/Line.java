/* Bing Li
 * ICS4U1
 * Line Class Assignment
 */
import java.awt.*;

class Line
{
    private int A,B,C;

    public Line(String eqn)
    {
        A = B = C = 0;
        int multi = 1; // Used for left/rigth side identification
        String integer="";
        boolean legal = true;
        
        // Check if it is legal
        // If there is no x nor y in the equation
        if(eqn.indexOf('x')<0 && eqn.indexOf('y')<0) legal = false;
        for(int i=0; i<eqn.length(); i++) {
            if(!(Character.toLowerCase(eqn.charAt(i))=='x' || Character.toLowerCase(eqn.charAt(i))=='y' || 
             eqn.charAt(i)=='=' || eqn.charAt(i)=='+' || eqn.charAt(i)=='-' || eqn.charAt(i)==' ' ||
             (eqn.charAt(i)>='0' && eqn.charAt(i)<='9')))
                legal=false;
        }
        
        // If it is legal go through the equation
        // If encounters x or y, parses into integer and adds to A or B
        // If encounters an operation without x or y, parses into int and add to C
        // Reset integer to "" after parsing so it's available to be used again
        for(int i=0; i<eqn.length() && legal; i++) {
            if(Character.toLowerCase(eqn.charAt(i))=='x') {
                if(integer.equals("")) A += multi;
                else if(integer.equals("-")) A -= multi;
                else A += multi*Integer.parseInt(integer);
                integer = "";
            } else if(Character.toLowerCase(eqn.charAt(i))=='y') {
                if(integer.equals("")) B += multi;
                else if(integer.equals("-")) B -= multi;
                else B += multi*Integer.parseInt(integer);
                integer = "";
            } else if(eqn.charAt(i)=='=' || eqn.charAt(i)=='+' || eqn.charAt(i)=='-') {
                if(!integer.equals("") && !integer.equals("-")) {
                    C += multi*Integer.parseInt(integer);
                    integer = "";
                }
            }
            // If the current character is a number or a negative sign, add to integer
            if((eqn.charAt(i)>='0' && eqn.charAt(i)<='9') || eqn.charAt(i)=='-') integer += eqn.charAt(i);
            // Two negatives equal a positive
            if(integer.equals("--")) integer = "";
            // The right side needs to be multiplied by -1 since they are all being subtracted from the left
            if(eqn.charAt(i)=='=') multi = -1;
        }
        
        // If there is still leftover it must have been a constant at the end, so parse into int and add to C
        if(!integer.equals("")) C += multi*Integer.parseInt(integer);
    }

    public String toString() 
    {
        String str = "";
        
        // Put A, B, C in the form of Ax + By + C = 0
        if(A==1) str += "x";
        else if(A==-1) str += "-x";
        else if(A!=0) str += A + "x";

        if(A!=0 && B!=0) {
            if(B==1) str += " + y";
            else if(B==-1) str += " - y";
            else if(B>0) str += " + " + Math.abs(B) + "y";
            else str += " - " + Math.abs(B) + "y";
        } else if(A==0 && B!=0) {
            if(B==1) str += "y";
            else if(B==-1) str += "-y";
            else str += B + "y";
        }

        if((B!=0 || A!=0) && C!=0) {
            if(C>0) str += " + " + Math.abs(C);
            else str += " - " + Math.abs(C);
        } else if(A==0 && B==0 && C!=0) str += C;
        if(!str.equals("")) str += " = 0";

        return str;
    }
    
    // Returns whether the line is vertical
    public boolean vertical()
    {
        return B==0;
    }
    
    // Returns whether the line is horizontal
    public boolean horizontal()
    {
        return A==0;
    }
    
    // Returns the x intercept, or DNE if it doesn't exist
    public String xint()
    {
        String str = "";

        if(horizontal()) str = "DNE";
        else str += (double)-C/A;

        return str;
    }
    
    // Returns the y intercept, or DNE if it doesn't exist
    public String yint()
    {  
        String str = "";

        if(vertical()) str = "DNE";
        else str += (double)-C/B;

        return str;
    }
    
    // Returns the slope, or DNE if it doesn't exist
    public String slope()
    {
        String str = "";

        if(vertical()) str = "DNE";
        else str += (double)-A/B;

        return str;
    }

    public Point intersect(Line line)
    {
        int a = line.A;
        int b = line.B;
        int c = line.C;
        double x,y;
        
        // Get the x value using x intercepts and slope
        // get the y value using the slope y intercept equation
        // Since vertical lines do not have y intercepts, they require special treatment
        if(vertical()) {
            x = Double.parseDouble(xint());
            y = Double.parseDouble(line.slope())*x+Double.parseDouble(line.yint());
        } else if(line.vertical()) {
            x = Double.parseDouble(line.xint());
            y = Double.parseDouble(slope())*x+Double.parseDouble(yint());
        } else {
            x = (Double.parseDouble(line.yint())-Double.parseDouble(yint()))/(Double.parseDouble(slope())-Double.parseDouble(line.slope()));
            y = Double.parseDouble(slope())*x+Double.parseDouble(yint());
        }

        Point p;
        p = new Point(x,y);

        return p;
    }

    public Graphics draw(Graphics g, int w, int h, int minX, int maxX, int minY, int maxY, int xAxis, int yAxis, Color c)
    {
        // Draw line in blue
        g.setColor(c);
        if(!toString().equals("")) {
            Point P,Q;
            int x,y;
            x = y = 0;

            // We can draw all lines given the x and y intercepts
            // Horizontal: y intercept
            // Vertical: x intercept
            // Other: x and y intercept
            if(!horizontal()) x = (int)(Double.parseDouble(xint())*((double)w/(maxX-minX)));
            if(!vertical()) y = (int)(Double.parseDouble(yint())*((double)h/(maxY-minY)));
            // Check if horizontal or vertical line
            if(horizontal()) {
                P = new Point(0,h-xAxis-y);
                Q = new Point(w,h-xAxis-y);
            } else if(vertical()) {
                P = new Point(yAxis+x,0);
                Q = new Point(yAxis+x,h);
            } else {
                // Find the point, then scale to the graph
                P = intersect(new Line("y-"+maxY+"=0"));
                x = (int)(P.x()*w/(maxX-minX));
                y = (int)(P.y()*h/(maxY-minY));
                P = new Point(yAxis+x,h-xAxis-y);

                Q = intersect(new Line("y-"+minY+"=0"));
                x = (int)(Q.x()*w/(maxX-minX));
                y = (int)(Q.y()*h/(maxY-minY));
                Q = new Point(yAxis+x,h-xAxis-y);
            }

            // Draw the actual line
            g.drawLine((int)P.x(),(int)P.y(),(int)Q.x(),(int)Q.y());
        }

        return g;
    }
}