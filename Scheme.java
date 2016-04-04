/*
  Team Doughnut Cats - Andrea Ma, Dorothy Ng
  APCS2 pd10
  HW24 -- Schemin
  2016-04-03
*/

/*****************************************************
 * class Scheme
 * Simulates a rudimentary Scheme interpreter
 *
 * ALGORITHM for EVALUATING A SCHEME EXPRESSION:
      *
      * STACK OF CHOICE: ALStack by Dorothy Ng
      * b/c ...

1. Use a function similar to allMatched to determine the parts of each operation, 
with opening brackets starting the operation, and closing brackets ending the operation.

2. The characters inside the brackets are separated using whitespace,
and we will have a function to parse the code and determine which characters are useful,
and where each character separates from the next.

3. Once inside an opening bracket,
we will store the prefix operator to be used once we determine the operands.

4. After storing the operator, we will store the operands,
and rearrange the sequence so that the function can be performed in valid Java syntax.
      ******************************************************/
      
public class Scheme {

    /****************************************************** 
     * precond:  Assumes expr is a valid Scheme (prefix) expression,
     *           with whitespace separating all operators, parens, and 
     *           integer operands.
     * postcond: Returns the simplified value of the expression, as a String
     * eg,
     *           evaluate( "( + 4 3 )" ) -> 7
     *         evaluate( "( + 4 ( * 2 5 ) 3 )" ) -> 17
     ******************************************************/
    public static String evaluate( String expr ) 
    {
	if (isNumber(expr)) return expr;
	else {
	    ALStack<Integer> groupPos = findGroup(expr);
	    Integer end = groupPos.pop();
	    Integer start = groupPos.pop();
	    String group = expr.substring(start+2,end); //extract op and num only

	    //find op
	    int op = 0;
	    String opS = group.substring(0,1);
	    if (opS.equals("*")) op=3;
	    else if (opS.equals("-")) op=2;
	    else if (opS.equals("+")) op=1;

	    //find nums
	    ALStack<String> numbers = new ALStack<String>();
	    String num = "";
	    for (int i=2;i<group.length();i++){ //start from number
		if (group.substring(i,i+1).equals(" ")){
		    numbers.push(num);
		    num = "";
		}
		else {
		    num += group.substring(i,i+1);
		}
	    }

	    //evaluate the group
	    group = unload(op,numbers);
	    
	    //update Scheme operation
	    String ret = expr.substring(0,start);
	    ret += group;
	    ret += expr.substring(end+1,expr.length());
	    System.out.println(ret);
	    return evaluate(ret);
	}
    }//end evaluate()
    
    //Find the first innermost group
    public static ALStack<Integer> findGroup( String s )
    {
	ALStack<Integer> ret = new ALStack<Integer>(); //stores pos of brackets
        String bracket = "";
        ALStack<Integer> open = new ALStack<Integer>();
        for (int i=0; i<s.length(); i++) {
            bracket = s.substring(i,i+1);
            if (bracket.equals("(")) {
                open.push(i);
            }
            else if (bracket.equals(")")) {
		ret.push(open.pop());
		ret.push(i);
		return ret;
	    }
	}
        return ret;
    }//end allMatched()

    /****************************************************** 
     * precond:  Assumes top of input stack is a number.
     * postcond: Performs op on nums until closing paren is seen thru peek().
     *           Returns the result of operating on sequence of operands.
     *           Ops: + is 1, - is 2, * is 3
     ******************************************************/
    public static String unload( int op, ALStack<String>  numbers ) 
    {
	int result = 0;
        if (op == 3) {
	    Integer num = new Integer (numbers.pop());
	    result = num;
	    while (numbers.isEmpty()!=true){
                num = num.parseInt(numbers.pop());
		result *= num;
            }
        }
        else if (op == 2){
	    ALStack<String> rev = new ALStack<String> ();
	    while (numbers.isEmpty()!=true){
		rev.push(numbers.pop());
	    }
	    Integer num = new Integer (rev.pop());
	    result = num;
	    while (rev.isEmpty()!=true){ 
		num = num.parseInt(rev.pop());
		result -= num;
            }
        }
        else if (op == 1){
	    Integer num = new Integer (numbers.pop());
	    result = num;
	    while (numbers.isEmpty()!=true){
		num = num.parseInt(numbers.pop());
		result += num;
            }
        }
	return result+"";
    }//end unload()
    

    //optional check-to-see-if-its-a-number helper fxn:
    public static boolean isNumber( String s ) {
        try {
	    Integer.parseInt(s);
	        return true;
		}
        catch( NumberFormatException e ) {
	    return false;
	    }
    }

    //main method for testing
    public static void main( String[] args ) {

	  String zoo1 = "( + 4 3 )";
	  System.out.println(zoo1);
	  System.out.println("zoo1 eval'd: " + evaluate(zoo1) );
	  //...7

	  String zoo2 = "( + 4 ( * 2 5 ) 3 )";
	  System.out.println(zoo2);
	  System.out.println("zoo2 eval'd: " + evaluate(zoo2) );
	  //...17

	  String zoo3 = "( + 4 ( * 2 5 ) 6 3 ( - 56 50 ) )";
	  System.out.println(zoo3);
	  System.out.println("zoo3 eval'd: " + evaluate(zoo3) );
	  //...29

	  String zoo4 = "( - 1 2 3 )";
	  System.out.println(zoo4);
	  System.out.println("zoo4 eval'd: " + evaluate(zoo4) );
	  //...-4
	/*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v
          ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
    }//main

}//end class Scheme
