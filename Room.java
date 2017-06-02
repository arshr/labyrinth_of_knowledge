import java.awt.*;
/*import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Room extends JPanel {
 Application app;
 int[] doors = new int[4];//0 is south , 1 is west, 2 is north, 3 is east
 boolean[] doorsVis = new boolean[4];
 JLabel floor;
 JLabel[] floorOptions = {new JLabel(new ImageIcon("Images\\floor.png")),new JLabel(new ImageIcon("Images\\stone floor.png")) };
 
 public Room() {
   floor = floorOptions[(int)(Math.random()*2)];
 }
 public void setDoor(int dir, int otherRoom)
 {
   doors[dir] = otherRoom;
 }
 public boolean checkDoor(int dir)
 {
  if(doors[dir]!=0)
    return true;
  return false;
 }

}

