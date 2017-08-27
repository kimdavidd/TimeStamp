import java.io.Serializable;


/**
 * This is a time-date class, which represents a time, date, and length for
 * which a person is busy/available. These tDobjects will be added as a list,
 * together forming a person's schedule.
 *
 * @author Adhiv Dhar
 * @version May 19, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp
 *
 * @author Sources: none.
 */
public class tD implements Serializable
{
    private double startTime;

    private double endTime;

    private int dayOfYear;


    /**
     * Constructs a tD object.
     * 
     * @param start
     *            start time of event
     * @param end
     *            end time of event
     * @param day
     *            day of year of event (in the numerical 365 system).
     */
    public tD( double start, double end, int day )
    {
        startTime = start;
        endTime = end;
        dayOfYear = day;
    }


    /**
     * Accessor method for start time
     * 
     * @return start time of event.
     */
    public double getStartTime()
    {
        return startTime;
    }


    /**
     * 
     * Accessor method for end time.
     * 
     * @return end time of event.
     */
    public double getEndTime()
    {
        return endTime;
    }


    /**
     * 
     * Accessor method for date of event (day of the year).
     * 
     * @return event date (out of 365).
     */
    public int getDay()
    {
        return dayOfYear;
    }


    /**
     * 
     * Converts a double time to a time using the traditional colon and AM/PM
     * format.
     * 
     * @param time
     *            time in double format
     * @return time in traditional 00:00 AM/PM format
     */
    public String convertTime( double time )
    {
        // not sure if this is necessary for this class,
        // but code here for whoever implements it!

        String minutes = ":00";
        int adjTime = (int)time;

        if ( time % 1 != 0 )
        {
            double temp = time % 1;
            if ( temp == 0.25 )
            {
                minutes = ":15";
            }
            else if ( temp == 0.5 )
            {
                minutes = ":30";
            }
            else
            {
                minutes = ":45";
            }
        }

        if ( time >= 13 )
        {
            adjTime -= 12;
            return adjTime + minutes + "PM";
        }
        else
        {
            return adjTime + minutes + "AM";
        }
    }




    @Override
    /**
     * the print toString method for a tD object.
     */
    public String toString()
    {
        String realSt = convertTime( startTime );
        String realEnd = convertTime( endTime );

        String print = "Day: " + getDay() + " | Start:" + realSt + " | End:" + realEnd;

        return print;

    }

}
