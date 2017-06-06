
public class Highscore {
	String name;
	int score;
	String scoreString;
	public Highscore (String name, int score){
		this.name = name;
		this.score = score;
		scoreString = score/60+"min "+score%60+"sec";
	}
}
