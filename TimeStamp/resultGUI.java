import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
/**
 * This class is for the final result GUI
 *
 * @author David D. Kim
 * @version May 18, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and Katherine S. Li
 *
 * @author Sources: none.
 */
public class resultGUI
{

    public JFrame frame;


    /**
     * Create the application.
     */
    public resultGUI()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds( 100, 100, 620, 310 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        PQResult result = new PQResult( MainGUI.newActivity.returnAllPosEvents() );
        ArrayList<posEvent> a = result.getEntireSortedList();
        String text = "";
        for ( posEvent j : a)
        {
            text += j + "\n";
        }
        frame.getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 580, 260);
        frame.getContentPane().add(scrollPane);
        
        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setText( text );
        

    }
}
