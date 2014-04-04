import java.util.*;
import java.io.*;

public class MultChoice extends Quiz
{
    
    public MultChoice(int id, String question, String answer, int type)
    {
        super(id,question,answer,type);
    }
    
    public void addChoices( String[] options )
    {
        this.options = new ArrayList<String>(Arrays.asList(options));
    }
    
    public List<String> getOptions()
    {
        return options;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( super.toString());
        for(String x: options)
        {
            sb.append(x).append("\n");
        }
        return sb.toString();
    }
}
