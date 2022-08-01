package ratingStudents.service;

import ratingStudents.dao.StudentDataCollectionImplementation;
import ratingStudents.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataServiceImplementation {
    private StudentDataCollectionImplementation dao ;
    private int id =0;
    public StudentDataServiceImplementation(){
        this.dao = new StudentDataCollectionImplementation();
    }

    public int  autoGenrateId(){
       this.id++;
       return  id;
    }

    public Student addStudent(String name, String subjectName, String assignmentCategory, String datOfSubmission, int points){
        List<Student> particularStudentData= findStudentByStudentName(name);
        int id = autoGenrateId();
        String assignmentChangedCategory = checkAssignmentAlreadyExist(particularStudentData , assignmentCategory);
        Student addingStudent = new Student(id,name.trim(),subjectName.trim(),assignmentChangedCategory.trim(),datOfSubmission.trim(),points);
        addingStudent =  dao.addStudent(addingStudent);
        return addingStudent;
    }

    public String checkAssignmentAlreadyExist(List<Student> data , String assignmentCategory){
        int i=1;
        for(Student iterator : data){
            if(iterator.getAssignmentCategory().contains(assignmentCategory))
            {
                i++;
            }
        }
        String assignmentChangedName =  assignmentCategory+ "_"+i;
        return assignmentChangedName;
    }
    public Student removeStudent(String studentName , String assignmentCategory , int weightage){
        List<Student> studentData = findStudentByStudentName(studentName);
        for(Student iterater : studentData){
            if(iterater.getAssignmentCategory().contains(assignmentCategory) && iterater.getPoints() == weightage){
               Student         studentRemoved = dao.removeStudent(iterater);
               return  studentRemoved;
            }
        }
        return  null;
    }

    public float calculateTestScore(String studentName ,String subjectName){
        List<Student> particularStudentData= findStudentByStudentName(studentName);
        List<Integer> testScores = new ArrayList<>();
        for(Student iterator : particularStudentData){
            if(iterator.getAssignmentCategory().contains("Test") && iterator.getSubjectName().equalsIgnoreCase(subjectName))
            {
               testScores.add(iterator.getPoints());
            }
        }
        float scores =0 ;
        for(int i=0;i<testScores.size();i++){
            scores= scores + (40/testScores.size())*testScores.get(i);
        }
        float finalScore = scores/100;
        return  finalScore;
    }
    public float calculateProjectScore(String studentName ,String subjectName){
        List<Student> particularStudentData= findStudentByStudentName(studentName);
        List<Integer> projectScores = new ArrayList<>();
        for(Student iterator : particularStudentData){
            if(iterator.getAssignmentCategory().contains("Project")  && iterator.getSubjectName().equalsIgnoreCase(subjectName))
            {
                projectScores.add(iterator.getPoints());
            }
        }
        float scores =0 ;
        for(int i=0;i<projectScores.size();i++){
            scores= scores + (30/(projectScores.size()))*projectScores.get(i);
        }
        float finalScore = scores/100;
        return  finalScore;
    }
    public float calculateQuizScore(String studentName,String subjectName){
        List<Student> particularStudentData= findStudentByStudentName(studentName);
        List<Integer> quizScores = new ArrayList<>();
        for(Student iterator : particularStudentData){
            if(iterator.getAssignmentCategory().contains("Quiz") && iterator.getSubjectName().equalsIgnoreCase(subjectName))
            {
               quizScores.add(iterator.getPoints());
            }
        }
        float scores =0 ;
        for(int i=0;i<quizScores.size();i++){
            scores= scores + (10/(quizScores.size()))*quizScores.get(i);
        }
        float finalScore = scores/100;
        return  finalScore;
    }
    public float calculateLabScore(String studentName , String subjectName ){
        List<Student> particularStudentData= findStudentByStudentName(studentName);
        List<Integer> labScores = new ArrayList<>();
        for(Student iterator : particularStudentData){

            if(iterator.getAssignmentCategory().contains("Lab") && iterator.getSubjectName().equalsIgnoreCase(subjectName))
            {
                labScores.add(iterator.getPoints());
            }
        }

        float scores =0 ;
        for(int i=0;i<labScores.size();i++){
            scores= scores + (20/labScores.size())*labScores.get(i);
        }
        float finalScore = scores/100;
        return  finalScore;
    }
    public List<Student> listOfAllStudent(){
        List<Student> studentList = dao.getStudentsData();
        return studentList;
    }
    public List<String> studentSubjects(String studentName){
        List<Student> studentDataList= findStudentByStudentName(studentName);
        List<String> studentSubjects = new ArrayList<>();
        studentSubjects.add(studentDataList.get(0).getSubjectName());
        for(int i =1 ;i<studentDataList.size();i++){
            int j=0;
            for(j=0;j<studentSubjects.size();j++)
            {

                if(studentDataList.get(i).getSubjectName().equals(studentSubjects.get(j))){
                    break;
                }
            }
            if(j== studentSubjects.size()){
                studentSubjects.add(studentDataList.get(i).getSubjectName());
            }

        }
        return studentSubjects;
    }
    public List<Student> findStudentByStudentName(String studentName) {
        List<Student> studentsData = listOfAllStudent();
        List<Student> studentData =  new ArrayList<>();
        for(Student iterate : studentsData){
            if(iterate.getName().equalsIgnoreCase(studentName)){
                studentData.add(iterate);
            }
        }
        return  studentData;
    }
    public List<Student> findStudentsBySubject(String subject) {
        List<Student> studentsData = listOfAllStudent();
        List<Student> subjectData =  new ArrayList<>();
        for(Student iterate : studentsData){
            if(iterate.getSubjectName().equalsIgnoreCase(subject)){
                subjectData.add(iterate);
            }
        }
        return  subjectData;
    }
    public List<Student> studentsSameSubject(String subject){
        List<Student> subjectDataList= findStudentsBySubject(subject);
        List<Student> studentsFromSubject = new ArrayList<>();
        studentsFromSubject.add(subjectDataList.get(0));
        for(int i =1 ;i<subjectDataList.size();i++){
            int j=0;
            for(j=0;j<studentsFromSubject.size();j++)
            {

                if(subjectDataList.get(i).getName().equals(studentsFromSubject.get(j).getName())){
                    break;
                }
            }
            if(j== studentsFromSubject.size()){
                studentsFromSubject.add(subjectDataList.get(i));
            }

        }
        return studentsFromSubject;
    }
}
