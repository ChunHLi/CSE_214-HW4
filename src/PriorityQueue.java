import java.util.*;

/**
 * @author Ritwik Banerjee
 */
public class PriorityQueue<T> implements Heap<T> {

    private transient int           size;
    private transient ArrayList<T>  queue;
    private transient Comparator<T> comparator;

    public PriorityQueue(Comparator<T> comparator){
        this.size = 0;
        this.queue = new ArrayList<T>();
        this.comparator = comparator;
    }

    public static <E> PriorityQueue<E> fromCollection(Collection<? extends E> c, Comparator<E> comparator) {
        PriorityQueue pQ = new PriorityQueue(comparator);
        for (Iterator iterator = c.iterator(); iterator.hasNext();){
            pQ.insert(iterator.next());
        }
        return pQ;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    public T findBest(){
        return queue.get(0);
    }

    private int compare(T i, T j){
        if (this.comparator == null) return ((Comparable<T>) i).compareTo(j);
        else return this.comparator.compare(i, j);
    }

    public void insert(T t){
        queue.add(t);
        size++;
        int position = size - 1;
        while (position > 0 && compare(queue.get(position),queue.get((position - 1)/2)) > 0){
            T temp = queue.get(position);
            queue.set(position, queue.get((position - 1)/2));
            position = (position - 1)/2;
            queue.set(position, temp);
        }
    }

    public T deleteBest(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T returnValue = findBest();
        queue.set(0, queue.remove(size - 1));
        size--;
        fixPriorityQueue();
        return returnValue;
    }

    public void fixPriorityQueue() {
        int position = 0;
        int childPosition;
        while (position * 2 + 1 < size){
            childPosition = position * 2 + 1;
            if (childPosition < size - 1 && compare(queue.get(childPosition + 1), queue.get(childPosition)) > 0){
                childPosition++;
            }
            if (compare(queue.get(position), queue.get(childPosition)) >= 0){
                return;
            }
            T temp = queue.get(position);
            queue.set(position, queue.get(childPosition));
            position = childPosition;
            queue.set(position, temp);
        }
    }

    public void clear(){
        queue.clear();
        size = 0;
    }
    @Override
    public int hashCode() {
        return queue.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        int i = 0;
        while (i < queue.size() - 1){
            returnString.append(queue.get(i).toString());
            returnString.append("\n");
            i++;
        }
        returnString.append(queue.get(i).toString());
        return returnString.toString();
    }
}