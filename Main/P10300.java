import java.util.*;
import java.io.*;
import java.lang.*;
public class P10300
{
    public static void main(String arg[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int y = Integer.parseInt(br.readLine());
        for(int x = 0; x < y; x++)
        {
            int b = Integer.parseInt(br.readLine());
            int i = 0;
            for(int a = 0; a < b; a++)
            {
                String[] c = (br.readLine()).split(" ");
                int p = Integer.parseInt(c[0]);
                int q = Integer.parseInt(c[1]);
                int r = Integer.parseInt(c[2]);
                double s = ((((double)p/(double)q)*(double)r)*(double)p);
                i += (int)s;
            }
            System.out.println(i);
        }
    }
    
}
