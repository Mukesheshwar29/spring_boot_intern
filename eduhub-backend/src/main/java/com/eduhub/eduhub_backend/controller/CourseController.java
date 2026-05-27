package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    StudentService studentService;
    public CourseController(CourseService courseService,StudentService studentService){
        this.courseService=courseService;
        this.studentService=studentService;
    }

    @GetMapping("get-course")
    public String getcourse(){
        return courseService.getCourse();
    }
    @GetMapping("get-student")
    public String getstudent(){
        return studentService.getStudent();
    }
    List<Course> courseList = new ArrayList<>();

    public CourseController() {

        courseList.add(new Course(101, "Java", 4));
        courseList.add(new Course(102, "Python", 3));
        courseList.add(new Course(103, "DBMS", 4));
        courseList.add(new Course(104, "AI", 5));
        courseList.add(new Course(105, "CN", 3));
    }

    // GET ALL COURSES
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(courseList);
    }

    // GET COURSE USING PATHVARIABLE
    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(
            @PathVariable int code) {

        for (Course c : courseList) {

            if (c.getCourseCode() == code) {
                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET COURSE USING REQUESTPARAM
    @GetMapping("/search")
    public ResponseEntity<Course> searchCourse(
            @RequestParam int code) {

        for (Course c : courseList) {

            if (c.getCourseCode() == code) {
                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // POST NEW COURSE
    @PostMapping
    public ResponseEntity<String> addCourse(
            @RequestBody Course course) {

        courseList.add(course);

        return ResponseEntity.ok("Course Added");
    }

    // UPDATE COURSE
    @PutMapping("/{code}")
    public ResponseEntity<String> updateCourse(
            @PathVariable int code,
            @RequestBody Course updatedCourse) {

        for (Course c : courseList) {

            if (c.getCourseCode() == code) {

                c.setSubjectName(updatedCourse.getSubjectName());
                c.setCredits(updatedCourse.getCredits());

                return ResponseEntity.ok("Course Updated");
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE COURSE
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable int code) {

        for (Course c : courseList) {

            if (c.getCourseCode() == code) {

                courseList.remove(c);

                return ResponseEntity.ok("Course Deleted");
            }
        }

        return ResponseEntity.notFound().build();
    }
}
