package com.flx.protobuf;

import com.flx.protobuf.dto.Course;
import org.junit.Assert;
import org.junit.Test;

public class CourseTest {

  @Test
  public void testCourse() {
    Course course = Course.newBuilder().setOrderNo("1234567890abcd").setScore(1).build();
    String str = course.toString();
    Assert.assertNotNull(str);
    System.out.println(str);
  }

}
