import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;
public class Application extends JFrame {

  static final String FILENAME = "highScores.txt";
  
 Slide[] panes = new Slide [] {new SplashScreen(this),
       new MainMenu(this),
       new Instructions(this),
       new Highscores(this),
       new PlayGame(this),
       new PickDifficulty(this),
   new TreasureSlide(this),
   new Gameover(this)
       };

 int currentSlideIndex;
 int difficulty; 
 int lives;
 
 long gameStartTime;
 long gameEndTime;
 long gameTime;
 String playerName;
 ArrayList<Highscore> highscores;
 
 JPanel mapPanel;
 JPanel dunPanel;
 
 Question question;
 
 public Application (){
  super("Labyrinth of Knowledge");
  highscores = new ArrayList<Highscore>();
  loadHighscores();
  
  for (int i = 0; i<panes.length;i++){
   if(i!=4)
   this.add(panes[i]);
   panes[i].setVisible(false);
  }
  currentSlideIndex = 0;


  this.setSize(1042,690);
  this.setVisible(true);
  goToPane(currentSlideIndex);
 }

  
 public void goToPane (int nextPaneIndex){
  Slide nextPane;
  if(nextPaneIndex==4)
  {
    panes[4] = new PlayGame(this);
    this.add(panes[4]);
  }
  panes [currentSlideIndex].setVisible(false);
  nextPane = panes [nextPaneIndex];
  nextPane.setVisible(true);
  nextPane.setSize(1024,650);
  nextPane.setBounds(0, 0, 1024, 690);
  nextPane.repaint();
  nextPane.activate();
  currentSlideIndex = nextPaneIndex;
    }
 
 public void loadHighscores ()
    {
        //LOAD HIGHSCORES FROM FILE
        try
        {
            BufferedReader input = new BufferedReader (new FileReader (FILENAME));
            while (true) {
    String name = input.readLine();
    if (name == null)
     break;
    String score = input.readLine();
    if (score == null)
     break;
    Highscore highscore = new Highscore(name,Integer.parseInt(score));
    this.highscores.add(highscore);
   }
         }
        catch (IOException e){}
    }

 public void saveHighscores() {
  // SAVES HIGHSCORE BACK INTO FILE
  try {
   PrintWriter output = new PrintWriter(new FileWriter(FILENAME));
   for (int i = 0; i < highscores.size(); i++) {
    Highscore highscore = highscores.get(i);
    output.println(highscore.name);
    output.println(highscore.score);
   }
   output.close();
  } catch (IOException e) {
  }
 }
 public static void main(String[] args)
 {
  Application a = new Application ();
 }
 
}
