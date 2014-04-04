import java.util.*;
import java.io.*;
import java.lang.*;
public class P11547
{
    public static void main(String arg[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int y = Integer.parseInt(br.readLine());
        for(int x = 0; x < y; x++)
        {
            long z = Long.parseLong(br.readLine());
            z = (((((((((z*567)/9)+7492))*235)/47)-498)%100)/10);
            if(z < 0)
            {
                z *= -1;
            }
            System.out.println(z);
        }
    }
    
}
