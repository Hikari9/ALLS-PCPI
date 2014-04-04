import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class Exam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exam extends JFrame
{
    private Quiz curr;
    private Fonts font;
    private Data data, submit;
    private int i;
    private Container main;
    
    public Exam(Data x, Fonts y) throws IOException
    {
        main = this.getContentPane();
        data = x;
        font = y;
        i = 0;
        first = true;
        body = new JPanel();
        question = new JLabel();
        next = new JButton("Next");
        prev = new JButton("Back");
        submit = new Data();
        head = new JPanel();
        head.setBackground(font.bg);
        head.add(question, BorderLayout.CENTER);
        submit.setConnectionString("Student.txt");
        items = (ArrayList<Quiz>)x.getList();
        body.add(head,BorderLayout.NORTH);
        body.setLayout(new GridLayout(2,1));
        set(i);
        this.add(body,BorderLayout.CENTER);
        next.setSize(20,this.getHeight());
        prev.setSize(20,this.getHeight());
        //body.setSize(this.getWidth(),this.getHeight());
        body.setBackground(font.bg);
        main.add(next,BorderLayout.EAST);
        main.add(prev,BorderLayout.WEST);        
        next.addActionListener(new Shift());
        prev.addActionListener(new Shift());
        meh = this;
    }
    
    public void set(int x)
    {   
        if(!first)
        {
            body.remove(pane);
        }
        first = false;
        pane = new JPanel();
        curr = items.get(x);
        question.setText(curr.getQuestion());
        question.setForeground(font.color);
        question.setFont(font.getFont());
        pane.setBackground(font.bg);
        if(curr.getType() == 1)
        {
            MultChoice curr2 = (MultChoice)curr; 
            ArrayList<String> choices = (ArrayList<String>)curr2.getOptions();
            options = new ArrayList<JLabel>();
            pane.setBackground(font.bg);
            for(String y: choices)
            {
                options.add(new JLabel(y));
            }
            for(JLabel y: options)
            {
                y.setFont(font.getFont());
                y.setForeground(font.color);
                pane.add(y,BorderLayout.CENTER);
            }
            body.add(pane,BorderLayout.SOUTH);
        }
        else
        {
            scroll = new JScrollPane();
            essay = new JTextArea();
            essay.setRows(22);
            essay.setColumns(70);
            essay.setLineWrap(true);
            essay.setWrapStyleWord(true);
            //SpringLayout layout = new SpringLayout();
            //body.setLayout(layout);
            //layout.putConstraint(SpringLayout.WEST,essay,65,SpringLayout.WEST,body);
            //layout.putConstraint(SpringLayout.EAST,essay,50,SpringLayout.EAST,body);
            //layout.putConstraint(SpringLayout.SOUTH,essay,65,SpringLayout.SOUTH,body);
            //layout.putConstraint(SpringLayout.NORTH,essay,50,SpringLayout.SOUTH,question);
            //layout.putConstraint(SpringLayout.NORTH,question,15,SpringLayout.NORTH,body);
            essay.setBackground(font.bg);
            essay.setForeground(font.color);
            scroll.setViewportView(essay);
            pane.add(scroll);
            body.add(pane,BorderLayout.SOUTH);
        }
    }
    
    class Shift implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if(e.getSource().equals(prev) && i >= 0)
                {
                    i-=1;
                    meh.set(i);
                }
                else if(e.getSource().equals(next) && i < items.size())
                {
                    i+=1;
                    meh.set(i);
                }
            }
            catch(Exception g)
            {}
        }
    }
    
    private ArrayList<Quiz> items;
    private JButton next, prev;
    private ArrayList<JLabel> options;
    private JLabel question;
    private JTextArea essay;
    private JScrollPane scroll;
    private JPanel body, pane, head;
    private Exam meh;
    private boolean first;
}
