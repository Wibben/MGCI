/* Bing Li
 * ICS4U1
 * Line Class Assignment
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

public class Intersect extends JFrame
{
    private int s;
    private JTextArea _words = new JTextArea(1,15);
    private JComboBox<String> dropDown1,dropDown2;
    private GUI window;

    public Intersect(GUI gui, Line[] line, int size, int x, int y, int width)
    {
        // Initialize components
        window = gui;
        JButton getIntersectBtn = new JButton("Get Intersect");
        getIntersectBtn.setToolTipText("The lines will be displayed in green");
        getIntersectBtn.addActionListener(new getIntersectBtnListener());
        s = 0;
        for(int i=0; i<size; i++)
            if(!line[i].toString().equals("")) s++;

        String[] choice = new String[s+1];
        choice[0] = "Select a line";
        for(int i=0,j=1; i<size; i++) {
            if(!line[i].toString().equals("")) choice[j++] = line[i].toString();
        }

        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JPanel selection = new JPanel();
        selection.setLayout(new GridLayout(3,1));
        
        // Make text area uneditable and set font and margin
        _words.setEditable(false);
        _words.setFont(new Font("Veranda", Font.BOLD, 12));
        _words.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        _words.setText("No line selected");

        dropDown1 = new JComboBox<String>(choice);
        dropDown2 = new JComboBox<String>(choice);

        // Add components to content panes
        selection.add(dropDown1);
        selection.add(dropDown2);
        selection.add(getIntersectBtn);
        content.add(selection,"North");
        content.add(_words,"Center");

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Intersect");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(x,y);
    }

    class getIntersectBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Get selected lines
            Line line1 = new Line((String)dropDown1.getSelectedItem());
            Line line2 = new Line((String)dropDown2.getSelectedItem());
            
            // If one of the lines invalid, say no line selected
            // If lines are the same or parallel, say so
            // Other wise get the intersect and output result
            if(line1.toString().equals("") || line2.toString().equals("")) _words.setText("No line selected");
            else if(line1.toString().equals(line2.toString())) _words.setText("They are the same line");
            else if(line1.slope().equals(line2.slope())) _words.setText("They are parallel");
            else {
                Point p = line1.intersect(line2);
                DecimalFormat thousandth = new DecimalFormat("#.###");
                
                _words.setText(
                    "P( " + thousandth.format(p.x()) + " , " + thousandth.format(p.y()) + " )"
                );
                
                window.updateIntersect(line1,line2);
            }
        }
    }
    
    // Return the number of valid lines in the dropdown menu
    public int S()
    {
        return s;
    }
}
