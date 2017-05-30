import javax.swing.*;

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
public class Instructions extends JPanel {
 Application app;
  JLabel background;
  JButton backButton;
  
  public Instructions( Application app) {
    super(null);
    this.app = app;
    init();
  }
  
  public void init() {
    background = new JLabel(new ImageIcon("images\\Instructions.png"));
    background.setBounds(0, 0, 1024, 650);
    
    
    backButton = new JButton (new ImageIcon("images\\back button.png"));
 backButton.setBounds(620, 410, 394, 225);
 backButton.setBackground(new Color (120,79,37));
 this.add(backButton);
 this.add(background);
 
 backButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     app.goToPane(1);
    }
 });
  
  }
}