import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_sk;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
/**
 * This class is for the adding a new schedule for a person GUI
 *
 * @author David D. Kim
 * @version May 18, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and Katherine S. Li
 *
 * @author Sources: none.
 */
public class addScheduleGUI
{

    public JFrame frame;
    private JTextField month;
    private JTextField day;
    private JTextField startTime;
    private JTextField endTime;
    private JTextField startAMPM;
    private JTextField endAMPM;
    private JTextField year;
    private List<tD> schedule;
    private JTextField startMin;
    private JTextField endMin;
    private JLabel lblErrors = new JLabel("");


    /**
     * Create the application.
     */
    public addScheduleGUI()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        schedule = new ArrayList<tD>();
        frame = new JFrame();
        frame.setBounds( 100, 100, 620, 310 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout(null);
        
        JLabel lblMonthBusyavailable = new JLabel("Month Busy/Available");
        lblMonthBusyavailable.setBounds(23, 26, 163, 14);
        frame.getContentPane().add(lblMonthBusyavailable);
        
        JLabel lblDayBusyavailable = new JLabel("Day Busy/Available");
        lblDayBusyavailable.setBounds(23, 65, 136, 14);
        frame.getContentPane().add(lblDayBusyavailable);
        
        month = new JTextField();
        month.setBounds(229, 23, 46, 20);
        frame.getContentPane().add(month);
        month.setColumns(10);
        
        day = new JTextField();
        day.setBounds(229, 62, 46, 20);
        frame.getContentPane().add(day);
        day.setColumns(10);
        
        JLabel lblStartBusyavailableTime = new JLabel("Start Busy/Available Time");
        lblStartBusyavailableTime.setBounds(23, 108, 181, 14);
        frame.getContentPane().add(lblStartBusyavailableTime);
        
        startTime = new JTextField();
        startTime.setBounds(229, 105, 30, 20);
        frame.getContentPane().add(startTime);
        startTime.setColumns(10);
        
        JLabel lblEndBusyavailableTime = new JLabel("End Busy/Available Time");
        lblEndBusyavailableTime.setBounds(23, 151, 163, 14);
        frame.getContentPane().add(lblEndBusyavailableTime);
        
        endTime = new JTextField();
        endTime.setBounds(229, 148, 30, 20);
        frame.getContentPane().add(endTime);
        endTime.setColumns(10);
        
        JLabel lblAmpm = new JLabel("AM/PM");
        lblAmpm.setBounds(364, 108, 46, 14);
        frame.getContentPane().add(lblAmpm);
        
        startAMPM = new JTextField();
        startAMPM.setBounds(447, 105, 46, 20);
        frame.getContentPane().add(startAMPM);
        startAMPM.setColumns(10);
        
        JLabel lblAmpm_1 = new JLabel("AM/PM");
        lblAmpm_1.setBounds(364, 151, 46, 14);
        frame.getContentPane().add(lblAmpm_1);
        
        endAMPM = new JTextField();
        endAMPM.setBounds(447, 148, 46, 20);
        frame.getContentPane().add(endAMPM);
        endAMPM.setColumns(10);
        
        lblErrors.setBounds(23, 176, 540, 38);
        frame.getContentPane().add(lblErrors);
        
        JButton btnAddAnother = new JButton("Add Another Schedule");
        btnAddAnother.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                convertTextMonthToNumber();
                if ( ( startMin.getText().equals( "0" ) || startMin.getText().equals( "00" ) || startMin.getText().equals( "15" ) || startMin.getText().equals( "30" ) || startMin.getText().equals( "45" ) ) && ( endMin.getText().equals( "0" ) || endMin.getText().equals( "00" ) || endMin.getText().equals( "15" ) || endMin.getText().equals( "30" ) || endMin.getText().equals( "45" ) ) )
                {
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
                    
                    if ( convertRawDateTo365(Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) < convertRawDateTo365(Integer.parseInt( MainGUI.startMonth.getText() ), Integer.parseInt( MainGUI.startDay.getText() ), Integer.parseInt( MainGUI.year.getText() )) || convertRawDateTo365(Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) > convertRawDateTo365(Integer.parseInt( MainGUI.endMonth.getText() ), Integer.parseInt( MainGUI.endDay.getText() ), Integer.parseInt( MainGUI.year.getText() )) || convertRawTimeToMilitaryTime(Double.parseDouble( startTime.getText() ), startAMPM.getText() ) < convertRawTimeToMilitaryTime(Double.parseDouble( MainGUI.startTime.getText() ), MainGUI.startAMPM.getText()) || convertRawTimeToMilitaryTime(Double.parseDouble( endTime.getText() ), endAMPM.getText() ) > convertRawTimeToMilitaryTime(Double.parseDouble( MainGUI.endTime.getText() ), MainGUI.endAMPM.getText() ))
                    {

                        lblErrors.setText("Out of bounds! Must be within " + MainGUI.startMonth.getText() + "/" + MainGUI.startDay.getText() + "/" + MainGUI.year.getText() + " and " + MainGUI.endMonth.getText() + "/" + MainGUI.endDay.getText() + "/" + MainGUI.year.getText() + " and within " + MainGUI.startHour + ":" + MainGUI.startMin.getText() + " " +  MainGUI.startAMPM.getText() + " and " + MainGUI.endHour + ":" + MainGUI.endMin.getText() + " " + MainGUI.endAMPM.getText());
                        lblErrors.setForeground( Color.RED );
                    }
                    else
                    {
                        
                        schedule.add( new tD( convertRawTimeToMilitaryTime(Double.parseDouble( startTime.getText() ), startAMPM.getText()), convertRawTimeToMilitaryTime(Double.parseDouble( endTime.getText() ), endAMPM.getText()), convertRawDateTo365( Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) ) );
                        month.setText( "" );
                        day.setText( "" );
                        startTime.setText( "" );
                        endTime.setText( "" );
                        startAMPM.setText( "" );
                        endAMPM.setText( "" );
                        year.setText( "" );
                        startMin.setText( "" );
                        endMin.setText( "" );

                    }
                }
                else
                {
                    lblErrors.setText("Starting and ending min must be 0 or intervals of 15 minutes");
                    lblErrors.setForeground( Color.RED );
                }
                
                
            }
        });
        btnAddAnother.setBounds(6, 233, 212, 23);
        frame.getContentPane().add(btnAddAnother);
        
        JButton btnDone = new JButton("Done");
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                convertTextMonthToNumber();
                
                if ( ( startMin.getText().equals( "0" ) || startMin.getText().equals( "00" ) || startMin.getText().equals( "15" ) || startMin.getText().equals( "30" ) || startMin.getText().equals( "45" ) ) && ( endMin.getText().equals( "0" ) || endMin.getText().equals( "00" ) || endMin.getText().equals( "15" ) || endMin.getText().equals( "30" ) || endMin.getText().equals( "45" ) ) )
                {
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
                    
                    if ( convertRawDateTo365(Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) < convertRawDateTo365(Integer.parseInt( MainGUI.startMonth.getText() ), Integer.parseInt( MainGUI.startDay.getText() ), Integer.parseInt( MainGUI.year.getText() )) || convertRawDateTo365(Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) > convertRawDateTo365(Integer.parseInt( MainGUI.endMonth.getText() ), Integer.parseInt( MainGUI.endDay.getText() ), Integer.parseInt( MainGUI.year.getText() )) || convertRawTimeToMilitaryTime(Double.parseDouble( startTime.getText() ), startAMPM.getText() ) < convertRawTimeToMilitaryTime(Double.parseDouble( MainGUI.startTime.getText() ), MainGUI.startAMPM.getText()) || convertRawTimeToMilitaryTime(Double.parseDouble( endTime.getText() ), endAMPM.getText() ) > convertRawTimeToMilitaryTime(Double.parseDouble( MainGUI.endTime.getText() ), MainGUI.endAMPM.getText() ))
                    {

                        lblErrors.setText("Out of bounds! Must be within " + MainGUI.startMonth.getText() + "/" + MainGUI.startDay.getText() + "/" + MainGUI.year.getText() + " and " + MainGUI.endMonth.getText() + "/" + MainGUI.endDay.getText() + "/" + MainGUI.year.getText() + " and within " + MainGUI.startTime.getText() + " " + MainGUI.startAMPM.getText() + " and " + MainGUI.endTime.getText() + " " + MainGUI.endAMPM.getText());
                        lblErrors.setForeground( Color.RED );
                    }
                    else
                    {  
                        schedule.add( new tD( convertRawTimeToMilitaryTime(Double.parseDouble( startTime.getText() ), startAMPM.getText()), convertRawTimeToMilitaryTime(Double.parseDouble( endTime.getText() ), endAMPM.getText()), convertRawDateTo365( Integer.parseInt( month.getText() ), Integer.parseInt( day.getText() ), Integer.parseInt( year.getText() )) ) );
                        frame.setVisible( false );
                        
                        addPersonGUI.tempListOfSchedules.add( schedule );
                        for ( tD td : schedule )
                        {
                            addPersonGUI.allSchedules.setText( addPersonGUI.allSchedules.getText() + "\n" + td.toString() );
                        }

                    }
                }
                else
                {
                    lblErrors.setText("Starting and ending min must be 0 or intervals of 15 minutes");
                    lblErrors.setForeground( Color.RED );
                }
                
            }
        });
        btnDone.setBounds(483, 233, 89, 23);
        frame.getContentPane().add(btnDone);
        
        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(364, 65, 46, 14);
        frame.getContentPane().add(lblYear);
        
        year = new JTextField();
        year.setBounds(447, 62, 86, 20);
        frame.getContentPane().add(year);
        year.setColumns(10);
        
        JLabel colon = new JLabel("<html><b>:</b></html>");
        colon.setBounds(259, 107, 16, 16);
        frame.getContentPane().add(colon);
        
        JLabel colon2 = new JLabel("<html><b>:</b></html>");
        colon2.setBounds(259, 150, 16, 16);
        frame.getContentPane().add(colon2);
        
        startMin = new JTextField();
        startMin.setBounds(271, 102, 30, 26);
        frame.getContentPane().add(startMin);
        startMin.setColumns(10);
        
        endMin = new JTextField();
        endMin.setColumns(10);
        endMin.setBounds(271, 145, 30, 26);
        frame.getContentPane().add(endMin);
        
        
    }
    
    private void convertTextMonthToNumber()
    {
        if ( month.getText().toLowerCase().equals( "january" ) )
        {
            month.setText( "1" );
        }
        else if ( month.getText().toLowerCase().equals( "february" ) )
        {
            month.setText( "2" );
        }
        else if ( month.getText().toLowerCase().equals( "march" ) )
        {
            month.setText( "3" );
        }
        else if( month.getText().toLowerCase().equals( "april" ) )
        {
            month.setText( "4" );
        }
        else if( month.getText().toLowerCase().equals( "may" ) )
        {
            month.setText( "5" );
        }
        else if( month.getText().toLowerCase().equals( "june" ) )
        {
            month.setText( "6" );
        }
        else if( month.getText().toLowerCase().equals( "july" ) )
        {
            month.setText( "7" );
        }
        else if( month.getText().toLowerCase().equals( "august" ) )
        {
            month.setText( "8" );
        }
        else if( month.getText().toLowerCase().equals( "september" ) )
        {
            month.setText( "9" );
        }
        else if( month.getText().toLowerCase().equals( "october" ) )
        {
            month.setText( "10" );
        }
        else if( month.getText().toLowerCase().equals( "november" ) )
        {
            month.setText( "11" );
        }
        else if( month.getText().toLowerCase().equals( "december" ) )
        {
            month.setText( "12" );
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
