import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Main menu class
 * 
 * Arsh Raza
 * ICS4U0
 * Ms. Krasteva
 * */

public class MainMenu extends JPanel {

   JLabel background;
  /**Array keeping all the button images*/
  JLabel[] buttons = new JLabel[]{new JLabel(new ImageIcon("images\\mainmenu_draft1.png"))};
  /**Array keeping track of button images of selected buttons*/
  JLabel[] selButtons = new JLabel[]{new JLabel(new ImageIcon("images\\mainmenu_draft1.png"))};
  public MainMenu() 
  {
    super(null);
   //background = new JLabel(new ImageIcon("pyramids.png"));
   //background.setBounds(0, 0, 1024, 576);
   buttons[0].setBounds(0,0,1024,576);
   draw(2);
  }
  public void draw(int selected)
  {
    this.add(buttons[0]);
    //this.add(background);
  }
  
  public static void main(String[] args) throws IOException
  {
       MainMenu mm = new MainMenu();
       JFrame fram = new JFrame();
       fram.add(mm);
       fram.setSize(1024,615);
       fram.setVisible(true);
      
       
  }
   
}