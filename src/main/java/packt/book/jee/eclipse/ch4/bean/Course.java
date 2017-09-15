package packt.book.jee.eclipse.ch4.bean;

import java.util.*;
import java.sql.SQLException;
import packt.book.jee.eclipse.ch4.dao.*;

public class Course {
  private int id;
  private String name;
  private int credits;
  private int teacherId;
  private Teacher teacher;
  private int MaxStudents;
  private CourseDAO courseDAO = new CourseDAO();
  
  public boolean isValidCourse() {
	return name != null && name.trim().length() > 0 && credits != 0;
  }
  
  public void addCourse() throws SQLException {
	courseDAO.addCourse(this); //static method
  }
  
  public List<Course> getCourses() throws SQLException {
	return courseDAO.getCourses();
  }
  
  public void setCourseDAO(CourseDAO courseDAO) {
	this.courseDAO = courseDAO;
  }
  
  public void addStudent(Student student) throws EnrolmentFullException, SQLException {
	//get current enrolment first
	int currentEnrolment = courseDAO.getNumStudentsInCourse(id);
	if (currentEnrolment >= getMaxStudents())
	  throw new EnrolmentFullException("Course is full. Enrolment closed");
	courseDAO.enrolStudentInCourse(id, student.getId());
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getCredits() {
    return credits;
  }
  public void setCredits(int credits) {
    this.credits = credits;
  }
  public Teacher getTeacher() {
	return teacher;
  }  
  public void setTeacher(Teacher teacher) {
	this.teacher = teacher;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(int teacherId) {
    this.teacherId = teacherId;
  }

public int getMaxStudents() {
	return MaxStudents;
}

public void setMaxStudents(int maxStudents) {
	MaxStudents = maxStudents;
}
}