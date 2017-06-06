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

 JPanel characterPanel;

 int exitX;
 int exitY;

 int dir;
 int x;
 int y;
 int i;
 int speed = 5;
 int startDir;// 1-Up, 2-Right, 3-Down, 4-Left

 public GameSlide(Application app, String fileName, int startX, int startY, int exitX, int exitY, int startDir) {
  super(app);
  this.x = startX;
  this.y = startY;
  this.exitX = exitX;
  this.exitY = exitY;
  this.startDir = startDir;
  init(fileName);
 }

 public void init(String fileName) {
  background = new JLabel(new ImageIcon(fileName));
  background.setBounds(0, 0, 1024, 650);

  upPos = new JLabel(new ImageIcon("Images\\moveUp.png"));
  downPos = new JLabel(new ImageIcon("Images\\moveDown.png"));
  leftPos = new JLabel(new ImageIcon("Images\\moveLeft.png"));
  rightPos = new JLabel(new ImageIcon("Images\\moveRight.png"));
  upPos.setBounds(0, 0, 83, 83);
  downPos.setBounds(0, 0, 83, 83);
  leftPos.setBounds(0, 0, 83, 83);
  rightPos.setBounds(0, 0, 83, 83);

  characterPanel = new JPanel(null);
  characterPanel.add(upPos);
  characterPanel.add(downPos);
  characterPanel.add(leftPos);
  characterPanel.add(rightPos);
  characterPanel.setBounds(x, y, 83, 83);
  characterPanel.setBackground(new Color(0, 0, 0, 0));
  characterPanel.setVisible(true);

  this.add(characterPanel);

  // button --------------------------------------------------
  backButton = new JButton(new ImageIcon("Images\\back button.png"));
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
  addKeyListener(this);
 }

 public void activate() {
  turnCharacter(startDir);
  this.requestFocus();
 }

 public boolean isFocusable() {
  return true;
 }
 
 private void turnCharacter(int direction) {
  if (this.dir != direction) {
   downPos.setVisible(direction == 3);
   upPos.setVisible(direction == 1);
   rightPos.setVisible(direction == 2);
   leftPos.setVisible(direction == 4);
   repaint();

   this.dir = direction;
  }
 }

 // dir determines up down or left right (0 = up/down, 1 = left/right)
 // step determines when up and when down (for up/down movement) (step>0 =
 // downwards, step<0 = upwards)
 // and when right and when left (for left/right movement)
 // x and y are the starting position
 public void move(int dir) {
  // down
  if (dir == 3 && y < 450) {
   y += speed;
  }
  // up
  if (dir == 1 && y > 130) {
   y -= speed;
  }
  // right
  if (dir == 2 && x < 795) {
   x += speed;
  }
  // left
  if (dir == 4 && x > 150) {
   x -= speed;
  }
  characterPanel.setBounds(x, y, 83, 83);
 }

 public void actionPerformed(ActionEvent evt) {

 }

 public void keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
   startMove(3);
  }
  if (e.getKeyCode() == KeyEvent.VK_UP) {
   startMove(1);
  }
  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
   startMove(4);
  }
  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
   startMove(2);
  }
 }

 private void startMove(int direction) {
  turnCharacter(direction);
  move(direction);
  if (detectExit()){
   exit();
  }
  
 }

 private boolean detectExit() {
  return Math.abs(x-exitX) < 30 && Math.abs(y-exitY)<30;
 }
 
 private void exit(){
  if (app.currentSlideIndex == 9)
   app.goToPane(1);
  else
   app.goToPane(app.currentSlideIndex+1);
 }
 
 public void keyReleased(KeyEvent arg0) {
 }

 public void keyTyped(KeyEvent arg0) {
  // TODO Auto-generated method stub

 }

}