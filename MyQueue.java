import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private static int startSize = 50;
	
	public MyQueue() {
		this(startSize);
	}
	public MyQueue(int i) {
		startSize = i;
		this.queue = (T[]) new Object[startSize];
	    frontIndex = 0;
	    backIndex = 0;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if (backIndex == startSize)
			return true;	
		return false;
	}

	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) 
			throw new QueueUnderflowException();
		T x = queue[frontIndex];
		for(int i =frontIndex+1; i< backIndex;i++) {
			queue[i-1] = queue[i];
		}
		backIndex--;
		return x;
	}

	public int size() {
		return backIndex - frontIndex;
	}

	public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull())
            throw new QueueOverflowException();
        queue[backIndex] = e;
        backIndex++;
        return true;
    }
	public String toString() {
		String x = "";
		for (int i = frontIndex; i < backIndex; i++) {
			x += queue[i];
		}
		return x;
	}
	
	public String toString(String delimiter) {
		String s = "";
		 for (int i = frontIndex; i < backIndex; i++) {
	            s += queue[i];
	            if (i < backIndex-1) {
	                s += delimiter;
	            }
	        }
		return s;
	}

	public void fill(ArrayList<T> list) throws QueueOverflowException {
		if (isFull())
            throw new QueueOverflowException();
        for (T element : list) {
            enqueue(element);
        }		
	}

}
