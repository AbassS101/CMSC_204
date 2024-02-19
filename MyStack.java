import java.util.ArrayList;

public final class MyStack<T> implements StackInterface<T>{
	private T[] stack;
	private int topIndex;
	private static int startSize =50;
	
	public MyStack() {
		this(startSize);
	}
	public MyStack(int i) {
		startSize = i;
		T[] tempStack = (T[]) new Object[startSize];
		stack = tempStack;
	    topIndex = -1;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if (topIndex >= (startSize-1))
			return true;
		return false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		T top = stack[topIndex];
		stack[topIndex] = null;
		topIndex--;
		return top;
	}

	@Override
	public T top() throws StackUnderflowException {
		return stack[topIndex];
	}

	@Override
	public int size() {
		return topIndex + 1;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull())
	        throw new StackOverflowException();
	    stack[topIndex + 1] = e;
	    topIndex++;
	    return true;
	}
	public String toString() {
		String s = "";
		for(T x: stack) {
			if (x != null)
				s += x;
		}
		return s;
	}
	@Override
	public String toString(String delimiter) {
		String s = "";
		int counter = 0;
		for(T x: stack) {
			if (x != null) {
				s += x;
				if (counter < topIndex)
					s+= delimiter;
			}
			counter++;
		}
		return s;
	}
	

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		for (T element : list)
            push(element);
	}



}
