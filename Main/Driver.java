import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

class Driver
{
	public static void main(String arg[]) throws Exception
	{
		JFrame f = new Demo();
        f.setSize( 600, 450 );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setVisible(true);
        f.setResizable(false);
	}
}