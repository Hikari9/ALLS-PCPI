// package alls.pcpi;

import java.util.*;
import java.io.*;

class Quiz implements Comparable<Quiz> {
    private int id;
    public int getId(){ return id; }
    public String question;
    public String getQuestion(){ return question; }
    public String answer;
    public String getAnswer(){ return answer; }
    public int type;
    public int getType(){ return type; };
    public List<String> options;
    public Quiz( int id, String question, String answer, int type){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( id ).append( "\n\n" );
        sb.append( type ).append( "\n\n" );
        sb.append( question ).append( "\n\n" );
        sb.append( answer ).append( "\n\n" );
        return sb.toString();
    }
    @Override
    public int compareTo( Quiz q ){
        int cmp = id - q.id;
        if( cmp == 0 ){
            cmp = question.compareTo( q.question );
            if( cmp == 0 ){
                cmp = answer.compareTo( q.answer );
            }
        }
        return cmp;
    }
    @Override
    public int hashCode(){
        return id;
    }
}

public class Data {
    
    private String connection = null;
    private List<Quiz> list;
    private Map<Integer, Quiz> map;
    private final static String defaultFileName = "Data.txt";
    
    public Data(){
        list = new ArrayList<Quiz>();
        map = new HashMap<Integer, Quiz>();
        this.connection = defaultFileName;
    }
    
    public Data( String connection ) throws IOException {
        this();
        this.connection = connection;
        load();
    }
    
    public void clear(){
        list.clear();
        map.clear();
    }
    
    public void load() throws IOException {
        load( this.connection );
    }
    
    public void load( String fileName ) throws IOException {
        
        BufferedReader br = new BufferedReader( new FileReader( fileName ) );
        String buffer = "";
        
        while( (buffer = br.readLine()) != null ){
        
            int id = Integer.parseInt( buffer.trim() );
            br.readLine();
            buffer = br.readLine();
            int type = Integer.parseInt( buffer.trim() );
            br.readLine();
            if( map.containsKey(id) ){
                //System.err.println( "Warning: duplicate Quiz id entries: #" + id );
            }
            StringBuilder question = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            
            while( (buffer = br.readLine() ) != null ){
                buffer = buffer.trim();
                if( buffer.length() == 0 ) break;
                if( question.length() != 0 ) question.append( '\n' );
                question.append( buffer );
            }
            
            while( (buffer = br.readLine()) != null ){
                buffer = buffer.trim();
                if( buffer.length() == 0 ) break;
                if( answer.length() != 0 ) answer.append( '\n' );
                answer.append( buffer );
            }
            if( !map.containsKey(id) )
            {
                if(type == 1)
                {
                    ArrayList<String> options = new ArrayList<String>();
                    while( (buffer = br.readLine()) != null ){
                        buffer = buffer.trim();
                        if( buffer.length() == 0 ) break;
                        options.add(buffer);
                    }
                    MultChoice q = new MultChoice( id, question.toString(), answer.toString(),type );
                    String[] carl = new String[options.size()];
                    options.toArray(carl);
                    q.addChoices(carl);
                    list.add(q);
                    map.put( id, q );
                }
                else
                {
                    Quiz q = new Quiz( id, question.toString(), answer.toString(),type );
                    list.add(q);
                    map.put( id, q );
                }
            }
        }
        
    }
    
    public void save() throws IOException {
        save( this.connection );
    }
    
    public void save( String fileName ) throws IOException {
        
        PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), false ) ) );
        Collections.sort( list );
        for( Quiz q : list ){
            pw.print( q.toString());
        }
                pw.close();
        
    }
    
    public void setConnectionString( String connection ){
        this.connection = connection;
    }
    
    public String getConnectionString(){
        return connection;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for( Quiz q : list ){
            sb.append( q.toString() );
        }
        return sb.toString();
    }
    
    public List<Quiz> getList() {
        return list;
    }
    
    public Map<Integer, Quiz> getMap() {
        return map;
    }
    
    public void add(int id, String question, String answer, int type, String[] options)
    {
        add(id,question,answer,type);
        Quiz q = map.get( id );
        q.options = Arrays.asList(options);
    }
    
    public void add( int id, String question, String answer, int type){
        if( map.containsKey( id ) ){
            Quiz q = map.get( id );
            q.question = question;
            q.answer = answer;
            q.type = type;
        }
        else{
            if(type == 1)
            {
                MultChoice q = new MultChoice(id, question, answer, type);
                list.add(q);
                map.put( q.getId(), q );
            }
            else
            {
                Quiz q = new Quiz(id, question, answer, type);
                list.add(q);
                map.put( q.getId(), q );
            }
        }
        
    }
    
    public Quiz getQuizMap(int x)
    {
        return map.get(x);
    }
    
    public Quiz getQuizList(int x)
    {
        return list.get(x);
    }
}
