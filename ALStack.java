/***********************
Will implement a stack using ArrayList
**********************/

import java.util.ArrayList;

public class ALStack<T> implements Stack<T>{
    
    private ArrayList<T> _stack;
    private int _size;
    
    public ALStack(){
	_stack = new ArrayList<T> (42);
	_size = 0;
    }
    
    public ALStack(int size){
	_stack = new ArrayList<T> (size);
	_size = 0;
    }
    
    //Return true if this stack is empty, otherwise false.
    public boolean isEmpty(){
	return _size == 0;
    }

    //Return top element of stack without popping it.
    public T peek(){
	if (isEmpty()) return null;
	return _stack.get(_size-1);
    }
    
    //Pop and return top element of stack.
    public T pop(){
	if (isEmpty()) return null;
	T tmp = this.peek();
	_stack.set(_size-1,null);
	_size--;
	return tmp;
    }

    //Push an element onto top of this stack.
    public void push( T x ){
	_stack.add(x);
	_size++;
    }
    
    //main method for testing
    public static void main( String[] args ) {

	Stack<String> tastyStack = new ALStack<String>();

	tastyStack.push("aoo");
	tastyStack.push("boo");
	tastyStack.push("coo");
	tastyStack.push("doo");
	tastyStack.push("eoo");
	tastyStack.push("foo");
	tastyStack.push("goo");
	tastyStack.push("hoo");
	tastyStack.push("ioo");
	tastyStack.push("joo");
	tastyStack.push("coocoo");
	tastyStack.push("cachoo");

	//cachoo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//coocoo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//joo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//ioo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//hoo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//goo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//foo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//eoo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//doo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//coo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//boo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	//aoo
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );

	//stack empty by now; SOP(null)
	System.out.println( "peek: " + tastyStack.peek() );
	System.out.println( "pop: " + tastyStack.pop() );
	System.out.println( tastyStack.pop() );
    }

}//end class ALStack