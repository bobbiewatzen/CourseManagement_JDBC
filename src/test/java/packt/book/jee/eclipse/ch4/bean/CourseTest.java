package packt.book.jee.eclipse.ch4.bean;

import org.junit.Assert;
import org.junit.Test;

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
}