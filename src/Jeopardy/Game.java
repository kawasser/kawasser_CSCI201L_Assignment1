package Jeopardy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {
	
	private int c;
	private Vector<Team> t;
	private Catergory cat1;
	private Catergory cat2;
	private Catergory cat3;
	private Catergory cat4;
	private Catergory cat5;
	private boolean m=true;
	private Catergory fj;
	private Vector <Catergory> r;
	int pv1;		
	int pv2;
	int pv3;
	int pv4;
	int pv5;
	
	
	private void print_scores(){
		/*This function prints the current scores of all the teams
		*/
		
		System.out.println("Here are the updated scores: " );
		for (int i=0; i<t.size(); i++){
			System.out.print("_________________");
		}
		System.out.print("\n");
		for (int i=0; i<t.size(); i++){ //prints all the teams' names
			System.out.print("|"+ "\t"+ t.get(i).get_name() + "\t");
		}
		System.out.print("|"+ "\n");
		for (int i=0; i<t.size(); i++){
			System.out.print("_________________");
		}
		System.out.print("\n");
		for (int i=0; i<t.size(); i++){// prints all the teams' scores
			System.out.print("|"+ "\t"+ t.get(i).get_score() + "\t");
		}
		System.out.print("|"+ "\n");
		for(int i=0; i<t.size(); i++){
			System.out.print("_________________");
		}
		System.out.print("\n");
	}
	
	private boolean check_Qs(int i){
		/*This function checks the questions to make sure that the point values attatched to the questions are the 
		 *correct point values 
		
		*/
		
		boolean check1=false;
		boolean check2=false;
		boolean check3=false;
		boolean check4=false;
		boolean check5=false;
		
		for (int m=0; m<r.get(i).getVec().size(); m++){// this loop checks for every question in a category that their is
			if(r.get(i).getVec().get(m).getPointval()== pv1){//there's a question for each point value 
				check1=true;
			}
			else if(r.get(i).getVec().get(m).getPointval()== pv2){
				check2=true;
			}
			else if(r.get(i).getVec().get(m).getPointval()== pv3){
				check3=true;
			}
			else if(r.get(i).getVec().get(m).getPointval()== pv4){
				check4=true;
			}
			else if(r.get(i).getVec().get(m).getPointval()== pv5){
				check5=true;
			}
		}
		if (check1 && check2 && check3 && check4 && check5){
			return true;
		}
		else{
			return false;
		}
	}
	
	private String getcats(String str){
		/*This function returns the next category from the string that is the first line of the file
		 * 
		 */
		
		String cat="";
		int i=0;
		while (i<str.length() && str.charAt(i) != ':' ){//this loop gets the full category name
			cat = cat+ str.charAt(i);
			i++;

		}
		if (i<str.length() && str.charAt(i)!=':' && str.charAt(i+1)!=':'){// this makes sure there are two colons instead of 1
			m=false;
			return null;
		}
		c=i++;
		return cat;
		
	}
	private  int getpvs(String str){
		/*This method takes a string of the second line and returns a valid integer point value
		*/
		String cat="";
		int i=0;
		
		while (i<str.length() &&str.charAt(i) != ':'&&  Character.isDigit(str.charAt(i))){
			cat = cat+ str.charAt(i);
			i++;
		}//gets full point value
		if (i<str.length() && str.charAt(i)!=':' && str.charAt(i+1)!=':'){// checks to make sure there are two colons
			m=false;
			return 0;
		}
		c=c+i++;
		int pv=  Integer.parseInt(cat);
		if (pv<0){
			m=false;
		}
		
		return  pv;
	}

	private  void is_correct(String answer, int cat, int pv,  int teamnum){
		/*this method checks the inputtted answer to see if it was correct. it then updates scores and tells the user if 
		 * their answer was correct
		*/
		
		String correct_answer= r.get(cat).getVec().get(pv).getAns().toLowerCase();
		if (answer.equalsIgnoreCase( correct_answer )){// this is for if they got the answer right but didn't put it in the form of a question
			if (!t.get(teamnum).get_mistake()){//checks if they haven't yet made this mistake 
				System.out.println("Correct! Warning: your answer must be in the form of a question!");
				t.get(teamnum).set_score(t.get(teamnum).get_score() + r.get(cat).getVec().get(pv).getPointval());
				t.get(teamnum).set_mistake(true);
			}
			else{
				System.out.println("Incorrect.");
				t.get(teamnum).set_score(t.get(teamnum).get_score() -  r.get(cat).getVec().get(pv).getPointval());
			}
		}
		
		else if (answer.equalsIgnoreCase("who is " + correct_answer) || answer.equalsIgnoreCase( "who are " + correct_answer) || answer.equalsIgnoreCase( "what are " + correct_answer)|| answer.equalsIgnoreCase("what is " + correct_answer) ){
			System.out.println("Correct!");
			t.get(teamnum).set_score(t.get(teamnum).get_score() +  r.get(cat).getVec().get(pv).getPointval());
			
				}
		else if (answer.equalsIgnoreCase("where is " + correct_answer) ||answer.equalsIgnoreCase("where are " + correct_answer) ||answer.equalsIgnoreCase("when is " + correct_answer) ||answer.equalsIgnoreCase("when are " + correct_answer) ){
			
		}
		else{
			System.out.println("Incorrect.");
			t.get(teamnum).set_score(t.get(teamnum).get_score() -  r.get(cat).getVec().get(pv).getPointval());
		}
		
	
	}
	
	
	private boolean final_iscorrect(int index, String answer){
		/* This function checks to see if their final jeopardy answer is correct;
		*/
		
		String correct_answer= r.get(5).getVec().get(0).getAns().toLowerCase().replaceAll("\\s+","") ;
		if (answer.equalsIgnoreCase(correct_answer)){
			if (t.get(index).get_mistake()){
				return true;
			}
			else{
				return false;
			}
		}
		else if (answer.equalsIgnoreCase("where is " + correct_answer) || answer.equalsIgnoreCase( "where are " + correct_answer) || answer.equalsIgnoreCase( "when are " + correct_answer)|| answer.equalsIgnoreCase( "when is " + correct_answer)){
			return true;
		}
		else if (answer.equalsIgnoreCase("who is " + correct_answer) || answer.equalsIgnoreCase( "who are " + correct_answer) || answer.equalsIgnoreCase( "what are " + correct_answer)|| answer.equalsIgnoreCase("what is " + correct_answer) ){
			return true;
		}
		else{
			return false;
		}
	}
	

	private void reset(){
		/*
		 * This function resets all scores and sets mistake boolean back to false (which is there to check if they've
		 * already made the mistake of not answering as a question) and sets open boolean on all questions back to false 
		 * (which marks if the question has already been asked).
		*/
		
		for(int i=0; i<t.size(); i++){
			t.get(i).set_score(0);
			t.get(i).set_mistake(false);
		}
		for (int i=1; i<=5; i++){
			for (int m=0; m<r.get(i).getVec().size(); m++){
				r.get(i).getVec().get(m).setOpen(true);
			}
		}
	}
	
	
	private boolean parse_questions(Game g, String str, BufferedReader br){
		/*This takes a string of the question and splits it up into category, point value, question, and answer
		 * and also does error checks to make sure it's in the right format.
		*/
		
		String category="";
		String question="";
		String answer="";
		String pv="";
		int i=2;
		boolean fj=false;
		int counter=0;
		
		if (Character.toLowerCase(str.charAt(2)) =='f'){// checks to see if it's a final jeopardy question
			if(Character.toLowerCase(str.charAt(3)) =='j'){
				fj=true;
			}
		}
		
		if (!fj){
			for (int m=0; m<str.length(); m++){// checks to see if it has four sets of  colons
				if (str.charAt(m)==':' && str.charAt(m+1)== ':'){
					counter++;
					m++;
				}
				else if (str.charAt(m)== ':'){
					return false;
				}
			}
			if (counter!=4){
				return false;
			}
		} 
		if (str.contains("::::")){// checks to see if there is an empty category, point value, ext
			return false;
		}
		
		while (str.charAt(i)!=':'&& i<str.length()){// get's category name
			category= category + str.charAt(i);
			i++;
		}
		if (i==2){
			return false;
		}

		if (str.charAt(i+1)!=':'){// checks to make sure double colon
			return false;
		}
		i=i+2;		
		if (!category.equalsIgnoreCase("fj")){
			while (str.charAt(i)!=':'&& i<str.length()){// get's point value
				pv= pv + str.charAt(i);
				i++;
			}
			if (str.charAt(i+1)!=':' ){
					return false;
			}
		
		i=i+2;
		}
		while (str.charAt(i)!=':' && i<str.length()){//get's question
			question= question + str.charAt(i);
			i++;
		}
		
		if (str.charAt(i+1)!=':' ){
			return false;
		}
		i=i+2;
		while ( i<str.length() && str.charAt(i)!=':'){// get's answer
			answer= answer + str.charAt(i);
			i++;
		}

		if (category.equalsIgnoreCase( cat1.getName())){// adds full question to whichever category it goes with
			g.cat1.addQuestion(Integer.parseInt(pv), question, answer );
		}
		else if (category.equalsIgnoreCase( cat2.getName())){
			g.cat2.addQuestion(Integer.parseInt(pv), question, answer );
		}
		else if (category.equalsIgnoreCase( cat3.getName())){
			g.cat3.addQuestion(Integer.parseInt(pv), question, answer );
		}
		else if (category.equalsIgnoreCase( cat4.getName())){
			g.cat4.addQuestion(Integer.parseInt(pv), question, answer );
		}
		else if (category.equalsIgnoreCase( cat5.getName())){
			g.cat5.addQuestion(Integer.parseInt(pv), question, answer );
		}
		else if (category.equalsIgnoreCase( "fj")){
			g.fj.addQuestion(0, question, answer );
		}
		else{
			return false;
		}
	
		return true;
		
	}
	
	private void play_game(int n){
		/*This is the main method for game play
		 * this is recalled when a played types replay
		 */
		Scanner reader = new Scanner(System.in);
		boolean check=true;
		System.out.println("AutoPlay?");
		String str= reader.nextLine();
		str= str.toLowerCase();

		
		

		Random rand = new Random();
		int  num  = rand.nextInt(n );
		if (str.equalsIgnoreCase("yes")){}
		else{
		for (int k=0; k<25; k++){//repeats 25 times for each question
			System.out.println("It's " + t.get(num).get_name()+"'s turn. Please enter a category.");
			str= reader.nextLine();
			str= str.toLowerCase();
			int which_cat=0;
			
			if (str.equalsIgnoreCase("exit")){
				System.exit(0);
			}
			if (str.equalsIgnoreCase("replay")){
				reset();
				play_game(n);
			}
			
			while (which_cat==0){//loops until they give a valid category
				for (int i=1; i<=5; i++){
					if(r.get(i-1).getName().equalsIgnoreCase( str)){
						which_cat=i;
					}
				}
				if (which_cat==0){
					System.out.println("Invalid Input. Please enter a category");
					str= reader.nextLine();
					str= str.toLowerCase();
					if (str.equalsIgnoreCase("exit")){
						System.exit(0);
					}
					if (str.equalsIgnoreCase("replay")){
						reset();
						play_game(n);
					}
				}
			}
			
			System.out.println("Please enter a point value amount");
			String  p= reader.nextLine();
			which_cat--;
			
			if (p.equalsIgnoreCase("exit")){
				System.exit(0);
			}
			if (p.equalsIgnoreCase("replay")){
				reset();
				play_game(n);
				//start loop over
			}

			int points= Integer.parseInt(p);
			int which_pv=-1;
			
			while (which_pv==-1){//loops until they give a valid and open point value for the category they chose
				for (int i=0; i<5; i++){
					if (r.get(which_cat).getVec().get(i).getPointval() == points ){
						if (r.get(which_cat).getVec().get(i).getOpen() ){
							which_pv=i;
							r.get(which_cat).getVec().get(i).setOpen(false);
						}
						else{
							System.out.println("Question already chosen. Please enter a different point value amount");
							p= reader.nextLine();
							if (p.equalsIgnoreCase("exit")){
								System.exit(0);
							}
							if (p.equalsIgnoreCase("replay")){
								reset();
								play_game(n);
							}
							 points= Integer.parseInt(p);
						}
					}
				}
				if (which_pv==-1){
					System.out.println("Invalid Input. Please enter a point value amount.");
					p= reader.nextLine();
					if (p.equalsIgnoreCase("exit")){
						System.exit(0);
					}
					if (p.equalsIgnoreCase("replay")){
						reset();
						play_game(n);
					}
					 points= Integer.parseInt(p);
				}
				
			}
		
			String m = r.get(which_cat).getVec().get(which_pv).getQuestion();
			System.out.println(m);//prints question
			String answer= reader.nextLine();
			
			if (answer.equalsIgnoreCase("exit")){
				System.exit(0);
			}
			if (answer.equalsIgnoreCase("replay")){
				reset();
				play_game(n);
			}
			is_correct(answer, which_cat, which_pv,  num);
			print_scores();
			
			if (num+1>=n){
				num=0;
			}
			else{
				num++;
			}
		
		}
		

		for (int i=0; i<n; i++){//checks to make sure not all teams have negative points
			if (t.get(i).get_score()>0){
				check=true;
			}
		}
		}
		if (!check){
			System.out.println("All teams have negative score. No one won the game.");
		}
		else{	
			System.out.println("Final Jeopardy round!");
			for (int i=0; i<n; i++){//for each team gets how much they want to bet
				//if (t.get(i).get_score()>=0){//unless they have a negative score
					System.out.println(t.get(i).get_name() + ", how much would you like to bet?");
					String tr= reader.nextLine();
					if (tr.equalsIgnoreCase("exit")){
						System.exit(0);
					}
					if (tr.equalsIgnoreCase("replay")){
						reset();
						play_game(n);
					}
					int temp= Integer.parseInt(tr);
					t.get(i).setBet(temp);
				//}
			}//if bet not valid error message, what is invalid bet?
	
			
			System.out.println(r.get(r.size()-1).getVec().get(0).getQuestion());
			for (int i=0; i<n; i++){
				if (t.get(i).get_score()>=0){
					System.out.println(t.get(i).get_name() + ", what is your answer");
					String temp= reader.nextLine();
					
					if (temp.equalsIgnoreCase("exit")){
						System.exit(0);
					}
					if (temp.equalsIgnoreCase("replay")){
						reset();
						play_game(n);
					}
					if (final_iscorrect(i, temp)){
						t.get(i).set_fj(true);
						t.get(i).set_score(t.get(i).get_score()+t.get(i).get_bet());
					}
					else{
						t.get(i).set_fj(false);
						t.get(i).set_score(t.get(i).get_score()-t.get(i).get_bet());
					}
				}
			}
			boolean no=true;
			for (int i=0; i<n; i++){
				if (t.get(i).get_fj()){
					System.out.print(t.get(i).get_name()+ " ");
					no=false;
				}
			}
			if (!no){
				System.out.print("got the question corrrect"+ "\n");
			}
			else{
				System.out.println("No teams got the question right"+ "\n");
			}
			
			print_scores();
			int max=-1000000000;
			int winner=0;
			Vector<Integer> tie = new Vector<Integer>();
			for (int i=0; i<n; i++){//gets max score
				if (max < t.get(i).get_score()){
					max= t.get(i).get_score();
					winner=i;
				}
			}
			for (int i=0; i<n; i++){//checks to see if their are ties
				if (max == t.get(i).get_score()){
					tie.add(i);
				}
			}
			
			if (tie.size()>1){//if there are ties, print this
				System.out.print("Tie Game! These teams tied for the win: ");
				for (int i=0; i<tie.size(); i++){
					if (i==tie.size()-1){
						System.out.print(t.get(i).get_name());
					}
					else {
						System.out.print(t.get(i).get_name()+ " & ");
					}
				}
				System.out.print("\n");
			}
			else{
				System.out.println(t.get(winner).get_name() + " won the game!");
			}
		}
		reader.close();
	}
	
	private boolean getQs(Game g, BufferedReader br){
		/*This function reads from the file and splits it up into individual questions and does error checking to make sure
		 * they're in the right format
		*/
		
		String quest="";
		String temp="";
		
		try {
			quest=br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (!quest.isEmpty() &&  quest.charAt(0) != ':' && quest.charAt(1) != ':'){
			return false;
		}
		
		int count=0;
			try {
				while(br.ready()){//this loop keeps getting lines until the line starts with semi colons, then parses
					count++;// the previous question ans stores the next line in temp
					if(temp.isEmpty()) {
						quest= quest+temp;
						temp = br.readLine();
						while (temp.charAt(0) != ':'){
							quest= quest+temp;
							temp = br.readLine();
						}
					}

					if (!parse_questions( g,  quest,  br)){
						return false;
					}
					quest=temp;
//					if (count==23){
//						
				//		temp="";
					//}
					//else{
					temp="";
					//}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!parse_questions( g,  quest,  br)){
				return false;
			}
			if (count!=25){
				return false;
			}
			for (int i=0; i<r.size()-1; i++){//checks every category to make sure all the questions are valid (except FJ
				if (!check_Qs(i)){//category)
					return false;
				}
			}
			
			return true;
	}
	
	public static void main (String [ ] args){
		Scanner reader = new Scanner(System.in);

		Game g= new Game();
		g.t= new Vector<Team>();
		FileReader fr=null;
		BufferedReader br;
		try{	
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
			try{
				while (br.ready()){
					try{
						String line = br.readLine();
						//check beginning of line to make sure it's a word 

			
						//gets categories
						g.cat1= new Catergory(g.getcats(line));
						g.cat2= new Catergory(g.getcats(line.substring(g.cat1.getName().length()+2, line.length())));	
						g.cat3= new Catergory(g.getcats(line.substring(g.cat1.getName().length()+g.cat2.getName().length()+4, line.length())));
						g.cat4= new Catergory(g.getcats(line.substring(g.cat1.getName().length()+g.cat2.getName().length()+g.cat3.getName().length()+6, line.length())));
						g.cat5= new Catergory(g.getcats(line.substring(g.cat1.getName().length()+g.cat2.getName().length()+g.cat3.getName().length()+g.cat4.getName().length()+8, line.length())));
						g.fj= new Catergory("fj");
						
						//vector of categories and adds them all
						g.r = new Vector <Catergory>();
						g.r.add(g.cat1);						
						g.r.add(g.cat2);
						g.r.add(g.cat3);
						g.r.add(g.cat4);
						g.r.add(g.cat5);
						g.r.add(g.fj);
						
						if (!g.m){
							System.out.println("Input Error, program terminated.");
							System.exit(0);
						}
						//get point values
						g.c=0;
						line= br.readLine();
						g.pv1= g.getpvs(line);		
						g.pv2= g.getpvs(line.substring(g.c+2, line.length()));
						g.pv3= g.getpvs(line.substring(g.c+4, line.length()));
						g.pv4= g.getpvs(line.substring(g.c+6, line.length()));
						g.pv5= g.getpvs(line.substring(g.c+8, line.length()));
						
						//checks to make sure pvs aren't duplicates
						if (g.pv1==g.pv2 || g.pv1==g.pv3 || g.pv1==g.pv4 || g.pv1==g.pv5){
							g.m=false;
						}
						else if (g.pv2==g.pv3 ||  g.pv2==g.pv4 || g.pv2==g.pv5){
							g.m=false;
						}
						else if ( g.pv3==g.pv4 || g.pv3==g.pv5){
							g.m=false;
						}
						else if (g.pv4==g.pv5){
							g.m=false;
						}
						
						
						if (!g.m){
							System.out.println("Input Error, program terminated");
							System.exit(0);
						}
						if (!g.getQs(g, br)){
							System.out.println("Input Error, program terminated.");
							System.exit(0);
						}
					} catch(IOException ioe){
						System.out.println("IOException blank file");
					}
			
				}
			}catch(FileNotFoundException fnfe){
			System.out.println("FileNotFoundException: " + fnfe.getMessage());
			} catch (IOException e) {
			e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					if (fr != null) {
						fr.close();
					}
				} catch (IOException ioe) {
					System.out.println("IOException closing file" + ioe.getMessage());
				}
			}
			
			System.out.println("Welcome to Jeopardy!");
			System.out.println("Please enter the number of teams that will be playing in this game");

			

			int n= Integer.parseInt(reader.nextLine());
			
			while (n!=1 && n!=2 && n!=3 && n!=4){
				System.out.println("Invalid input. Please enter the number of teams that will be playing in this game");			
				n= Integer.parseInt(reader.nextLine());
			}
			for (int i=1; i<=n; i++){
				System.out.println("Please enter a team name for team " + i);
				String str= reader.nextLine();
				Team tr= new Team(str);
				g.t.add(tr);
			}//create teams from inputs

			g.play_game(n);
			//terminate program

	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} finally{
		reader.close();
	}
	System.exit(0);
}
}
