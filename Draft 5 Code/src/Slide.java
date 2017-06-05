import javax.swing.JPanel;

public class Slide extends JPanel{
	Application app;

	  public Slide(Application app) 
	  {
	    super(null);
		this.app = app;
	  }
	  
	  public void activate (){};
	  public void deactivate(){};
	  public void reset(){};
}
