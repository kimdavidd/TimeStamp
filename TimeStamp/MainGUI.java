import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
/**
 * This class is the main GUI for when the user first opens the program (The event manager)
 *
 * @author David D. Kim
 * @version May 18, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and Katherine S. Li
 *
 * @author Sources: none.
 */
public class MainGUI
{

    private JFrame frame;
    public static JTextField startMonth;
    public static JTextField startDay;
    public static JTextField year;
    public static JTextField startTime;
    public static String startHour;
    public static JTextField startAMPM;
    public static JTextField endTime;
    public static String endHour;
    public static JTextField endAMPM;
    public static JTextField endMonth;
    public static JTextField endDay;
    private JTextField length;
    private JLabel lblEarliest;
    private JTextField earliest;
    public static Manage newActivity;
    public static JTextField startMin;
    private JLabel colon2;
    public static JTextField endMin;
    private JLabel errorMSG = new JLabel("");


    /**
     * Launch the application.
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                try
                {
                    MainGUI window = new MainGUI();
                    window.frame.setVisible( true );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        } );
    }


    /**
     * Create the application.
     */
    public MainGUI()
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
        frame.getContentPane().setLayout(null);
        
        startMonth = new JTextField();
        startMonth.setBounds(143, 21, 67, 26);
        frame.getContentPane().add(startMonth);
        startMonth.setColumns(10);
        
        startDay = new JTextField();
        startDay.setBounds(428, 21, 67, 26);
        frame.getContentPane().add(startDay);
        startDay.setColumns(10);
        
        year = new JTextField();
        year.setBounds(143, 98, 67, 26);
        frame.getContentPane().add(year);
        year.setColumns(10);
        
        startTime = new JTextField();
        startTime.setBounds(143, 136, 32, 26);
        frame.getContentPane().add(startTime);
        startTime.setColumns(10);
        
        startAMPM = new JTextField();
        startAMPM.setBounds(428, 136, 67, 26);
        frame.getContentPane().add(startAMPM);
        startAMPM.setColumns(10);
        
        endTime = new JTextField();
        endTime.setBounds(143, 174, 32, 26);
        frame.getContentPane().add(endTime);
        endTime.setColumns(10);
        
        endAMPM = new JTextField();
        endAMPM.setBounds(428, 174, 67, 26);
        frame.getContentPane().add(endAMPM);
        endAMPM.setColumns(10);
        
        endMonth = new JTextField();
        endMonth.setBounds(143, 59, 67, 26);
        frame.getContentPane().add(endMonth);
        endMonth.setColumns(10);
        
        endDay = new JTextField();
        endDay.setBounds(428, 59, 67, 26);
        frame.getContentPane().add(endDay);
        endDay.setColumns(10);
        
        length = new JTextField();
        length.setBounds(490, 98, 67, 26);
        frame.getContentPane().add(length);
        length.setColumns(10);
        
        errorMSG.setBounds(25, 238, 424, 16);
        frame.getContentPane().add(errorMSG);
        
        JButton btnCreateEvent = new JButton("Create Event");
        btnCreateEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                
                if ( ( startMin.getText().equals( "0" ) || startMin.getText().equals( "00" ) || startMin.getText().equals( "15" ) || startMin.getText().equals( "30" ) || startMin.getText().equals( "45" ) ) && ( endMin.getText().equals( "0" ) || endMin.getText().equals( "00" ) || endMin.getText().equals( "15" ) || endMin.getText().equals( "30" ) || endMin.getText().equals( "45" ) ) )
                {
                    
                    addPersonGUI addPerson = new addPersonGUI();
                    addPerson.frame.setVisible( true );
                    frame.setVisible( false );
                    
                    startHour = startTime.getText();
                    endHour = endTime.getText();
                    
                    if ( startMin.getText().equals("15") )
                    {
                        startTime.setText( startTime.getText() + ".25" );
                    }
                    else if ( startMin.getText().equals("30") )
                    {
                        startTime.setText( startTime.getText() + ".50" );
                    }
                    else if ( startMin.getText().equals("45") )
                    {
                        startTime.setText( startTime.getText() + ".75" );
                    }
                    
                    if ( endMin.getText().equals("15") )
                    {
                        endTime.setText( endTime.getText() + ".25" );
                    }
                    else if ( endMin.getText().equals("30") )
                    {
                        endTime.setText( endTime.getText() + ".50" );
                    }
                    else if ( startMin.getText().equals("45") )
                    {
                        endTime.setText( endTime.getText() + ".75" );
                    }   
                    
                    
                    boolean isEarly = true;
                    if ( earliest.getText().toLowerCase().equals( "yes" ))
                    {
                        isEarly = true;
                    }
                    else
                    {
                        isEarly = false;
                    }
                    
                    convertTextMonthToNumber();
                    
                    newActivity = new Manage( convertRawDateTo365( Integer.parseInt( startMonth.getText() ), Integer.parseInt( startDay.getText() ), Integer.parseInt( year.getText() ) ), 
                        convertRawDateTo365( Integer.parseInt( endMonth.getText() ), Integer.parseInt( endDay.getText() ), Integer.parseInt( year.getText() ) ), 
                        convertRawTimeToMilitaryTime( Double.parseDouble( startTime.getText() ), startAMPM.getText() ), 
                        convertRawTimeToMilitaryTime( Double.parseDouble( endTime.getText() ), endAMPM.getText() ), Double.parseDouble( length.getText() ), Integer.parseInt( year.getText() ), isEarly );
                }
                else
                {
                    errorMSG.setText("Starting and ending min must be 0 or intervals of 15 minutes");

                    errorMSG.setForeground( Color.RED );

                }
      
                
            }
        });
        btnCreateEvent.setBounds(477, 238, 117, 29);
        frame.getContentPane().add(btnCreateEvent);
        
        JLabel lblStartDay = new JLabel("Start Month");
        lblStartDay.setBounds(25, 25, 93, 20);
        frame.getContentPane().add(lblStartDay);
        
        JLabel lblStartDay_1 = new JLabel("Start Day");
        lblStartDay_1.setBounds(309, 27, 57, 14);
        frame.getContentPane().add(lblStartDay_1);
        
        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(25, 102, 46, 14);
        frame.getContentPane().add(lblYear);
        
        JLabel lblEndMonth = new JLabel("End Month");
        lblEndMonth.setBounds(25, 65, 69, 14);
        frame.getContentPane().add(lblEndMonth);
        
        JLabel lblEndDay = new JLabel("End Day");
        lblEndDay.setBounds(309, 65, 67, 14);
        frame.getContentPane().add(lblEndDay);
        
        JLabel lblStartTime = new JLabel("Start Time");
        lblStartTime.setBounds(25, 142, 68, 14);
        frame.getContentPane().add(lblStartTime);
        
        JLabel lblEndTime = new JLabel("End Time");
        lblEndTime.setBounds(25, 180, 66, 14);
        frame.getContentPane().add(lblEndTime);
        
        JLabel lblAmpm = new JLabel("AM/PM");
        lblAmpm.setBounds(307, 142, 46, 14);
        frame.getContentPane().add(lblAmpm);
        
        JLabel lblAmpm_1 = new JLabel("AM/PM");
        lblAmpm_1.setBounds(307, 180, 46, 14);
        frame.getContentPane().add(lblAmpm_1);
        
        JLabel lblLengthOfEvent = new JLabel("Length of Event (Every 15 min = .25)");
        lblLengthOfEvent.setBounds(226, 104, 252, 14);
        frame.getContentPane().add(lblLengthOfEvent);
        
        lblEarliest = new JLabel("Sort by Earliest? (Yes/No)");
        lblEarliest.setBounds(25, 212, 185, 14);
        frame.getContentPane().add(lblEarliest);
        
        earliest = new JTextField();
        earliest.setBounds(226, 209, 69, 20);
        frame.getContentPane().add(earliest);
        earliest.setColumns(10);
        
        startMin = new JTextField();
        startMin.setBounds(187, 136, 32, 26);
        frame.getContentPane().add(startMin);
        startMin.setColumns(10);
        
        JLabel colon = new JLabel("<html><b>:</b></html>");
        colon.setBounds(179, 141, 11, 16);
        frame.getContentPane().add(colon);
        
        colon2 = new JLabel("<html><b>:</b></html>");
        colon2.setBounds(179, 179, 11, 16);
        frame.getContentPane().add(colon2);
        
        endMin = new JTextField();
        endMin.setColumns(10);
        endMin.setBounds(187, 174, 32, 26);
        frame.getContentPane().add(endMin);
        
        

    }
    
    private void convertTextMonthToNumber()
    {
        if ( startMonth.getText().toLowerCase().equals( "january" ) )
        {
            startMonth.setText( "1" );
        }
        else if ( startMonth.getText().toLowerCase().equals( "february" ) )
        {
            startMonth.setText( "2" );
        }
        else if ( startMonth.getText().toLowerCase().equals( "march" ) )
        {
            startMonth.setText( "3" );
        }
        else if( startMonth.getText().toLowerCase().equals( "april" ) )
        {
            startMonth.setText( "4" );
        }
        else if( startMonth.getText().toLowerCase().equals( "may" ) )
        {
            startMonth.setText( "5" );
        }
        else if( startMonth.getText().toLowerCase().equals( "june" ) )
        {
            startMonth.setText( "6" );
        }
        else if( startMonth.getText().toLowerCase().equals( "july" ) )
        {
            startMonth.setText( "7" );
        }
        else if( startMonth.getText().toLowerCase().equals( "august" ) )
        {
            startMonth.setText( "8" );
        }
        else if( startMonth.getText().toLowerCase().equals( "september" ) )
        {
            startMonth.setText( "9" );
        }
        else if( startMonth.getText().toLowerCase().equals( "october" ) )
        {
            startMonth.setText( "10" );
        }
        else if( startMonth.getText().toLowerCase().equals( "november" ) )
        {
            startMonth.setText( "11" );
        }
        else if( startMonth.getText().toLowerCase().equals( "december" ) )
        {
            startMonth.setText( "12" );
        }
        
        if ( endMonth.getText().toLowerCase().equals( "january" ) )
        {
            endMonth.setText( "1" );
        }
        else if ( endMonth.getText().toLowerCase().equals( "february" ) )
        {
            endMonth.setText( "2" );
        }
        else if ( endMonth.getText().toLowerCase().equals( "march" ) )
        {
            endMonth.setText( "3" );
        }
        else if( endMonth.getText().toLowerCase().equals( "april" ) )
        {
            endMonth.setText( "4" );
        }
        else if( endMonth.getText().toLowerCase().equals( "may" ) )
        {
            endMonth.setText( "5" );
        }
        else if( endMonth.getText().toLowerCase().equals( "june" ) )
        {
            endMonth.setText( "6" );
        }
        else if( endMonth.getText().toLowerCase().equals( "july" ) )
        {
            endMonth.setText( "7" );
        }
        else if( endMonth.getText().toLowerCase().equals( "august" ) )
        {
            endMonth.setText( "8" );
        }
        else if( endMonth.getText().toLowerCase().equals( "september" ) )
        {
            endMonth.setText( "9" );
        }
        else if( endMonth.getText().toLowerCase().equals( "october" ) )
        {
            endMonth.setText( "10" );
        }
        else if( endMonth.getText().toLowerCase().equals( "november" ) )
        {
            endMonth.setText( "11" );
        }
        else if( endMonth.getText().toLowerCase().equals( "december" ) )
        {
            endMonth.setText( "12" );
        }
    }
    
    private double convertRawTimeToMilitaryTime( double time, String amPM )
    {
        if ( amPM.toLowerCase().equals( "am" ) )
        {
            if ( time == 12.0 )
            {
                return 0;
            }
            return time;
        }
        else
        {
            if ( time == 12.0 )
            {
                return 12;
            }
            return 12 + time;
        }
    }
    
    
    private int convertRawDateTo365( int month, int day, int year )
    {
        int dayNumber = 0;
        int daysInFeb;
        
        if ( ( year % 400 == 0 ) || ( ( year % 4 == 0 ) && ( year % 100 != 0 ) ) )
        {
            daysInFeb = 29;
        }
        else
        {
            daysInFeb = 28;
        }
        
        if ( month == 1 )
        {
            dayNumber = day;
        }
        else if ( month == 2 )
        {
            dayNumber = 31 + day;
        }
        else if ( month == 3 )
        {
            dayNumber = 31 + daysInFeb + day;
        }
        else if ( month == 4 )
        {
            dayNumber = 31 + daysInFeb + 31 + day;
        }
        else if ( month == 5 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + day;
        }
        else if ( month == 6 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + day;
        }
        else if ( month == 7 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + day;
        }
        else if ( month == 8 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + 31 + day;
        }
        else if ( month == 9 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + 31 + 31 + day;
        }
        else if ( month == 10 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + 31 + 31 + 31 + day;
        }
        else if ( month == 11 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + day;
        }
        else if ( month == 12 )
        {
            dayNumber = 31 + daysInFeb + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + day;
        }
        
        return dayNumber;
    }
}
