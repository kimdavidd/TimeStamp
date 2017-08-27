import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
/**
 * This class is the for the adding a new person GUI
 *
 * @author David D. Kim
 * @version May 18, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and Katherine S. Li
 *
 * @author Sources: none.
 */
public class addPersonGUI
{

    public JFrame frame;
    private JTextField name;
    private JTextField isBusy;
    public static List<List<tD>> tempListOfSchedules = new ArrayList<List<tD>>();
    public static JTextArea allSchedules;

    /**
     * Create the application.
     */
    public addPersonGUI()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    public void initialize()
    {
        frame = new JFrame();
        frame.setBounds( 100, 100, 620, 310 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setBounds(24, 22, 46, 14);
        frame.getContentPane().add(lblNewLabel);
        
        name = new JTextField();
        name.setBounds(153, 19, 86, 20);
        frame.getContentPane().add(name);
        name.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Busy or Available?");
        lblNewLabel_1.setBounds(265, 22, 127, 14);
        frame.getContentPane().add(lblNewLabel_1);
        
        isBusy = new JTextField();
        isBusy.setBounds(423, 19, 86, 20);
        frame.getContentPane().add(isBusy);
        isBusy.setColumns(10);
        
        JButton btnAddSchedule = new JButton("Add Schedule");
        btnAddSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                addScheduleGUI addSchedule = new addScheduleGUI();
                addSchedule.frame.setVisible( true );
            }
        });
        btnAddSchedule.setBounds(392, 116, 117, 23);
        frame.getContentPane().add(btnAddSchedule);
        
        JButton btnAddAnotherPerson = new JButton("Add Another Person");
        btnAddAnotherPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                boolean busy;
                if ( isBusy.getText().toLowerCase().equals( "busy" ) )
                {
                   busy = true;
                }
                else
                {
                   busy = false;
                }
                
                List<tD> schedule = new ArrayList<tD>();
                
                for ( List<tD> schedules : tempListOfSchedules )
                {
                    for ( tD td : schedules )
                    {
                        schedule.add( td );
                    }
                }
                tempListOfSchedules.clear();
                
                MainGUI.newActivity.addPerson( new Person( name.getText(), busy, schedule ) );
                name.setText( "" );
                isBusy.setText( "" );
                allSchedules.setText( "Added Schedule(s):" );
            }
        });
        btnAddAnotherPerson.setBounds(10, 228, 168, 23);
        frame.getContentPane().add(btnAddAnotherPerson);
        
        JButton btnDone = new JButton("Done");
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                boolean busy;
                if ( isBusy.getText().toLowerCase().equals( "busy" ) )
                {
                   busy = true;
                }
                else
                {
                   busy = false;
                }
                
                List<tD> schedule = new ArrayList<tD>();
                
                for ( List<tD> schedules : tempListOfSchedules )
                {
                    for ( tD td : schedules )
                    {
                        schedule.add( td );
                    }
                }
                tempListOfSchedules.clear();
                
                MainGUI.newActivity.addPerson( new Person( name.getText(), busy, schedule ) );
                frame.setVisible( false );
                resultGUI result = new resultGUI();
                result.frame.setVisible( true );
                allSchedules.setText( "" );
            }
        });
        btnDone.setBounds(485, 228, 89, 23);
        frame.getContentPane().add(btnDone);
        
        allSchedules = new JTextArea();
        allSchedules.setText("Added Schedule(s):");
        allSchedules.setBounds(10, 55, 285, 162);
        frame.getContentPane().add(allSchedules);
    }
}
