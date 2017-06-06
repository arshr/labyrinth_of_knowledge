import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Trapdoor extends JPanel{
 JLabel closed;
 JLabel open;
 Question question;
 boolean isOpen = true;
 
 public Trapdoor (int x, int y, Question q){
  super (null);
  
  question =q;
  
  closed = new JLabel(new ImageIcon("Images\\trapDoorClosed.png"));
  closed.setBounds(0,0,51,51);
  closed.setVisible(false);
  this.add(closed);
  
  open = new JLabel(new ImageIcon("Images\\trapDoorOpen.png"));
  open.setBounds(0,0,51,80);
  open.setVisible(true);
  this.add(open);

  this.setBounds(x,y,51,80);
  
  this.setBackground(new Color(0,0,0,0));
 }
 
 public void enter (){
  closed.setVisible(true);
  open.setVisible(false);
  isOpen = false;
  repaint();
 }

 public void reset(){
  closed.setVisible(false);
  open.setVisible(true);
  isOpen = true;
  repaint();
 }
}
