import javax.swing.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * Instructions class class
 * 
 * Daniel Arakcheev
 * ICS4U0
 * Ms. Krasteva
 * */
public class Instructions extends JPanel implements ActionListener {
  JLabel background;
  public Instructions() {
    super(null);
    init();
  }
  
  public void init() {
    background = new JLabel(new ImageIcon("pyramids.png"));
    background.setBounds(0, 0, 1024, 576);
    this.add(background);
    
  }
  public void actionPerformed(ActionEvent evt) {}
}