import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 *
 * @author David D. Kim
 * @author Adhiv Dhar
 * @author Katherine S. Li
 * @version 5/2/17
 * @author TimeStamp JUTimeStamp
 * 
 *
 */
public class JUTimeStamp
{
    private int startD = 1;
    private int endD = 10;
    private double startT = 10.25;
    private double endT = 16.25;
    private double actLength = 1;
    private int year = 2017;
    private boolean isEarly = true;
    
    private String name = "David";
    
    /*********************************/
    // David's JUnit Tests.
    @Test
    public void ManageConstructorTest()
    {
        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year, isEarly );
        String toStr = newActivity.toString();

        assertNotNull( newActivity.returnTable() );
        assertTrue( "<< Invalid Manage Constructor >>", toStr.contains( "Start Day: " + startD + " End Day: " + endD + " Start Time: " + startT + " End Time: " + endT + " Activity Length: " + actLength + " Year: " + year + " isEarly: " + isEarly ));
    }

    @Test
    public void ManageAddPersonBusyTest()
    {
        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year, isEarly );
        TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> table = newActivity.returnTable();
        List<tD> schedule = new ArrayList<tD>();
        schedule.add( new tD( 11.50, 12.50, 7 ));
        Person David = new Person( name, true, schedule );
        
        newActivity.addPerson( David );
        
        assertTrue( "There is a person where he/she shouldnt be added here", table.get( 7 ).get( 11.5 - actLength ).isEmpty() );
        assertFalse( "A person should be added here", table.get( 5 ).get( 11.5 ).isEmpty() );
        
    }
    
    @Test
    public void ManageAddPersonAvailableTest()
    {
        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year, isEarly );
        TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> table = newActivity.returnTable();
        List<tD> schedule = new ArrayList<tD>();
        schedule.add( new tD( 11.50, 13, 7 ));
        Person David = new Person( name, false, schedule );
        
        newActivity.addPerson( David );
        
        assertFalse( "A person should be added here", table.get( 7 ).get( 11.5 ).isEmpty() );
        assertTrue( "No person should be here", table.get( 5 ).get( 11.5 ).isEmpty() );
        
    }
    
    @Test
    public void ManageAddPersonAvailableLessThanActLengthTest() // test the case when person is available for a time that is less than the activity length
    {
        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year, isEarly );
        TreeMap<Integer, TreeMap<Double, LinkedList<Person>>> table = newActivity.returnTable();
        List<tD> schedule = new ArrayList<tD>();
        schedule.add( new tD( 11.50, 12.25, 7 ));
        Person David = new Person( name, false, schedule );
        
        newActivity.addPerson( David );
        
        assertTrue( "There is a person where he/she shouldnt be added here", table.get( 7 ).get( 11.5 ).isEmpty() );
        assertTrue( "No person should be here", table.get( 5 ).get( 11.5 ).isEmpty() );
        
    }
    
    @Test
    public void ManageReturnAllPosEventsTest()
    {
        Manage newActivity = new Manage( startD, endD, startT, endT, actLength, year, isEarly );
        List<tD> schedule = new ArrayList<tD>();
        schedule.add( new tD( 11.50, 12.25, 7 ));
        Person David = new Person( name, false, schedule );
        
        newActivity.addPerson( David );
        
        assertFalse("Linked List is empty of all pos events", newActivity.returnAllPosEvents().isEmpty() );
    }
    
    /*********************************/
    // Katherine's JUnit Tests.

    // testing posEvent

    double st = 16.0; // Katherine's piano class

    double lgth = 1.5;

    int day = 130; // may 10th

    int yr = 2017;

    boolean early = true;

    LinkedList<tD> ASchedule = new LinkedList<tD>();

    LinkedList<tD> BSchedule = new LinkedList<tD>();

    LinkedList<tD> CSchedule = new LinkedList<tD>();

    Person A;

    Person B;

    Person C;

    LinkedList<Person> attendingPeople = new LinkedList<Person>();

    LinkedList<Person> NoCAttendingPeople = new LinkedList<Person>();


    @Test
    public void PosEventConstructorAndMethodsTest() // this all works.
    {

        ASchedule.add( new tD( 13.0, 14.0, 130 ) );
        BSchedule.add( new tD( 14.0, 15.0, 130 ) );
        CSchedule.add( new tD( 15.0, 16.0, 130 ) );

        A = new Person( "A", true, ASchedule );
        B = new Person( "B", true, BSchedule );
        C = new Person( "C", true, CSchedule );

        attendingPeople.add( A );
        attendingPeople.add( B );
        attendingPeople.add( C );

        NoCAttendingPeople.add( A );
        NoCAttendingPeople.add( B );

        posEvent event1 = new posEvent( st, lgth, day, attendingPeople, yr, early );
        // System.out.println(event1);
        assertEquals( "start time is not constructed properly", 16.0, event1.getStartTime(), 0.01 );
        assertEquals( "length is not constructed properly", 1.5, event1.getLengthOfEvent(), 0.01 );
        assertEquals( "date is not constructed properly", 130, event1.getDay() );
        assertNotNull( "People attending list is null even when it shouldn't be", event1.getPeopleAttending() );
        assertTrue( "posEvent print statement fail",
            event1.toString().contains( "4:00pm" ) && event1.toString().contains( "5/10/2017" )
                && event1.toString().contains( "3 Attendees: A B C" ) );

        posEvent event2 = new posEvent( 15.0, 1.5, 130, NoCAttendingPeople, 2017, true );

        assertEquals( "compareTo method incorrect", -1, event1.compareTo( event2 ) );

    }


    @Test
    public void PosEventCompareToTest() // all works.
    {
        ASchedule.add( new tD( 13.0, 14.0, 130 ) );
        BSchedule.add( new tD( 14.0, 15.0, 130 ) );
        CSchedule.add( new tD( 15.0, 16.0, 130 ) );

        A = new Person( "A", true, ASchedule );
        B = new Person( "B", true, BSchedule );
        C = new Person( "C", true, CSchedule );

        attendingPeople.add( A );
        attendingPeople.add( B );
        attendingPeople.add( C );

        NoCAttendingPeople.add( A );
        NoCAttendingPeople.add( B );

        posEvent compare1 = new posEvent( 0.0, 1.0, 130, attendingPeople, 2017, true );
        posEvent compare2 = new posEvent( 0.25, 1.0, 130, NoCAttendingPeople, 2017, true );
        posEvent compare3 = new posEvent( 0.5, 1.0, 130, attendingPeople, 2017, true );

        posEvent compare4 = new posEvent( 0.0, 1.0, 130, attendingPeople, 2017, false );
        posEvent compare5 = new posEvent( 0.5, 1.0, 130, attendingPeople, 2017, false );

        posEvent compare6 = new posEvent( 0.0, 1.0, 130, attendingPeople, 2017, false );

        assertEquals( "compareTo method does not work", -1, compare1.compareTo( compare2 ) );
        assertEquals( "compareTo method does not work", 1, compare2.compareTo( compare1 ) );
        assertEquals( "compareTo method does not work", -1, compare1.compareTo( compare3 ) );

        assertEquals( "compareTo method does not work", 1, compare5.compareTo( compare4 ) );
        assertEquals( "compareTo method does not work", 0, compare6.compareTo( compare4 ) );
    }

    // testing PQResult

    Manage m = new Manage( day, day, 13.0, 18.0, lgth, yr, true );


    @Test
    public void PQResultTestUsingManage() // all works.
    {

        ASchedule.add( new tD( 13.0, 14.0, 130 ) ); // busy from 1-2pm
        BSchedule.add( new tD( 14.0, 15.0, 130 ) ); // busy 2-3pm
        CSchedule.add( new tD( 15.0, 16.0, 130 ) ); // busy 3-4pm

        A = new Person( "A", true, ASchedule );
        B = new Person( "B", true, BSchedule );
        C = new Person( "C", true, CSchedule );

        m.addPerson( A );
        m.addPerson( B );
        m.addPerson( C );

        LinkedList<posEvent> toBeSortedList = m.returnAllPosEvents();

        PQResult pq = new PQResult( toBeSortedList );

        posEvent e = pq.getNextEvent();

        String str = e.toString();

        assertTrue( "posEvents printed in wrong order",
            str.contains( "4:00pm" ) && str.contains( "5/10/2017" ) && str.contains( "3 Attendees" )
                && str.contains( "A B C" ) );
        assertEquals( "pq size is incorrect", 20, pq.numberOfPosEventsLeft() );
        assertNotNull( "priority queue is not filled in correctly", pq.getEntireSortedList() );

        // List<posEvent> list = pq.getEntireSortedList();
        //
        // for ( posEvent a : list)
        // {
        // System.out.println( a );
        // }
        //

    }
    
    /*********************************/
    // Adhiv's JUnit Tests.

    private String nameA = "Brad";

    private String nameB = "Ley";

    private List<tD> schedule = new ArrayList<tD>();

    private tD event1 = new tD( 9, 11, 1 );

    private tD event2 = new tD( 14, 18, 2 );

    private tD event3 = new tD( 2, 6, 3 );
    
    private double sT = 9.5;
    private double eT = 14.5;
    private int doy = 5;


    @Test
    public void PersonConstructorTest()
    {
        schedule.add( event1 );
        schedule.add( event2 );
        schedule.add( event3 );

        Person test = new Person( nameA, true, schedule );
        String str = test.toString();
        String trueFormat = nameA + "'s" + " Busy Schedule:";
        for ( tD ev : schedule )
        {
            trueFormat += "\n" + ev;
        }

        assertNotNull( test );
        assertTrue( "Invalid Person Constructor", str.contains( trueFormat ) );
    }


    @Test
    public void PersonGetNameTest()
    {
        Person test = new Person( nameA, true, schedule );
        String str = test.toString();

        assertNotNull( test );
        assertTrue( "The correct name has not been returned", str.contains( "Brad" ) );
    }


    @Test
    public void PersonIsBusyTest()
    {
        Person test = new Person( nameA, true, schedule );
        String str = test.toString();
        boolean a = test.isBusy();

        Person test2 = new Person( nameB, false, schedule );
        String str2 = test2.toString();
        boolean b = test2.isBusy();


        assertNotNull( test );
        assertNotNull( test2 );
        assertTrue( a );
        assertFalse( b );
        assertTrue( "A busy or available schedule wasn't properly designated",
            str.contains( "Busy Schedule:" ) && str2.contains( "Available Schedule:" ) );
    }


    @Test
    public void PersonGetScheduleTest()
    {
        schedule.add( event1 );
        schedule.add( event2 );
        schedule.add( event3 );

        Person test = new Person( nameA, true, schedule );
        String str = test.getSchedule().toString();

        assertNotNull( test );
        assertTrue( "The schedule wasn't properly returned",
            str.contains( "Day: " + "1" + " | Start:" + "9:00AM" + " | End:" + "11:00AM")
            && str.contains("Day: " + "2" + " | Start:" + "2:00PM" + " | End:" + "6:00PM")
            && str.contains("Day: " + "3" + " | Start:" + "2:00AM" + " | End:" + "6:00AM"));
    }
    
    @Test
    public void PersonAddTDTest()
    {
        Person test = new Person( nameA, true);
        test.addtD( event1 );
        String str = test.toString();

        assertNotNull( test.getSchedule() );
        assertTrue( "The event wasn't properly added",
            str.contains( "Day: " + "1" + " | Start:" + "9:00AM" + " | End:" + "11:00AM" ));
        
        
    }
    
    @Test
    public void TDConstructorTest()
    {
        tD test = new tD(sT, eT, doy);
        String str = test.toString();

        assertNotNull( test );
        assertTrue( "The tD wasn't successfully constructed",
            str.contains( "Day: " + doy + " | Start:" + "9:30AM" + " | End:" + "2:30PM"));
    }
    
    @Test
    public void TDGetterMethodsTest()
    {
        tD test = new tD(sT, eT, doy);
        String startT = "" + test.getStartTime();
        String endT = "" + test.getEndTime();
        String day = "" + test.getDay();
        String str = test.toString();

        assertNotNull( test );
        assertTrue( "The day of year wasn't returned properly", str.contains( "Day: " + day ));
        assertTrue( "The start time wasn't returned properly", startT.equals( "9.5" ));
        assertTrue( "The end time wasn't returned properly", endT.equals( "14.5" ));
    }
    
    @Test
    public void TDConvertTimeTest()
    {
        tD test = new tD(sT, eT, doy);
        
        String startT = test.convertTime( test.getStartTime() );
        String endT = test.convertTime( test.getEndTime() );
        String str = test.toString();

        assertNotNull( test );
        assertTrue( "The start time wasn't returned properly", startT.equals( "9:30AM" ));
        assertTrue( "The end time wasn't returned properly", endT.equals( "2:30PM" ));
    }


    // Remove block comment below to run JUnit test in console
    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUTimeStamp.class );
    }


    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUTimeStamp" );
    }

}