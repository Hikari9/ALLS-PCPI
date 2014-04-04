
import java.util.*;
import java.io.*;
// import alls.pcpi.*;

public class Test {
	public static void main( String[] args ) throws IOException {
		Data d = new Data();
		String[] x = {"hey","hey","you","you"};
		d.add( 1, "Question 1", "Ans 1" , 0);
		d.add( 2, "Question 2", "Ans 2" , 0);
		d.add( 3, "Question 3", "Ans 3" , 0);
		d.add( 4, "Question 4", "Ans 4" , 1, x);
		d.save();
	}
}