import java.io.*;
import java.util.*;

/*
 * Algorithm for creating random map layouts.
 * 
 * 
 * */
public class MapGenerator{
  final int WIDTH = 9;
  final int HEIGHT = 5;
  final int MAPWIDTH = 100;
  final int MAPHEIGHT = 100;
  char map[][] = new char[MAPWIDTH][MAPHEIGHT];
  

  int rooms;
  /*The doors of each room that are already occupied. Doors are either north,west,east,south.
   * The doors of the xth room are the doors of the (x*4) to (x*4+3) (inclusive) indices. 
   0 is south, 1 is west, 2 is north, 3 is east*/
  int doors[];
  /*Current rooms which are placed*/
  ArrayList<Integer> placed = new ArrayList<Integer>();
  /*Rooms which have not been placed on the map yet*/
  ArrayList<Integer> notPlaced = new ArrayList<Integer>();
  public MapGenerator(int r)
  {
    rooms = r;
    doors = new int[r*4+10];
    
    placed.add(1);
    for (int i =2;i<=r;i++)
      notPlaced.add(i);
    doors[4] = r+1;//do this because there cannot be any south door in the beginning room. change this to make south door
  }
  public void makeConnections()
  {
    while(notPlaced.size()>0)
    {
     int toPlace = notPlaced.remove((int)(Math.random()*notPlaced.size()));
     placed.add(toPlace);
     ArrayList<Integer> validDoors = new ArrayList<Integer>();
     int connectRoom;
     while(true)
     {
       connectRoom = placed.get((int)(Math.random()*placed.size()));
       for(int i = connectRoom*4;i<=(connectRoom*4+3);i++)
       {

         if(doors[i]==0)
           validDoors.add(i);
       }
       if(validDoors.size()!=0)
         break;
     }

     int door = validDoors.get((int)(Math.random()*validDoors.size()));
     //add connection from connectRoom to toPlace
     doors[door] = toPlace;
     //This changes the door value so there is a connection between toPlace to connectRoom
     door = door-connectRoom*4;
     door =toPlace*4+((door+2)%4);
     doors[door] = connectRoom;
    }
    
  }
  
  public void makeMap()
  {
    int room = 1;
    boolean vis[] = new boolean[rooms+1];
    for(int i = 0;i<=rooms;i++)
      vis[i]=false;
    makeMapDFS(vis,room,MAPWIDTH/2,MAPHEIGHT/2);

    
  }
  /*This method does a dfs search to create map.*/
  private void makeMapDFS(boolean vis[],int room, int x, int y)
  {
    System.out.print(room);
    if(vis[room])
      return;
    vis[room]=true;
    for(int r = y-2;r<=y+2;r++)
    {
      for(int c = x-4;c<=x+4;c++)
      {
        if(r==y-2||r==y+2)
          map[r][c]='#';
        else if(c==x-4||c==x+4)
          map[r][c]='#';
        else
          map[r][c]='.';
      }
    }
    for(int i=room*4;i<=room*4+3;i++)
    {
     if(doors[i]!=0&&doors[i]!=rooms+1&&!vis[doors[i]])
     {
       if(i-rooms*4==0){
        map[y+2][x]=' ';
        map[y+3][x] = ' ';
        makeMapDFS(vis,doors[i],x,y+5);
       }
      else if(i-room*4==1)
      {
       map[y][x-4] = ' ';
       map[y][x-5] = ' ';
       makeMapDFS(vis,doors[i],x-9,y);
      }
       else if(i-room*4==2)
       {
         map[y-2][x]=' ';
         map[y-3][x]=' ';
         makeMapDFS(vis,doors[i],x,y-5);
       }
      else
      {
        map[y][x+4]= ' ';
        map[y][x+5] = ' ';
        makeMapDFS(vis,doors[i],x+9,y);
      }
     }
    }
  }
  public void printMap()
  {
    for(int r = 0;r<MAPHEIGHT;r++)
    {
      for(int c = 0;c<MAPWIDTH;c++){

        System.out.print(map[r][c]);
      }
      System.out.println();
    }
  }
  public static void main(String[] args)
  {

    MapGenerator mg = new MapGenerator(10);
    mg.makeConnections();
        System.out.println("make connetions is done");
    mg.makeMap();
    mg.printMap();
  }
}