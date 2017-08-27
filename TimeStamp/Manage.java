import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * The Manage class is in charge of creating the table and adding users to it based on the availability/busy times.
 * Also processes all the possible times into a Linked list of posEvents to pass on to the priority queue.
 *
 * @author David D. Kim
 * @version May 8, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and Katherine S. Li
 *
 * @author Sources: none.
 */
public class Manage
{

    private TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> days;
    private int startD;
    private int endD;
    private double startT;
    private double endT;
    private double actLength;
    private int year;
    private boolean isEarly;
    
    /**
     * 
     * The constructor creates the table of all the days and times and people that can be in each time for this program after the event manager specifies the fields.
     * @param startDay the start day of the time-frame the event has to happen in
     * @param endDay the end day of the time-frame of the event has to happen in
     * @param startTime the start time of the time-frame the event has to happen in
     * @param endTime the end time of the time-frame the event has to happen in
     * @param activityLength the length of the event
     * @param year the year when the event will take place in
     * @param isEarly Whether or not the results will also be ordered from earliest time (not day) or not
     */
    public Manage(int startDay, int endDay, double startTime, double endTime, double activityLength, int year, boolean isEarly ) // make sure startDay and endDay are converted beforehand to 365
    {
        days = new TreeMap<Integer, TreeMap<Double, LinkedList<Person>>>();
        startD = startDay;
        endD = endDay;
        startT = startTime;
        endT = endTime;
        actLength = activityLength;
        this.year = year;
        this.isEarly = isEarly;
        
        for ( int n = startDay; n <= endDay; n++ )
        {
            days.put( n, new TreeMap<Double, LinkedList<Person>>() );
            
            for ( double time = startTime; time <= endTime; time += 0.25 ) // make sure time is converted to 24 hour format beforehand
            {
                days.get( n ).put( time, new LinkedList<Person>() );
            }
        }
    }
    
    /**
     * 
     * Adds the availability of the person into the table, returns void
     * @param person the person being added into the table
     */
    public void addPerson( Person person )
    {
        List<tD> schedule = new ArrayList<tD>();
        schedule = person.getSchedule();

        if ( person.isBusy() ) // schedule is not available times
        {
            for ( int day = startD; day <= endD; day++ )
            {
                for ( double time = startT; time <= endT; time += 0.25 )
                {
                    days.get( day ).get( time ).add( person ); // adds the person to every day and time
                }
            }

            for ( tD busyTime : schedule )
            {
                double busyStartTime = busyTime.getStartTime();
                double busyEndTime = busyTime.getEndTime();
                int busyDay = busyTime.getDay();
//                System.out.println(busyStartTime);
//                System.out.println(busyEndTime);
//                System.out.println(busyStartTime - actLength);
                for ( double t = busyStartTime - actLength; t < busyEndTime; t += .25 )
                {
                    if ( t >= days.get( busyDay ).firstKey() )
                    {
                        days.get( busyDay ).get( t ).removeLast();
                    }   
                }
            }
        }
        else //schedule is for available times
        {
            for (tD availableTime: schedule )
            {
                double availableStartTime = availableTime.getStartTime();
                double availableEndTime = availableTime.getEndTime();
                int availableDay = availableTime.getDay();
                
                for ( double t = availableStartTime; t < availableEndTime; t += .25 )
                {
                    if ( t + actLength < availableEndTime )
                    {
                        days.get( availableDay ).get( t ).add( person );
                    }
                }
            }
        }
    }
    
    /**
     * 
     * Returns all the possible times the event can happen at with the number of people
     * @return availableTimes a linked list with all the possible times
     */
    public LinkedList<posEvent> returnAllPosEvents()
    {
        LinkedList<posEvent> availableTimes = new LinkedList<posEvent>();
        
        for ( int day = startD; day <= endD; day++ )
        {
            for ( double time = startT; time <= endT; time += 0.25 )
            {
                availableTimes.add( new posEvent( time, actLength, day, days.get( day ).get( time ), year, isEarly ));
            }
        }      
        
        return availableTimes;
    }
    
    /**
     * 
     * Returns the complete time table with all the days, times, and people for each time
     * @return days the complete time table
     */
    public TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> returnTable()
    {
        return days;
    }
    
    /**
     * the toString method used to test the constructor
     * @return string the constructor in string form
     */
    public String toString()
    {
        return "Start Day: " + startD + " End Day: " + endD + " Start Time: " + startT + " End Time: " + endT + " Activity Length: " + actLength + " Year: " + year + " isEarly: " + isEarly;
    }
    
    // for test purposes only
//    public static void main( String[] args )
//    {   
//        int startD = 1;
//        int endD = 10;
//        double startT = 10.25;
//        double endT = 16.25;
//        double actLength = 1;
//        int year = 2017;      
//        String name = "David";
//        
//        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year );
//        TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> table = newActivity.returnTable();
//        List<tD> schedule = new ArrayList<tD>();
//        schedule.add( new tD( 11.50, 12.50, 7 ));
//        Person David = new Person( name, true, schedule );
//        
//        
//        newActivity.addPerson( David );
//    }
    
    
}
