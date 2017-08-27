import java.util.*;


/**
 * The posEvent class contains the time, date, year, and the number of people
 * attending the event. It is the final output whose toString method is directly
 * shown to the user.
 *
 * @author Katherine S. Li
 * @version May 8, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp Group Project. Teammates Adhiv Dhar and David
 *         Kim
 *
 * @author Sources: none.
 */
public class posEvent implements Comparable<posEvent>
{

    private double startTime;

    private double lengthOfEvent;

    private int date;

    private LinkedList<Person> attendingPeople;

    private int year;

    private boolean earliest;


    /**
     * Constructs a posEvent object that holds a "possible event." This contains
     * the start time of the event, event length, event day, the list of people
     * attending, the year, and whether the event should happen as early in the
     * day as possible.
     * 
     * @param start event start time
     * @param length event length
     * @param daydate of event
     * @param people the attendees
     * @param year1 year the event is happening
     * @param isEarly whether the event should happen as early in the day as
     *            possible.
     */
    public posEvent( double start, double length, int day, LinkedList<Person> people, int year1, boolean isEarly )
    {
        startTime = start;
        lengthOfEvent = length;
        date = day;
        attendingPeople = people;
        year = year1;
        earliest = isEarly;
    }


    // methods--all of them are accessor methods that will return the time in
    // DOUBLE (so, if you want it to return military/regular format time, you
    // have to convert from double)

    /**
     * 
     * Accessor method. Returns event start time.
     * 
     * @return event start time.
     */
    public double getStartTime()
    {
        return startTime;
    }


    /**
     * 
     * Accessor method. Returns event length.
     * 
     * @return event length
     */
    public double getLengthOfEvent()
    {
        return lengthOfEvent;
    }


    /**
     * 
     * Accessor method. Returns event date.
     * 
     * @return event date
     */
    public int getDay()
    {
        return date;
    }


    /**
     * 
     * Accessor method. Returns a list of the attendees who are available at
     * this time.
     * 
     * @return list of people attending.
     */
    public LinkedList<Person> getPeopleAttending()
    {
        return attendingPeople;
    }


    // methods that return formatted String times/dates. Used for printing.

    private String getPrintTime()
    {
        String ap = "am";
        double time = startTime;
        String printdouble;
        String minutes;
        String hour;

        if ( time >= 12.0 )
        {
            ap = "pm";
            time -= 12.0;
        }

        printdouble = Double.toString( time );
        int index = printdouble.indexOf( '.' );

        if ( printdouble.substring( 0, index ).equals( "0" ) )
        {
            hour = "12";
        }
        else
        {
            hour = printdouble.substring( 0, index );
        }

        String decimal = printdouble.substring( index + 1 );
        if ( decimal.equals( "0" ) )
        {
            minutes = "00";
        }
        else if ( decimal.equals( "25" ) )
        {
            minutes = "15";
        }
        else if ( decimal.equals( "5" ) )
        {
            minutes = "30";
        }
        else
        {
            minutes = "45";
        }

        return hour + ":" + minutes + ap;

    }


    private int getDaysInThisMonth( int month )
    {
        int daysInThisMonth;
        if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
        {
            daysInThisMonth = 31;
        }
        else if ( month == 2 )
        {

            if ( ( year % 400 == 0 ) || ( ( year % 4 == 0 ) && ( year % 100 != 0 ) ) )
            {
                daysInThisMonth = 29;
            }
            else
            {
                daysInThisMonth = 28;
            }
        }
        else
        {
            daysInThisMonth = 30;
        }

        return daysInThisMonth;
    }


    private String getPrintDay()
    {

        int d = date;

        int month = 1;

        while ( d > getDaysInThisMonth( month ) )
        {

            d -= getDaysInThisMonth( month );
            month++;
        }

        return month + "/" + d + "/" + year;

    }


    @Override
    /**
     * Overrides the toString method of Object. Returns the posEvent in printed
     * format.
     */
    public String toString()
    {
        String str = "";

        str += getPrintTime() + ", " + getPrintDay() + "\n" + attendingPeople.size() + " Attendees: ";
        for ( Person p : attendingPeople )
        {
            str += p.getName() + " ";
        }

        return str;
    }


    /**
     * Compares one posEvent to another. First orders by how many people can
     * attend, then by how early/late the event is (depending on whether the
     * user wants the event as early as possible or as late as possible).
     */
    public int compareTo( posEvent o2 ) // want the most desirable event to be
                                        // most negative
    {

        if ( this.getPeopleAttending().size() > o2.getPeopleAttending().size() )
        {

            return -1;
        }
        else if ( this.getPeopleAttending().size() < o2.getPeopleAttending().size() )
        {
            return 1;

        }
        else // both have same number of people attending
        {
            if ( earliest = true )
            {
                if ( this.getStartTime() < o2.getStartTime() )
                {
                    return -1;
                }
                else if ( this.getStartTime() > o2.getStartTime() )
                {

                    return 1;
                }
                else
                {

                    return 0;
                }
            }
            else
            {
                if ( this.getStartTime() < o2.getStartTime() )
                {
                    return 1;
                }
                else if ( this.getStartTime() > o2.getStartTime() )
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }
}
