package packt.book.jee.eclipse.ch4.bean;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
//import packt.book.jee.eclipse.ch4.bean.*;
import packt.book.jee.eclipse.ch4.dao.*;

public class CourseTest {
  @Test
  public void testIsValidCourse() {
    Course course = new Course();
    //First validate without any values set
    Assert.assertFalse(course.isValidCourse());
    //set name
    course.setName("course1");
    Assert.assertFalse(course.isValidCourse());
    //set zero credits
    course.setCredits(0);
    Assert.assertFalse(course.isValidCourse());
    //now set valid credits
    course.setCredits(4);
    Assert.assertTrue(course.isValidCourse());
    //set empty course name
    course.setName("");
    Assert.assertFalse(course.isValidCourse());
  }
  
  @Test (expected = EnrolmentFullException.class)
  public void testAddStudentWithEnromentFull() throws Exception {
  CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
  try {
    Mockito.when(courseDAO.getNumStudentsInCourse(1)).thenReturn(60);
    Mockito.doNothing().when(courseDAO).enrollStudentInCourse(1, 1);
  } catch (SQLException e) {
    Assert.fail(e.getMessage());
  }
  Course course = new Course();
  course.setCourseDAO(courseDAO);
  course.setId(1);
  course.setName("course1");
  course.setMaxStudents(60);
  //create student
  Student student = new Student();
  student.setFirstName("Student1");
  student.setId(1);
  //now add student
  course.addStudent(student);
  try {
    Mockito.verify(courseDAO, Mockito.atLeastOnce()).getNumStudentsInCourse(1);
  } catch (SQLException e) {
    Assert.fail(e.getMessage());
  }
  //If no exception was thrown then the test case was successful
  //No need of Assert here
  }
  
  @Test
  public void testAddStudentWithEnrolmentOpen() throws Exception {
    CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
    try {
      Mockito.when(courseDAO.getNumStudentsInCourse(1)).thenReturn(59);
      Mockito.doNothing().when(courseDAO).enrollStudentInCourse(1, 1);
    } catch (SQLException e) {
      Assert.fail(e.getMessage());
    }
    
    Course course = new Course();
    course.setCourseDAO(courseDAO);
    course.setId(1);
    course.setName("course1");
    course.setMaxStudents(60);
    //create student
    Student student = new Student();
    student.setFirstName("Student1");
    student.setId(1);
    //now add student
    course.addStudent(student);
    try {
      Mockito.verify(courseDAO, Mockito.atLeastOnce()).getNumStudentsInCourse(1);
      Mockito.verify(courseDAO, Mockito.atLeastOnce()).enrollStudentInCourse(1,1);
    } catch (SQLException e) {
      Assert.fail(e.getMessage());
    }
    //If no exception was thrown then the test case was successful
    //No need of Assert here
  }
}