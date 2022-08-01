package ratingStudents.frontEnd;

import ratingStudents.entity.Student;
import ratingStudents.service.StudentDataServiceImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrontEnd {
    private StudentDataServiceImplementation service;
    private  InputStreamReader ir ;
    private  BufferedReader br ;
    private Scanner sc  ;

    public  FrontEnd(){
        this.service= new StudentDataServiceImplementation();
        this.sc = new Scanner(System.in);
        this.ir = new InputStreamReader(System. in );
        this.br =new BufferedReader(ir);
    }
    public  void addAll(){
        System.out.println(" name = ");
        String name = null;
        String subject = null;
        String assignmentCategory = null;
        String dateOfSubmission = null;
        int points = 0;
        try {
            name = br.readLine();
            System.out.println("Subject = ");
            subject = br.readLine();
            System.out.println(" Enter 1 : test  , 2 : Quiz , 3 : Lab , 4 : Project");
            int value = sc.nextInt();
            switch(value){
                case 1 : assignmentCategory = "Test";
                    break;
                case 2 : assignmentCategory = "Quiz";
                    break;
                case 3 : assignmentCategory = "Lab";
                    break;
                case 4 : assignmentCategory = "Project";
                    break;
                default :
                    System.out.println(" Invalid value");
                    switchPointsForFunctions();

            }
            System.out.println(" Date Of Submission = ");
            dateOfSubmission = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" weightage = ");
        points = sc.nextInt();
        Student student =  service.addStudent(name,subject,assignmentCategory,dateOfSubmission,points);
        System.out.println("Details Added " + student.getAssignmentCategory()+" , "+student.getName()+" , "+student.getPoints());
        System.out.println();
    }
    public void displayCategoryScoresByName(){
        System.out.println("Enter Student name you want to search");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> subjectsName = service.studentSubjects(name);

        System.out.println("Subject  " +"testScore "+"quizScore "+"labScore "+"projectScore " );
        for(String subjectName : subjectsName)
        {
            float labScore = service.calculateLabScore(name,subjectName);
            float projectScore = service.calculateProjectScore(name,subjectName);
            float quizScore = service.calculateQuizScore(name,subjectName);
            float testScore = service.calculateTestScore(name,subjectName);
            System.out.println(subjectName +"  "+testScore+"  "+quizScore+" "+labScore+" "+projectScore );
        }
    }

    public void displayCategoryScoresBySubject(){

        System.out.println(" Enter the subject name you want to search ");
        String subject = null;
        try {
            subject = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Student> studentsOfSameSubject =  service.studentsSameSubject(subject);
        System.out.println(" Scores Of Subject - " + subject);
        System.out.println("Name " +"testScore "+"quizScore "+"labScore "+"projectScore " );
        for(Student iterate :studentsOfSameSubject)
        {
            float labScore = service.calculateLabScore(iterate.getName(),subject);
            float projectScore = service.calculateProjectScore(iterate.getName(),subject);
            float quizScore = service.calculateQuizScore(iterate.getName(),subject);
            float testScore = service.calculateTestScore(iterate.getName(),subject);
            System.out.println(iterate.getName() +"  "+testScore+"  "+quizScore+" "+labScore+" "+projectScore );
        }
    }

    public void removeStudent( ){
        InputStreamReader ir = new InputStreamReader(System. in );
        BufferedReader br = new BufferedReader(ir);
        String name= null;
        String assignmentCategory = null;
        try {
            System.out.println(" Enter name you want to remove ");
            name = br.readLine();
            System.out.println(" Enter 1 : test  , 2 : Quiz , 3 : Lab , 4 : Project");
            int value = sc.nextInt();
            switch(value){
                case 1 : assignmentCategory = "Test";
                    break;
                case 2 : assignmentCategory = "Quiz";
                    break;
                case 3 : assignmentCategory = "Lab";
                    break;
                case 4 : assignmentCategory = "Project";
                    break;
                default :
                    System.out.println(" Invalid value");
                    switchPointsForFunctions();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Enter weightage you want to remove ");
        int weightage = sc.nextInt();
        Student studentRemoved = service.removeStudent(name,assignmentCategory,weightage);
        System.out.println("Student Removed with Assignemnt Category "+ assignmentCategory +  " weightage "+ weightage);
        System.out.println(studentRemoved.getName()+ " "+ studentRemoved.getAssignmentCategory()+ " "+studentRemoved.getPoints());
    }
    public  void displayStudentsList(){
        List<Student>students =  service.listOfAllStudent();
        for(Student iterate : students){
            System.out.println(iterate.getId()+" "+iterate.getName()+" "+iterate.getSubjectName()+" "+iterate.getAssignmentCategory()+" "+iterate.getDatOfSubmission()+" "+iterate.getPoints());
        }
    }

    public void switchPointsForFunctions(){
        System.out.println(" ******************************************************** ");
        System.out.println(" 1 :  Enter 1 For Adding Record ");
        System.out.println(" 2 :  Enter 2 for Seeing Record of Student ");
        System.out.println(" 3 :  Enter 3 for Seeing Record of Subject For each Student ");
        System.out.println(" 4 :  Enter 4 for Removing Data ");
        System.out.println(" 5 :  Enter 5 for List of Students ");
        System.out.println(" 6 :  Enter 6 for EXIT ");
        System.out.println(" Enter Value ");
        int value = sc.nextInt();
        switch (value){
            case 1:
                System.out.println("*********************************************");
                System.out.println("Enter number of Records you want to enter " );
                int records = sc.nextInt();
                for (int i =0 ;i<records;i++){

                    addAll();
                }
                switchPointsForFunctions();
                break;
            case 2 :
                System.out.println("*********************************************");
                System.out.println(" Enter Student name  for record you want to see");
                displayCategoryScoresByName();
                switchPointsForFunctions();
                break;
            case 3 :
                System.out.println("**********************************************");
                System.out.println(" Enter Subject name whose Record You want to see ");
                displayCategoryScoresBySubject();
                switchPointsForFunctions();
                break;
            case 4:
                System.out.println("**********************************************");
                System.out.println(" Enter Student name , Assignment Category  and Weightage which you want to delete ");
                removeStudent();
                switchPointsForFunctions();
                break;
            case 5:
                System.out.println("**********************************************");
                displayStudentsList();
                switchPointsForFunctions();
                break;

            default :
                System.out.println(" Thank You ");
                break;
        }
    }

}
