import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a person, who has a schedule of busy/available times,
 * and is to be included in as many meetings their schedule allows.
 *
 * @author Adhiv Dhar
 * @version May 19, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp
 *
 * @author Sources: none.
 */
public class Person implements Serializable
{
    private String name;

    private boolean busyAv;

    private List<tD> timeAndDates;


    /**
     * Constructs a person object.
     * 
     * @param personName
     *            name of the person
     * @param busy
     *            whether they entered their busy schedule or available
     * @param tDs
     *            a list of the times in which they are busy/available.
     */
    public Person( String personName, boolean busy, List<tD> tDs )
    {
        name = personName;
        busyAv = busy;
        timeAndDates = new ArrayList<tD>( tDs );
    }


    /**
     * Constructs a person object without a list of tDs.
     * 
     * @param personName
     *            name of person
     * @param busy
     *            whether they entered their busy schedule or available
     */
    public Person( String personName, boolean busy )
    {
        name = personName;
        busyAv = busy;
        timeAndDates = new ArrayList<tD>();
    }


    /**
     * 
     * Accessor method for person name
     * 
     * @return person name
     */
    public String getName()
    {
        return name;
    }


    /**
     * 
     * Accessor method for whether person is busy
     * 
     * @return whether person's listed times are busy
     */
    public boolean isBusy()
    {
        // returns true if busy schedule, false if available schedule
        return busyAv;
    }


    /**
     * 
     * Accessor method for list of tDs that show when a person is busy/available
     * 
     * @return list of tDs
     */
    public List<tD> getSchedule()
    {
        return timeAndDates;
    }


    /**
     * Adds a busy/available event to a person's schedule.
     * 
     * @param event
     */
    public void addtD( tD event )
    {
        this.getSchedule().add( event );
    }


    @Override
    /**
     * the toString method for Person.
     */
    public String toString()
    {
        String n = name + "'s";
        if ( isBusy() )
        {
            n += " Busy Schedule:";
        }
        else
        {
            n += "\nAvailable Schedule:";
        }

        for ( tD ev : getSchedule() )
        {
            n += "\n" + ev;
        }

        return n;
    }

}
