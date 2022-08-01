package ratingStudents.dao;

import ratingStudents.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataCollectionImplementation
{
    private List<Student> studentsData;

    public  StudentDataCollectionImplementation(){
        this.studentsData = new ArrayList<>();
    }

    // add students to arraytlist StudentsData
    public Student addStudent(Student student) {
      this.studentsData.add(student);
      return  student;
    }

    //return student which is getting removed from arraylist
    public Student removeStudent(Student student) {
       this.studentsData.remove(student);
       return student;
    }

    public List<Student> getStudentsData() {
        return studentsData;
    }

}
