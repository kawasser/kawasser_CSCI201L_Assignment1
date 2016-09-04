package Jeopardy;

public class Team {
	
	private String name;
	private int score;
	private int bet;
	private boolean mistake=false;
	private boolean fj=false;
	
	public String get_name(){
		return name;
	}
	public void set_name(String nme){
		name= nme;
	}
	public void setBet(int i){
		bet= i;
	}
	public void set_mistake(boolean bl){
		mistake= bl;
	}
	public void set_fj(boolean bl){
		fj= bl;
	}
	public int get_score(){
		return score;
	}
	public void set_score(int num){
		score= num;
	}
	public boolean get_fj(){
		return fj;
	}
	public void set_bet(int b){
		bet= b;
	}
	public int get_bet(){
		return bet;
	}
	public boolean get_mistake(){
		return mistake;
	}
	
	Team(String nm){
		name=nm;
		score=0;
		bet = 0;
	}
}
