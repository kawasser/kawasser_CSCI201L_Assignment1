package Jeopardy;

public class Questions {
	private String question;
	private String ans;
	private int pointval;
	private boolean open=true;
	
	public String getQuestion(){
		return question;
	}
	public int getPointval(){
		return pointval;
	}
	public String getAns(){
		return ans;
	}
	
	public void setAnswer(String str){
		ans= str;
	}
	public void setQuestion(String str){
		question=str;
	}
	public void setPointval(int num){
		pointval=num;
	}
	public boolean getOpen(){
		return open;
	}
	public void setOpen(boolean tr){
		open= tr;
	}
	
	
	
	
	Questions(String q,  int pv, String answer){
		question= q;
		pointval= pv;
		ans= answer;
	}
}
