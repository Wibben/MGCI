/* Bing Li
 * ICS4U1
 * Line Class Assignment
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

public class Stats extends JFrame
{
    private int s;
    private JTextArea _words = new JTextArea(3,10);
    private JComboBox<String> dropDown;
    private Line line;
    private GUI window;

    public Stats(GUI gui, Line[] line, int size, int x, int y)
    {
        // Initialize components
        window = gui;
        this.line = new Line("");
        JButton getStatsBtn = new JButton("Get Stats");
        getStatsBtn.setToolTipText("The line will be displayed in purple");
        getStatsBtn.addActionListener(new getStatsBtnListener());
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
        selection.setLayout(new FlowLayout());
        
        // Make the text area uneditable and set font and margins
        _words.setEditable(false);
        _words.setFont(new Font("Veranda", Font.BOLD, 12));
        _words.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        _words.setText("No line selected");
        
        dropDown = new JComboBox<String>(choice);

        // Add components to content panes
        selection.add(dropDown);
        selection.add(getStatsBtn);
        content.add(selection,"North");
        content.add(_words,"Center");

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Stats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(x,y);
    }

    class getStatsBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Get selected item from dropdown menu
            line = new Line((String)dropDown.getSelectedItem());
            // Use DecimalFormat to format output instead of using %.3f
            DecimalFormat thousandth = new DecimalFormat("#.###");
            String output = "x-int: ";
            
            // If it does not exist, output DNE, else output the value
            // Output x and y intercept and slope
            if(!line.xint().equals("DNE")) output += thousandth.format(Double.parseDouble(line.xint()));
            else output += "DNE";
            output += "\ny-int: ";
            if(!line.yint().equals("DNE")) output += thousandth.format(Double.parseDouble(line.yint()));
            else output += "DNE";
            output += "\nslope: ";
            if(!line.slope().equals("DNE")) output += thousandth.format(Double.parseDouble(line.slope()));
            else output += "DNE";

            _words.setText(output);
            
            // If no line selected, output no line selected
            if(line.toString().equals("")) _words.setText("No line selected");
            
            // Send back to gui object and repaint
            window.updateStat(line);
        }
    }
    
    // Return the number of valid lines in the dropdown menu
    public int S()
    {
        return s;
    }
}
