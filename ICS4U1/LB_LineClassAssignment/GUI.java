/* Bing Li
 * ICS4U1
 * Line Class Assignment
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame
{
    private Line[] line;
    private int maxX,maxY,minX,minY;
    private int size;

    private JTextField _equation = new JTextField(15);
    private JTextField _maxX = new JTextField(5);
    private JTextField _maxY = new JTextField(5);
    private JTextField _minX = new JTextField(5);
    private JTextField _minY = new JTextField(5);
    private Stats stat;
    private Intersect intersect;
    private Line statLine,intersectLine1,intersectLine2;

    public GUI()
    {
        // Initialize components
        _equation.setToolTipText("Type in a linear equation in any format you wish, separate equations with commas");
        _maxX.setToolTipText("The maximum value of the x-axis");
        _minX.setToolTipText("The minimum value of the x-axis");
        _maxY.setToolTipText("The maximum value of the y-axis");
        _minY.setToolTipText("The minimum value of the y-axis");
        JButton graphBtn = new JButton("Auto-Scaling");
        graphBtn.setToolTipText("Automatically scales the graph so all x and y intercepts are in view");
        JButton customDomBtn = new JButton("Custom Domain");
        customDomBtn.setToolTipText("Scales the graph using the max and min x and y at the bottom of the screen");
        line = new Line[1];
        size = 1;
        line[0] = new Line("");
        stat = new Stats(this,line,size,this.getX()+this.getWidth(),this.getY());
        intersect = new Intersect(this,line,size,stat.getX(),stat.getY()+stat.getHeight(),stat.getWidth());
        statLine = intersectLine1 = intersectLine2 = new Line("");
        minX = minY = -5;
        maxX = maxY = 5;
        _minX.setText(""+minX);
        _maxX.setText(""+maxX);
        _minY.setText(""+minY);
        _maxY.setText(""+maxY);
        graphBtn.addActionListener(new graphBtnListener());
        customDomBtn.addActionListener(new customDomBtnListener());

        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JPanel equationRow = new JPanel();
        equationRow.setLayout(new FlowLayout());
        JPanel limitRow = new JPanel();
        limitRow.setLayout(new FlowLayout());
        DrawArea display = new DrawArea(500,500);
        
        // Add components to content panes
        equationRow.add(new JLabel("Equation:"));
        equationRow.add(_equation);
        equationRow.add(graphBtn);
        equationRow.add(customDomBtn);
        limitRow.add(new JLabel("Min x:"));
        limitRow.add(_minX);
        limitRow.add(new JLabel("Max x:"));
        limitRow.add(_maxX);
        limitRow.add(new JLabel("Min y:"));
        limitRow.add(_minY);
        limitRow.add(new JLabel("Max y:"));
        limitRow.add(_maxY);

        content.add(equationRow,"North");
        content.add(limitRow,"South");
        content.add(display,"Center");
        
        // Setting up keybindings to pan wth arrow keys
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "move up");
        content.getActionMap().put("move up", new PanAction(1));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "move down");
        content.getActionMap().put("move down", new PanAction(2));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "move left");
        content.getActionMap().put("move left", new PanAction(3));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "move right");
        content.getActionMap().put("move right", new PanAction(4));

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Line Graphing Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    class PanAction extends AbstractAction
    {
        private int dir;
        
        public PanAction(int direction) 
        {
            // Set direction
            dir = direction;
        }
        
        public void actionPerformed(ActionEvent e) {
            // Use x and y multipliers to decide how much to move
            // Details in DrawArea's PaintComponent(Graphics g)
            int xMultiplier,yMultiplier;
            xMultiplier = (int)Math.pow(10.0,(int)Math.log10((maxX-minX)/2));
            yMultiplier = (int)Math.pow(10.0,(int)Math.log10((maxY-minY)/2));
            
            if(dir==1) { // UP
                maxY+=yMultiplier;
                minY+=yMultiplier;
            } else if(dir==2) { // DOWN
                maxY-=yMultiplier;
                minY-=yMultiplier;
            } else if(dir==3) { // LEFT
                maxX-=yMultiplier;
                minX-=yMultiplier;
            } else if(dir==4) { // RIGHT
                maxX+=yMultiplier;
                minX+=yMultiplier;
            }
            
            // Update textboxes
            _minX.setText(""+minX);
            _maxX.setText(""+maxX);
            _minY.setText(""+minY);
            _maxY.setText(""+maxY);
            
            // Update graphics
            repaint();
        }
    }

    class graphBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Get equation and set line to equation
            // Commas indicate more than 1 equation
            statLine = intersectLine1 = intersectLine2 = new Line("");
            String equation = _equation.getText();
            equation+=",";
            int commas = equation.length()-equation.replace(",","").length();
            line = new Line[commas];
            size = commas;
            
            maxX = maxY = minX = minY = 0;
            
            // Get maxX by getting the maximum of all x intercepts
            // Get minX by getting the minimum of all x intercepts
            // Get maxY and min Y using the same method
            // Also processes the input into lines
            for(int i=0,j=0,k=0; i<equation.length(); i++) {
                if(equation.charAt(i)==',') {
                    line[k] = new Line(equation.substring(j,i));

                    if(!line[k].toString().equals("")) {
                        if(line[k].horizontal()) {
                            maxX = Math.max(0,maxX);
                            minX = Math.min(0,minX);
                        } else {
                            maxX = Math.max((int)Double.parseDouble(line[k].xint()),maxX);
                            minX = Math.min((int)Double.parseDouble(line[k].xint()),minX);
                        }
                        if(line[k].vertical()) {
                            maxY = Math.max(0,maxY);
                            minY = Math.min(0,minY);
                        } else {
                            maxY = Math.max((int)Double.parseDouble(line[k].yint()),maxY);
                            minY = Math.min((int)Double.parseDouble(line[k].yint()),minY);
                        }
                    }

                    j = i+1; k++;
                }
            }

            // Put some room on the sides
            minX-=5;
            maxX+=5;
            minY-=5;
            maxY+=5;

            // repaint() but with many reused procedures
            paint();
        }
    }

    class customDomBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Get equation and set line to equation
            // Commas indicate more than 1 equation
            statLine = intersectLine1 = intersectLine2 = new Line("");
            String equation = _equation.getText();
            equation+=",";
            int commas = equation.length()-equation.replace(",","").length();
            line = new Line[commas];
            size = commas;
            
            // Processes input into lines
            for(int i=0,j=0,k=0; i<equation.length(); i++) {
                if(equation.charAt(i)==',') {
                    line[k] = new Line(equation.substring(j,i));
                    j = i+1; k++;
                }
            }
            
            // Get max and min x and y, if not entered, set to 25
            String num;
            num = _maxX.getText();
            if(num.equals("")) maxX = 25;
            else maxX = Integer.parseInt(num);
            num = _maxY.getText();
            if(num.equals("")) maxY = 25;
            else maxY = Integer.parseInt(num);
            num = _minX.getText();
            if(num.equals("")) minX = -25;
            else minX = Integer.parseInt(num);
            num = _minY.getText();
            if(num.equals("")) minY = -25;
            else minY = Integer.parseInt(num);
            
            // repaint() but with many reused procedures
            paint();
        }
    }
    
    private void paint() 
    {
        // Update textboxes
        _minX.setText(""+minX);
        _maxX.setText(""+maxX);
        _minY.setText(""+minY);
        _maxY.setText(""+maxY);

        String equation = "";
        for(int i=0; i<size; i++)
            if(!line[i].toString().equals("")) equation+=line[i].toString()+", ";
        if(!equation.equals("")) _equation.setText(equation.substring(0,equation.length()-2));
        else _equation.setText("");

        // Display stats window
        stat.dispose();
        stat = new Stats(this,line,size,getX()+getWidth(),getY());
        if(stat.S()>0) stat.setVisible(true);
        else stat.setVisible(false);

        // Display intersect
        intersect.dispose();
        intersect = new Intersect(this,line,size,stat.getX(),stat.getY()+stat.getHeight(),stat.getWidth());
        if(intersect.S()>1) intersect.setVisible(true);
        else intersect.setVisible(false);
        
        // Update graphics
        repaint();
    }
    
    public void updateStat(Line line)
    {
        statLine = line;
        
        // Update graphics
        repaint();
    }
    
    public void updateIntersect(Line a, Line b) 
    {
        intersectLine1 = a;
        intersectLine2 = b;
        
        // Update graphics
        repaint();
    }

    class DrawArea extends JPanel
    {
        public DrawArea(int width, int height)
        {
            // Setting up drawing area
            this.setPreferredSize(new Dimension(width,height));
        }

        public void paintComponent (Graphics g)
        {
            int w = this.getWidth();
            int h = this.getHeight();
            int yAxis,xAxis;

            // Determine location of axes in relation to max and min x and y
            yAxis = (int)(w-w*(double)maxX/(maxX-minX));
            xAxis = (int)(h-h*(double)maxY/(maxY-minY));

            // Draw border and axes in black and gridline in grey
            // Make background white
            g.setColor(Color.WHITE);
            g.fillRect(0,0,w-1,h-1);

            // Draw gridlines
            /* Scaling Gridlines:
             * Grid lines appear at the highest power of ten less than
             * (maxX-minX)/2 and
             * (maxY-minY)/2 and
             */
            int xMultiplier,yMultiplier;
            xMultiplier = (int)Math.pow(10.0,(int)Math.log10((maxX-minX)/2));
            yMultiplier = (int)Math.pow(10.0,(int)Math.log10((maxY-minY)/2));

            // Horizontal gridlines
            if(maxY==0) {
                for(int i=0,j=0; i+h-xAxis<=h; i+=(int)(yMultiplier*(double)h/-minY),j-=yMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(0,i+h-xAxis,w,i+h-xAxis);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,yAxis+1,i+h-xAxis-1);
                }
            } else {
                for(int i=0,j=0; i+h-xAxis<=h; i+=(int)(yMultiplier*(double)(h-xAxis)/maxY),j-=yMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(0,i+h-xAxis,w,i+h-xAxis);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,yAxis+1,i+h-xAxis-1);
                }
                for(int i=0,j=0; i+h-xAxis>=0; i-=(int)(yMultiplier*(double)(h-xAxis)/maxY),j+=yMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(0,i+h-xAxis,w,i+h-xAxis);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,yAxis+1,i+h-xAxis-1);
                }
            }
            // Vertical Gridlines
            if(maxX==0) {
                for(int i=0,j=0; i+yAxis>=0; i-=(int)(xMultiplier*(double)w/-minX),j-=xMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(i+yAxis,0,i+yAxis,h);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,i+yAxis+1,h-xAxis-1);
                }
            } else {
                for(int i=0,j=0; i+yAxis<=w; i+=(int)(xMultiplier*(double)(w-yAxis)/maxX),j+=xMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(i+yAxis,0,i+yAxis,h);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,i+yAxis+1,h-xAxis-1);
                }
                for(int i=0,j=0; i+yAxis>=0; i-=(int)(xMultiplier*(double)(w-yAxis)/maxX),j-=xMultiplier) {
                    g.setColor(new Color(175,175,175));
                    g.drawLine(i+yAxis,0,i+yAxis,h);
                    g.setColor(new Color(0,0,0));
                    g.drawString(""+j,i+yAxis+1,h-xAxis-1);
                }
            }

            // Draw axes and border
            g.setColor(Color.BLACK);
            // x-axis
            if(minY<0 && maxY>0) {
                g.setColor(Color.BLACK);
                g.drawLine(0,h-xAxis,w,h-xAxis);
            }
            // y-axis
            if(minX<0 && maxX>0) {
                g.setColor(Color.BLACK);
                g.drawLine(yAxis,0,yAxis,h);
            }
            // Draw borders
            g.drawRect(0,0,w-1,h-1);

            // Normal lines: blue
            // Stat line: red
            // Intersecting lines: green
            // Draw line
            for(int i=0; i<size; i++) {
                g = line[i].draw(g,w,h,minX,maxX,minY,maxY,xAxis,yAxis,Color.BLUE);
            }
            
            // Draw stat and intersect lines
            g = statLine.draw(g,w,h,minX,maxX,minY,maxY,xAxis,yAxis,Color.MAGENTA);
            g = intersectLine1.draw(g,w,h,minX,maxX,minY,maxY,xAxis,yAxis,Color.GREEN);
            g = intersectLine2.draw(g,w,h,minX,maxX,minY,maxY,xAxis,yAxis,Color.GREEN);
        }
    }

    public static int main()
    {   
        GUI window = new GUI();
        Instructions instructions = new Instructions();

        window.setVisible(true);
        instructions.setVisible(true);

        return 0;
    }
}
