
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;
import java.awt.*; 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//door normal size = 110x74
public class PlayGame extends JPanel {
 Application app;
 final int numRooms = 6;
 Room[] rooms = new Room[numRooms+1];
 JLabel floor;
 JLabel doorNorth = new JLabel(new ImageIcon("Images\\door.png"));
 JLabel doorEast = new JLabel(new ImageIcon("Images\\doorEast.png"));
 JLabel doorWest = new JLabel(new ImageIcon("Images\\doorWest.png"));
 JLabel doorSouth = new JLabel(new ImageIcon("Images\\doorSouth.png"));
 /*public PlayGame(Application app) {
  super(null);
  this.app = app;
  setMap();

 }*/

 public PlayGame() {
   super(null);
  setMap();
  drawRoom(1);

 }
 
  public void drawRoom(int roomNum)
 {
   Room room = rooms[roomNum];
   floor = room.floor;
   floor.setBounds(0,0,1024,643);
   if(room.checkDoor(0))
   {
     doorSouth.setBounds(457,520,110,74);
     this.add(doorSouth);
   }
   if(room.checkDoor(1))
   {
     doorWest.setBounds(80,251,74,110);
     this.add(doorWest);
   }
   if(room.checkDoor(2))
   {
     doorNorth.setBounds(457,58,110,74);
     this.add(doorNorth);
   }
   if(room.checkDoor(3))
   {
     doorEast.setBounds(870,251,74,110);
     this.add(doorEast);
   }
   this.add(floor);
 }
  
 public void setMap()
 {
   int row = -1;
   
   try{
    BufferedReader input = new BufferedReader (new FileReader ("Map.txt")); 
    ArrayList<String> grid = new ArrayList<String>();
    while(true)
    {
      String temp = input.readLine();
      if(temp == null)
        break;
      grid.add(temp);
      row++;
      for(int col = 1; col<temp.length();col++)
      {
       if(temp.charAt(col)!='.')
       {
        int roomNum = temp.charAt(col)-'0'; 
        rooms[roomNum] = new Room();
        if(temp.charAt(col-1)!='.')
        {
         int otherRoom = temp.charAt(col-1)-'0';
         rooms[roomNum].setDoor(1,otherRoom);
         rooms[otherRoom].setDoor(3,roomNum);
        }
        if(row!=0&&grid.get(row-1).charAt(col)!='.')
        {
          int otherRoom = grid.get(row-1).charAt(col)-'0';
          System.out.println(roomNum+" "+otherRoom);
          rooms[roomNum].setDoor(2,otherRoom);
          rooms[otherRoom].setDoor(0,roomNum);
        }
        
       }
      }
    }
   }
   catch(IOException e)
   {
   }
 }
 public static void main(String[] args)
 {

     PlayGame pg = new PlayGame();
     JFrame frame = new JFrame();
     frame.setVisible(true);
     frame.setSize(1024,690);
     frame.add(pg);
 }

}

