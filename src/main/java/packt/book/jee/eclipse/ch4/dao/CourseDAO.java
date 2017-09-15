package packt.book.jee.eclipse.ch4.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import packt.book.jee.eclipse.ch4.bean.Course;
import packt.book.jee.eclipse.ch4.bean.Teacher;
import packt.book.jee.eclipse.ch4.db.connection.DatabaseConnectionFactory;

public class CourseDAO {
  public /*static*/ void addCourse(Course course) throws SQLException  {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
    final String sql = "INSERT INTO Course(name, credits) values(?,?)";
    // get connection from connection pool
    try {
      con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
      stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1,  course.getName());
      stmt.setInt(2, course.getCredits());
      stmt.execute();
      rs = stmt.getGeneratedKeys();
      if (rs.next())
      course.setId(rs.getInt(1));
      } finally {
      if (con != null) con.close();
      if (stmt != null) stmt.close();
      if (rs != null) rs.close();
    }
  }
  
  public /*static*/ List<Course> getCourses() throws SQLException {
    Course course = null;
    List<Course> courses = new ArrayList<Course>();
    String sql = "SELECT Course.id AS courseId, Course.name AS courseName, Course.credits AS credits, Teacher.id AS teacherId, Teacher.first_name AS firstName, Teacher.last_name AS lastName, Teacher.designation AS designation " + "FROM Course LEFT OUTER JOIN Teacher " + "ON Course.Teacher_id = Teacher.id " + "ORDER BY Course.name;";
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs   = null;
    try {
      con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
      stmt = con.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        course = new Course();
        course.setId(rs.getInt("courseId"));
        course.setName(rs.getString("courseName"));
        course.setCredits(rs.getInt("credits"));
        courses.add(course);
        
        int teacherId = rs.getInt("teacherId");
        if(rs.wasNull()) //Reports whether the last column read had a value of SQL NULL
          continue;       
        
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFirstName(rs.getString("firstName"));
        teacher.setLastName(rs.getString("lastName"));
        teacher.setDesignation(rs.getString("designation"));
        course.setTeacher(teacher);
      }
      return courses;
    } finally {
      if (con != null) con.close();
      if (stmt != null) stmt.close();
      if (rs != null) rs.close();
    }
  }

  public int getNumStudentsInCourse(int id) throws SQLException {
	return 50;
  }
  
  public void enrollStudentInCourse(int courseId, int studentId) throws SQLException {
	  ;
  }
}