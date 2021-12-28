package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.Student;
import ua.lviv.iot.service.LecturerService;
import ua.lviv.iot.service.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String findAll(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student/student-list";
    }

    @GetMapping("student-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/student-create")
    public String createStudentForm(Student student){
        return "student/student-create";
    }

    @PostMapping("/student-create")
    public String createStudent(Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student-update/{id}")
    public String updateStudentForm(@PathVariable("id") Long id, Model model){
        if (studentService.findById(id) != null) {
            Student student = studentService.findById(id);
            model.addAttribute("student", student);
            return "student/student-update";
        }return "error";
    }

    @PostMapping("/student-update")
    public String updateStudent(Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable("id") Long id, Model model){
        if (studentService.findById(id) != null) {
            Student student = studentService.findById(id);
            model.addAttribute("student", student);
            return "student/student";
        }return "error";
    }


}
