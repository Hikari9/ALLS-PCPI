import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.KeyStroke.*;
import java.awt.event.KeyEvent.*;

class Demo extends JFrame
{
    public Demo()
    {
        Container main = this.getContentPane();
        main.setLayout(new BorderLayout() );
        setTitle("Quiz Generator");
        
        main2 = new JPanel();
        main2.setLayout( new GridLayout(2,3) );
        crag= true;
        color1 = Color.BLACK;
        color2 = Color.LIGHT_GRAY;
        data = new Data();
        spacer = new JLabel("                                                  \n");
        a = new JPanel();
        a1a = new JLabel("Question:");
        a1b = new JLabel("Answer:");
        a1c = new JLabel("Question Id:");
        a2a = new JTextField(14);
        a2b = new JTextField(14);
        a2c = new JTextField(14);
        a8a = new JComboBox<Integer>();
        b = new JPanel();
        b1a = new JLabel("Options (1 per line):");
        b3a = new JTextArea(10,13);
        b4a = new JScrollPane();
        c = new JPanel();
        c1a = new JLabel("Font");
        c1b = new JLabel("Size:");
        c1c = new JLabel("Face:");
        c1d = new JLabel("Sample Text");
        c7a = new JButton("Set on sample");
        c7b = new JButton("Select Text Color");
        c7c = new JButton("Select Background Color");
        c9a = new JComboBox<String>(Fonts.getList());
        c9b = new JComboBox<String>(styles);
        c8a = new JComboBox<Integer>(sizes);
        c10a = new JPanel();
        c10b = new JPanel();
        c10c = new JPanel();
        d = new JPanel();
        d1a = new JLabel("Select Question type:");
        d5a = new ButtonGroup();
        d6a = new JRadioButton("Multiple Choice");
        d6b = new JRadioButton("Essay");
        e = new JPanel();
        e1a = new JLabel("Path");
        e2a = new JTextField(data.getConnectionString(),17);
        e7a = new JButton("Load");
        e7b = new JButton("Save");
        e7c = new JButton("Submit");
        e7d = new JButton("Preview");
        f = new JPanel();
        
        a.setLayout( new FlowLayout(FlowLayout.LEFT) );
        b.setLayout( new FlowLayout(FlowLayout.LEFT) );
        c.setLayout( new FlowLayout(FlowLayout.LEFT) );
        d.setLayout( new FlowLayout(FlowLayout.LEFT) );
        e.setLayout( new FlowLayout(FlowLayout.LEFT) );
        f.setLayout( new FlowLayout(FlowLayout.LEFT) );
        
        menu = new JMenuBar();
        file = new JMenu();
        options = new JMenu();
        //help = new JMenu();
        fileNew = new JMenuItem();
        fileOpen = new JMenuItem();
        fileSave = new JMenuItem();
        optionsStyle = new JMenuItem();
        
        a.add(a1a);
        a.add(a2a);
        a.add(a1b);
        a.add(a2b);
        a.add(a1c);
        a.add(a2c);
        a.add(a8a);
        //a8a.setEditable(true);
        a8a.setSize(14,14);
        a8a.addActionListener(new IdSet());
        
        b.add(b1a);
        b4a.setViewportView(b3a);
        b.add(b4a);
        
        c.add(c1a);
        c.add(spacer);
        c.add(c1c);
        c.add(c9a);
        c.add(c1b);
        c.add(c8a);
        c.add(c9b);
        c.add(c7b);
        c.add(c10a);
        c.add(c7c);
        c.add(c10b);
        c.add(c7a);
        c10c.add(c1d);
        c10a.setSize(20,20);
        c10b.setSize(20,20);
        c10a.setBackground(color1);
        c10b.setBackground(color2);
        c7a.addActionListener(new Set());
        c7b.addActionListener(new Pallet());
        c7c.addActionListener(new Pallet());
        
        d.add(d1a);
        d5a.add(d6a);
        d5a.add(d6b);
        d6b.setSelected(true);
        d.add(d6a);
        d.add(d6b);
        d6a.addActionListener( new Radio());
        d6b.addActionListener( new Radio());
        
        e.add(e1a);
        e.add(e2a);
        e.add(e7a);
        e.add(e7b);
        e.add(e7c);
        e.add(e7d);
        e7a.addActionListener(new Load());
        e7b.addActionListener(new Save());
        e7c.addActionListener(new Submit());
        e7d.addActionListener(new Preview());
        
        f.add(c10c);
        
        main.add(menu, BorderLayout.NORTH);
        main.add(main2);
        main2.add(a);
        main2.add(b);
        main2.add(c);
        main2.add(d);
        main2.add(e);
        main2.add(f);
        
        file.setText("File");

        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        fileNew.setText("New Quizform");
        file.add(fileNew);

        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
        fileOpen.setText("Open Quizform");
        file.add(fileOpen);

        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        fileSave.setText("Save Quizform");
        file.add(fileSave);
        
        menu.add(file);

        options.setText("Options");
        menu.add(options);
        
        optionsStyle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
        optionsStyle.setText("Change Style");
        options.add(optionsStyle);
        
        fileSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser input = new JFileChooser();
                javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter(
                "Quizform", "qf");
                input.setFileFilter(filter);
                int result = input.showSaveDialog(new Container());
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    try
                    {
                        String fileName = (input.getCurrentDirectory().toString())+"/"+(input.getSelectedFile().getName());
                        data.save(fileName+".qf");
                        data.setConnectionString(fileName+".qf");
                    }
                    catch(FileNotFoundException meh)
                    {
                        
                    }
                    catch(UnsupportedEncodingException sigh)
                    {
                        
                    }
                    catch(IOException blargh)
                    {
                        
                    }
                } 
            }
        }
        );
        
        fileOpen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser input = new JFileChooser();
                javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter(
                "Quizform", "qf");
                input.setFileFilter(filter);
                int result = input.showOpenDialog(new Container());
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    String fileName = (input.getCurrentDirectory().toString())+"/"+(input.getSelectedFile().getName());
                    try
                    {
                        data = new Data(fileName);
                        a8a.removeAllItems();
                        ArrayList<Quiz> cake= (ArrayList<Quiz>)data.getList();
                        for(Quiz meh : cake)
                        {
                            a8a.addItem((Integer)meh.getId());
                        }
                    }
                    catch(IOException meh)
                    {
                        
                    }
                } 
            }
        }
        );
    }
    
    class Radio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if(d6b.isSelected())
                {
                    b3a.setEnabled(false);
                }
                else
                {
                    b3a.setEnabled(true);
                }
            }
            catch(Exception g)
            {}
        }
    }
    
    class Save implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String x = e2a.getText();
                if(x.equals(""))
                {
                    data.save();
                }
                else
                {
                    data.save(x);
                    data.setConnectionString(x);
                }
            }
            catch(Exception g)
            {}
        }
    }
    
    class Load implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String x = e2a.getText();
                data = new Data(x);
                a8a.removeAllItems();
                ArrayList<Quiz> cake= (ArrayList<Quiz>)data.getList();
                for(Quiz meh : cake)
                {
                    a8a.addItem((Integer)meh.getId());
                }
            }
            catch(IOException g)
            {}
        }
    }

    class Submit implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int id = Integer.parseInt(a2c.getText());
                String question = a2a.getText();
                String answer = a2b.getText();
                if(d6a.isSelected())
                {
                    String[] options = (b3a.getText()).split("\n");
                    data.add(id,question,answer,1,options);
                }
                else
                {
                    data.add(id,question,answer,0);
                }
                data.save();
                data.load();
            }
            catch(Exception g)
            {}
        }
    }
    
    class Preview implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                exam = new Exam(data, curry);
                exam.setSize(1300,700);
                exam.setVisible(true);
                exam.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            catch(Exception g)
            {}
        }
    }
    
    class IdSet implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if(crag)
                {
                    b3a.setText("");
                    int chi = (int)a8a.getSelectedItem();
                    Quiz meh = data.getQuizMap(chi);
                    a2a.setText(meh.getQuestion());
                    a2b.setText(meh.getAnswer());
                    a2c.setText(""+chi);
                    int blarg = meh.getType();
                    if(blarg == 1)
                    {
                        MultChoice bar = (MultChoice)meh;
                        String[] foo = new String[(bar.getOptions()).size()];
                        (bar.getOptions()).toArray(foo);
                        String lie = "";
                        for( String cav : foo)
                        {
                            lie += cav + "\n";
                        }
                        b3a.setText(lie);
                        d6a.doClick();
                    }
                    else
                    {
                        d6b.doClick();
                    }
                }
            }
            catch(Exception g)
            {}
        }
    }
    
    class Pallet implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if(e.getSource() == c7b)
                {
                    color1 = JColorChooser.showDialog(new JFrame(),"Colors",Color.BLACK);
                    c10a.setBackground(color1);
                }
                else if(e.getSource() == c7c)
                {
                    color2 = JColorChooser.showDialog(new JFrame(),"Colors",Color.BLACK);
                    c10b.setBackground(color2);
                }
            }
            catch(Exception g)
            {}
        }
    }
    
    class Set implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int ah = c9a.getSelectedIndex();
            int bi = 0;
            int chi = (int)c8a.getSelectedItem();
            switch (c9b.getSelectedIndex())
            {
                case 0: bi = Font.PLAIN;
                        break;
                case 1: bi = Font.BOLD;
                        break;
                case 2: bi = Font.ITALIC;
                        break;
                case 3: bi = Font.PLAIN;
                        break;
            }
            curry = new Fonts(ah,(chi*2)+10,bi ,color1,color2);
            c1d.setForeground(color1);
            c10c.setBackground(color2);
            c1d.setFont(curry.getFont());
        }
    }
    private Exam exam;
    private Data data;
    private Color color1, color2;
    private JPanel a,b,c,c10a,c10b,c10c,d,e,f,main2;
    private JComboBox<String> c9a, c9b;
    private JComboBox<Integer> a8a, c8a; 
    private JButton c7a,c7b,c7c,e7a,e7b,e7c,e7d;
    private JRadioButton d6a, d6b;
    private ButtonGroup d5a;
    private JScrollPane b4a;
    private JTextArea b3a;
    private JTextField a2a, a2b, a2c, c2a, c2b, e2a;
    private JLabel spacer, a1a, a1b,a1c, b1a, c1a, c1b, c1c, c1d, d1a, e1a;
    private Integer[] sizes = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    private String[] styles = {"Normal","Bold","Italic","Underline"};
    private Fonts curry;
    private boolean crag;
    private JMenuBar menu;
    private JMenu file, options, help;
    private JMenuItem fileNew, fileOpen, fileSave, optionsStyle;
}

