import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Instructions class class
 * 
 * Daniel Arakcheev
 * ICS4U0
 * Ms. Krasteva
 * */
public class GameSlide extends Slide implements ActionListener, KeyListener {
 JLabel background;
 JButton backButton;
 JLabel upPos;
 JLabel downPos;
 JLabel leftPos;
 JLabel rightPos;
 Timer timer;

 int dir;
 int step;
 int x;
 int y;
 int i;
 int speed = 5;
 
 
 public GameSlide(Application app) {
  super(app);
  init();
 }

 public void init() {
  background = new JLabel(new ImageIcon("room2.png"));
  background.setBounds(0, 0, 1024, 650);

  upPos = new JLabel(new ImageIcon("moveUp.png"));
  downPos = new JLabel(new ImageIcon("moveDown.png"));
  leftPos = new JLabel(new ImageIcon("moveLeft.png"));
  rightPos = new JLabel(new ImageIcon("moveRight.png"));

  this.add(upPos);
  this.add(downPos);
  this.add(leftPos);
  this.add(rightPos);

  // button --------------------------------------------------
  backButton = new JButton(new ImageIcon("back button.png"));
  backButton.setBounds(880, 5, 136, 80);
  backButton.setBackground(new Color(120, 79, 37));
  this.add(backButton);
  this.add(background);

  backButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    app.goToPane(1);
   }
  });
  // ------------------------------------------------------------
  move (0,10,0,0);
  addKeyListener(this);
 }
 
 public void activate (){
  this.requestFocus();
 }
 
 public boolean isFocusable() {
  return true;  
 }

 // dir determines up down or left right (0 = up/down, 1 = left/right)
 // step determines when up and when down (for up/down movement) (step>0 =
 // downwards, step<0 = upwards)
 // and when right and when left (for left/right movement)
 // x and y are the starting position
 public void move(int dir, int step, int x, int y) {
  this.dir = dir;
  this.step = step;
  this.x = x;
  this.y = y;
  this.i = 0;

  int delay = 5;
  timer = new Timer(delay, this);
  timer.setRepeats(true);
  timer.start();
 }
 
//Bounds 
//874x
//524y
 
 public void actionPerformed(ActionEvent evt) {
  //int xOriginal = x;
  //int yOriginal = y;
  // Up/down
  if (dir == 0) {
   // down
   if (step > 0) {
    downPos.setBounds(x, y + i, 330, 390);
    i+=speed;
    y+=speed;
    if (i == step || (y+83)>524)
     timer.stop();
   }
   // up
   else {
    upPos.setBounds(x, y + i, 330, 390);
    i-=speed;
    y-=speed;
    if (i == step || y<130)
     timer.stop();
   }
   // Left/right
  } else {
   // right
   if (step > 0) {
    rightPos.setBounds(x + i, y, 390, 330);
    i+=speed;
    x+=speed;
    if (i == step || (x+83)>874)
     timer.stop();
   }
   // left
   else {
    leftPos.setBounds(x + i, y, 390, 330);
    i-= speed;
    x-=speed;
    if (i == step || x<153)
     timer.stop();
   }
  }
 }

 public void keyPressed(KeyEvent e) {
     if(e.getKeyCode() == KeyEvent.VK_DOWN)
      move (0,speed,x,y);
     if(e.getKeyCode() == KeyEvent.VK_UP)
      move (0,-speed,x,y);
     if(e.getKeyCode()== KeyEvent.VK_LEFT)
      move (1,-speed,x,y);
     if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      move (1,speed,x,y);
    
 }

 public void keyReleased(KeyEvent arg0) {
     timer.stop();

 }
 
 public void keyTyped(KeyEvent arg0) {
  // TODO Auto-generated method stub
  
 }
 
 

}