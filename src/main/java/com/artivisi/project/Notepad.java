import javax.swing.*; // for the main JFrame design
import java.awt.*;  // for the GUI stuff
import java.awt.event.*; // for the event handling
import java.io.*; // for writing to a file
import javax.swing.event.*; // for the main JFrame design

public class Notepad extends JFrame implements ActionListener,ItemListener,MenuListener,KeyListener
{
   FileOutputStream fos;
   FileInputStream fis;
   JFileChooser dialog;
   JOptionPane msgbox;
   boolean modified=false;
   String str;
   JMenuBar menubar;
   
   // berikut adalah baris properti untuk Menu
   JMenu file,edit,help;
   
   // berikut adalah baris properti untuk Menu Item
   JMenuItem newfile,savefile,openfile,exit,cut,copy,paste,about,selall;
   
   // berikut adalah baris properti untuk area text pada notepad
   JTextArea disp;
   // // berikut adalah baris properti untuk scrollpane
   JScrollPane scrlpane;
   
   public Notepad()
   {
       disp=new JTextArea();
       scrlpane=new JScrollPane(disp); //,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       menubar=new JMenuBar();
       dialog=new JFileChooser();
       msgbox=new JOptionPane();
       file=new JMenu("File"); file.setMnemonic('F');
       edit=new JMenu("Edit"); edit.setMnemonic('E');
       help=new JMenu("Help"); help.setMnemonic('H');
       newfile=new JMenuItem("New"); newfile.setMnemonic('N');
       openfile=new JMenuItem("Open"); openfile.setMnemonic('O');
       savefile=new JMenuItem("Save"); savefile.setMnemonic('S');
       exit=new JMenuItem("Exit"); exit.setMnemonic('x');
       cut=new JMenuItem("Cut"); cut.setMnemonic('C');
       copy=new JMenuItem("Copy"); copy.setMnemonic('o');
       paste=new JMenuItem("Paste"); paste.setMnemonic('P');
       selall=new JMenuItem("Select All"); selall.setMnemonic('A');
       about=new JMenuItem("About"); about.setMnemonic('A');
       cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK,true));
       copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK,true));
       paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK,true));
       openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK,true));
       savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,true));
       newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK,true));
       file.add(newfile);
       file.add(openfile);
       file.add(savefile);
       file.addSeparator();
       file.add(exit);
       edit.add(cut);
       edit.add(copy);
       edit.add(paste);
       edit.addSeparator();
       edit.add(selall);
       help.add(about);
       menubar.add(file);
       menubar.add(edit);
       menubar.add(help);
       Container c=getContentPane();
       getRootPane().setJMenuBar(menubar);
       c.add(scrlpane);
       setSize(500,500);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
       setTitle("Untitled - Notepad");
       savefile.addActionListener(this);
       openfile.addActionListener(this);
       exit.addActionListener(this);
       cut.addActionListener(this);
       copy.addActionListener(this);
       paste.addActionListener(this);
       selall.addActionListener(this);
       newfile.addActionListener(this);
       edit.addMenuListener(this);
       disp.addKeyListener(this);
   }
   
   public void keyPressed(KeyEvent e)
   {
       //System.out.println("Press");
   }
   public void keyReleased(KeyEvent e)
   {
       //System.out.println("Release");
   }
   public void keyTyped(KeyEvent e)
   {
       String str=KeyEvent.getKeyModifiersText(InputEvent.CTRL_MASK);
       if(!str.equals("Ctrl"))
           modified=true;
   }
    public void menuSelected(MenuEvent ml)
   {
       //System.out.println("Sele");
       if(ml.getSource()==edit)
       {
           if(disp.getSelectedText()==null)
           {
               cut.setEnabled(false);
               copy.setEnabled(false);
           }
           else
           {
               cut.setEnabled(true);
               copy.setEnabled(true);
           }
       }
   }
   public void menuDeselected(MenuEvent ml)
   {
       //System.out.println("DeSele");
   }
   public void menuCanceled(MenuEvent ml)
   {
       //System.out.println("Can");
   }
   public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==savefile)
       {
           if(getTitle().equals("Untitled - Notepad"))
               saveFile(0);
           else if(modified)
               saveFile(1);
       }
       else if(ae.getSource()==openfile)
       {
           int x;
           if(modified)
           {
               x=isModified();
               if(x==1)
               {
                   if(getTitle().equals("Untitled - Notepad"))
                       saveFile(0);
                   else
                       saveFile(1);
                   openFile();
               }
               else if(x!=3) openFile();
           }
           else openFile();
       }
       else if(ae.getSource()==exit)
       {
           int x;
           if(modified)
           {
               x=isModified();
               if(x==1)
               {
                   if(getTitle().equals("Untitled - Notepad"))
                       saveFile(0);
                   else
                       saveFile(1);
               }
               else if(x==2) System.exit(0);
           }
           else System.exit(0);
       }
       else if(ae.getSource()==newfile)
       {
           int x;
           if(modified)
           {
               x=isModified();
               if(x==1)
               {
                   if(getTitle().equals("Untitled - Notepad"))
                       saveFile(0);
                   else
                       saveFile(1);
               }
               else if(x!=3){
                   disp.setText("");
                   setTitle("Untitled - Notepad");
                   modified=false;
               }
           }
           else{
               disp.setText("");
               setTitle("Untitled - Notepad");
               modified=false;
           }
       }
       else if(ae.getSource()==cut)
       {
           disp.cut();
       }
       else if(ae.getSource()==copy)
       {
           disp.copy();
       }
       else if(ae.getSource()==paste)
       {
           disp.paste();
       }
       else if(ae.getSource()==selall)
       {
           disp.selectAll();
       }
   }
   public void saveFile(int saveflag)
   {
       File f;
       try
       {
           if(saveflag==0)
           {
               int x=dialog.showSaveDialog(this);
               if(x==0){
                   f=dialog.getSelectedFile();
                   fos=new FileOutputStream(f);
                   setTitle(f.getPath());
                   PrintStream ps=new PrintStream(fos);
                   ps.print(disp.getText());
                   modified=false;
               }
           }
           else
           {
               f=new File(getTitle());
               fos=new FileOutputStream(f);
               setTitle(f.getPath());
               PrintStream ps=new PrintStream(fos);
               ps.print(disp.getText());
               modified=false;
           }
       }catch(Exception e){}
   }
   public void openFile()
   {
       int x=dialog.showOpenDialog(this);
       if(x==0){
       try
       {
           File f=dialog.getSelectedFile();
           fis=new FileInputStream(f);
           setTitle(f.getPath());
           disp.setText("");
           BufferedReader br=new BufferedReader(new InputStreamReader(fis));
           while((str=br.readLine())!=null)
           disp.append(str+"\n");
           disp.replaceRange("",disp.getText().length()-1,disp.getText().length());
           modified=false;
       }catch(Exception e){}
       }
   }
   public int isModified()
   {
       int x=JOptionPane.showConfirmDialog(this, "The text in the "+getTitle()+" has changed\nDo you want to save it", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
       if(x==JOptionPane.YES_OPTION) return 1;
       else if(x==JOptionPane.NO_OPTION) return 2;
       else return 3;
   }
   public void itemStateChanged(ItemEvent ie){}
   public static void main(String[] args)
   {
       new Notepad();
   }
}
