package Jeopardy;

import java.util.Vector;

public class Catergory {
	private Vector <Questions> q;
	private String name; 
	
	public Catergory(String nm) {
		name= nm;
		q = new Vector<Questions>();
	}

	public String getName(){
		return name;
	}

	public void seNamte(String n){
		name= n;
	}
	
	public void addQuestion(int pv, String question, String answer){
		Questions ne= new Questions(question, pv, answer);
		q.addElement(ne);
	}

	public Vector<Questions> getVec(){
		return q;
	}
}
