import java.util.*;


/**
 * This is a priority queue that sorts posEvents according to their comparable
 * method.
 *
 * @author Katherine S. Li
 * @version May 9, 2017
 * @author Period: 2
 * @author Assignment: TimeStamp
 *
 * @author Sources: none.
 */
public class PQResult
{

    private PriorityQueue<posEvent> pq;

    private LinkedList<posEvent> savedList;


    /**
     * Constructs a priority queue that will sort the posEvents.
     * 
     * @param list a list of posEvents to be sorted, which is returned by
     *            Manage.
     */
    public PQResult( LinkedList<posEvent> list )
    {

        savedList = list;
        pq = new PriorityQueue<posEvent>();

        for ( posEvent e : list )
        {
            pq.add( e );
        }
    }


    /**
     * 
     * Returns the next event at the top of the Priority Queue.
     * 
     * @return next most desirable event.
     */
    public posEvent getNextEvent()
    {
        return pq.poll();
    }


    /**
     * 
     * Counts the number of posEvents left in the priority queue and returns the
     * count.
     * 
     * @return number of events in the priority queue.
     */
    public int numberOfPosEventsLeft()
    {
        return pq.size();
    }


    /**
     * 
     * Returns the entire list of posEvents, all sorted in order. This will be
     * the final output to the user.
     * 
     * @return list of posEvents.
     */
    public ArrayList<posEvent> getEntireSortedList() // for testing purposes
    {

        ArrayList<posEvent> alist = new ArrayList<posEvent>();

        PQResult pq2 = new PQResult( savedList );

        while ( pq2.numberOfPosEventsLeft() > 0 )
        {
            alist.add( pq2.getNextEvent() );
        }

        return alist;

    }

}
