import java.util.*;
import java.awt.*;
public class Fonts
{
    public static String[] face = {"Arial","Arial Black","Comic Sans MS","Courier New","Georgia","Impact","Lucida Console","Lucida Sans Unicode","Palantino Linotype","Tahoma","Times New Roman","Trebuchet MS","Verdana","Symbol","MS Sans Serif","MS Serif"};
    public String currFace;
    public int size, style;
    public Color color, bg;
    public Fonts(int x, int size, Color c, Color b)
    {
        this(x, size, Font.PLAIN, c, b);
    }
    
    public Fonts(int x, int y, int z, Color c, Color b)
    {
        currFace = face[x];
        size = y+25;
        style = z;
        color = c;
        bg = b;
    }
    
    public static String[] getList()
    {
        return face;
    }
    
    public static String getFace(int x)
    {
        return face[x];
    }
    
    public String getFace()
    {
        return currFace;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int getStyle()
    {
        return style;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setFace(String x)
    {
        currFace = x;
    }
    
    public void setFace(int x)
    {
        currFace = face[x];
    }
    
    public void setSize(int x)
    {
        size = x;
    }
    
    public void setStyle(int x)
    {
        style = x;
    }
    
    public void setColor(Color c)
    {
        color = c;
    }
    
    public Font getFont()
    {
        return new Font(currFace, style,size);
    }
}
