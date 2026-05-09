package project.java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class User {
    private final String name;
    private int attempt;
    private int totalscore;
    User(String name){
        this.name=name;
        this.attempt=0;
        this.totalscore=0;
    }
    public void increaseattempt(){
        attempt++;
    }
    public boolean canattempt(){
        return attempt<3;
    }
    public void addscore(int score){
        totalscore+=score;
    }
    public String getname(){
        return name;
    }
    public int getattempt(){
        return attempt;
    }
    public int gettotalscore(){
        return totalscore;
    }
}
class Question{
    private String Question;
    private String[] options;
    private int correctanswer;
     Question(String Question,String[] options,int correctanswer){
        this.Question=Question;
        this.options=options;
        this.correctanswer=correctanswer;
    }
    public String getquestion(){
        return Question;
    }
    public String[] getoptions(){
        return options;
    }
    
    public void displayQuestion(){
        System.out.println(Question);
        for(int i=0; i<options.length;i++){
            System.out.println((i+1)+ " " + options[i]);
        }

    }

    public boolean isCorrect(int answer) {
         return answer==correctanswer;
    }
    public int getcorrectanswer(){
        return correctanswer;
    }
}
class Exam{
private ArrayList<Question> questions;
private int Score;
 Exam( ArrayList<Question>questions){
    this.questions=questions;
    this.Score=0;

}

    public int startExam(){
    int answer = 0;
    Scanner cin = new Scanner(System.in);
   Collections.shuffle(questions);
   long startTime = System.currentTimeMillis();
   long timeLimit = 60 * 60 * 1000; 
    for(int i=0;i<questions.size();i++) {
        Question q = questions.get(i);
        System.out.println("Question" + (i + 1));
        System.out.println("you have 1 minute for this Question");
        q.displayQuestion();
        System.out.println("Enter your answer");
        answer = cin.nextInt();
        long remaining = timeLimit - (System.currentTimeMillis() - startTime);
        if(remaining <= 0){
            System.out.println("Time up");
            return Score;
        }
        System.out.println("Time remaining: " + remaining/1000 + " seconds");
        while(answer< 1 ||answer>4 ){
            System.out.println("invsliad answer try again(1-4)");
            answer=cin.nextInt();
        }
        if (q.isCorrect(answer))
            Score++;
    }
        return Score;
    }
    }

class Admin{
    private String username="Admin";
    private String pass="admin828";
    public boolean login(String usrn,String password){
        if(username.equals(usrn)&&pass.equals(password)){
            return true;
        }
        else return false;
    }
    public void addQuestions(Scanner cin){
    	int choice;
    	String filename;
    	
    	String question;
    	String options;
    	int correctans;
    	System.out.println("Select subject");
    	System.out.println("1 English");
    	System.out.println("2 physics");
    	System.out.println("3 chemistry");

    	System.out.println("4 GK");
    	choice=cin.nextInt();
    	cin.nextLine();
    	if(choice == 1) { 
    		filename = "English.txt";
    	
    	}
        else if(choice == 2) { 
        	filename = "physics.txt";
        }
        else if(choice == 3) {
        	filename = "chemistry.txt";
        }
        else filename = "GK.txt";
    
    	
System.out.println("Enter Question");
question = cin.nextLine();
System.out.println("Enter 4 options");
options = cin.nextLine();
System.out.println("Select correct answer");
cin.nextLine();
correctans = cin.nextInt();
try{
    java.io.FileWriter fw = new java.io.FileWriter(filename, true);
    fw.write("\n" + question + "\n" + options + "\n" + correctans + "\n");
    fw.close();
    System.out.println("Question added");
} catch(Exception e){
    System.out.println("Error");
}



    }
    public void deleteQuestions(Scanner cin){
      	int choice;
    	String Filename;
    	int num;
    
    	System.out.println("Select subject");
    	System.out.println("1 English");
    	System.out.println("2 physics");
    	System.out.println("3 chemistry");

    	System.out.println("4 GK");
    	choice=cin.nextInt();
    	if(choice == 1) { 
    		Filename = "English.txt";
    	
    	}
        else if(choice == 2) { 
        	Filename = "physics.txt";
        }
        else if(choice == 3) {
        	Filename = "chemistry.txt";
        }
        else Filename = "GK.txt";
    	
    	ArrayList<Question> list = OnlineExam.loadQuestions(Filename);
        for(int i=0; i<list.size(); i++)
            System.out.println((i+1) + " " + list.get(i).getquestion());
        
        System.out.println("Enter question number to delete:");
         num = cin.nextInt();
        list.remove(num-1);
        
        try{
            java.io.FileWriter fw = new java.io.FileWriter(Filename, false);
            for(Question q : list){
                fw.write(q.getquestion() + "\n");
                fw.write(String.join(",", q.getoptions()) + "\n");
                fw.write(q.getcorrectanswer() + "\n");
            }
            fw.close();
            System.out.println("Question deleted");
        } catch(Exception e){
            System.out.println("Error");
        }
    }
    

    
    public void updateQuestions(Scanner cin){
    	int choice=0;
    	String Filename="";
    	int num;
    	String question;
    	int correctanswers=0;
    	String options;
    	System.out.println("Select Subejct");
    	System.out.println("1 English");
    	System.out.println("2 physics");
    	System.out.println("3 chemistry");
    	System.out.println("4 GK");
    	choice = cin.nextInt();
    	if(choice ==1) {
    	Filename = "English.txt";
    	}
    	if(choice == 2) {
    	Filename="physics.txt";
    	}
    	if(choice ==3) {
    	 Filename="chemistry.txt";
    	}
    	 if(choice == 4) {
    	Filename ="GK.txt";
    	 }
    	ArrayList<Question> list = OnlineExam.loadQuestions(Filename);
        for(int i=0; i<list.size(); i++)
            System.out.println((i+1) + " " + list.get(i).getquestion());
        System.out.println("Enter Question num for update");
        num = cin.nextInt();
        System.out.println("Enter neew question");
        question = cin.nextLine();
        System.out.println("Enter nw options");
        options = cin.nextLine();
        list.set(num-1, new Question(question, options.split(" "), correctanswers));
        
        try{
            java.io.FileWriter fw = new java.io.FileWriter(Filename, false);
            for(Question qu : list){
                fw.write(qu.getquestion() + "\n");
                fw.write(String.join(" ", qu.getoptions()) + "\n");
                fw.write(qu.getcorrectanswer() + "\n");
            }
            fw.close();
            System.out.println("Question updated!");
        } catch(Exception e){
            System.out.println("Error");
        }
    }
        

    
    public void viewresults(){
    	try{
            Scanner file = new Scanner(new File("results.txt"));
            while(file.hasNextLine())
                System.out.println(file.nextLine());
            file.close();
        } catch(Exception e){
            System.out.println("No results found!");
        }

    }
    
    public void adminmenu(Scanner cin){
    	int choice=0;
    	while(choice !=4) {
    		System.out.println("1 Add questions");
    		System.out.println("2 Delete Question");
    		System.out.println("3 Update Question");
    				System.out.println("4 Exit");
    		choice = cin.nextInt();
    		if(choice == 1) addQuestions(cin);
    		else if(choice == 2) deleteQuestions(cin);
    		else if(choice == 3) updateQuestions(cin);
    	}
    	
    }

}

public class OnlineExam{
	public static ArrayList<Question> loadQuestions(String javaproject) {
	    ArrayList<Question> list = new ArrayList<>();

	    try {
	        Scanner file = new Scanner(new File(javaproject));

	        while(file.hasNextLine()) {
	            String q = file.nextLine();

	            if(q.isEmpty()) continue;

	            String optLine = file.nextLine();
	            int correct = Integer.parseInt(file.nextLine());

	            String[] options = optLine.split(",");

	            list.add(new Question(q, options, correct));
	        }

	        file.close();
	    } catch (Exception e) {
	        System.out.println("File error");
	    }

	    return list;
	}
	
   public static void main(String[] args) {
	   int choice=0;
	  int score=0;
	  int role=0;
	  String uname="";
	  String pass="";
        String name;
        int subjectdone=0;
        System.out.println("Welcome to the Exam");
        Scanner cin = new Scanner(System.in);
        System.out.println("1 Student");
        System.out.println("2 Admin");
        role = cin.nextInt();
        cin.nextLine();
        
        if(role == 2) {
        	Admin admin = new Admin();
        	System.out.println("Enter user name");
        	uname = cin.nextLine();
        	System.out.println("Enter your password");
        	pass = cin.nextLine();
        	while(!admin.login(uname, pass)){
        	    System.out.println("Wrong credentials, try again");
        	    System.out.println("Enter user name");
        	    uname = cin.nextLine();
        	    System.out.println("Enter your password");
        	    pass = cin.nextLine();
     
        	}
        	
        	System.out.println("Login Successfull");
        	admin.adminmenu(cin);
        	return;
        	}
        System.out.println("Enter your name");
        name=cin.nextLine();
        while(name.trim().isEmpty() || name.matches(".*\\d.*")){
            System.out.println("Please enter a valid name");
            name=cin.nextLine();
        }
        
        	
        
        
        
  
        User user = new User(name);
 
        String javaproject="";
        ArrayList<Question> questions = new ArrayList<>();
        
       
        while(user.canattempt()){
            subjectdone=0;
            while(subjectdone<4){
                System.out.println("Select subject");
                System.out.println("1 English");
                System.out.println("2 physics");
                System.out.println("3 chemistry");
                System.out.println("4 General knowledge");
                choice = cin.nextInt();
                cin.nextLine();
                if(choice == 1) javaproject = "English.txt";
                else if(choice == 2) javaproject = "physics.txt";
                else if(choice == 3) javaproject = "chemistry.txt";
                else javaproject = "GK.txt";
                questions = loadQuestions(javaproject);
                Exam exam = new Exam(questions);
                score = exam.startExam();
                user.addscore(score);
                System.out.println("Subject " + (subjectdone+1) + " Score " + score);
                subjectdone++;
            }
            user.increaseattempt();
            System.out.println("Attempt " + user.getattempt() + " complete! Total score: " + user.gettotalscore());
            if(user.canattempt()){
                System.out.println("Try again? y/n");
                String retry = cin.next();
                while(!retry.equals("y") && !retry.equals("n")){
                    System.out.println("Please enter y or n");
                    retry = cin.next();
                }
                if(!retry.equals("y")) break;
            }
        }
        System.out.println("Exam complete");


    }
}