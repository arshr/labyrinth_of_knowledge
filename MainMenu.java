import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/*
 * Main menu class
 * 
 * Arsh Raza
 * ICS4U0
 * Ms. Krasteva
 * */
public class MainMenu implements ActionListener{
      JFrame frame = new JFrame("Main Menu");
  public MainMenu()
  {
    Scanner sc = new Scanner(System.in);
    frame.setSize(1024,576);
    frame.setVisible(true);
    
    JButton playButton = new JButton("Play");
    JButton hsButton = new JButton("High Scores");
    JButton exitButton = new JButton("Exit");
    JButton inButton = new JButton("Instructions");
    
    JLabel mainMenuLabel= new JLabel("Main Menu");
    
    exitButton.addActionListener(this);
    
    


    
    JPanel pane = new JPanel(new FlowLayout());
    pane.add(mainMenuLabel);
    pane.add(playButton);
    pane.add(hsButton);
    pane.add(inButton);
    pane.add(exitButton);
    
    
    frame.add(pane); 
  }
  public void actionPerformed (ActionEvent ae)
  {
    
    if (ae.getActionCommand().equals("Exit"))
     {
       frame.dispose();
    }
  }
  public static void main(String[] args)
  {
       MainMenu mm = new MainMenu();
  }
   
}