package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.StudentGroup;
import ua.lviv.iot.service.LecturerService;
import ua.lviv.iot.service.StudentGroupService;

import java.util.List;

@Controller
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    @Autowired
    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("/studentGroups")
    public String findAll(Model model){
        List<StudentGroup> studentGroups = studentGroupService.findAll();
        model.addAttribute("studentGroups", studentGroups);
        return "studentGroup/studentGroup-list";
    }

    @GetMapping("studentGroup-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        studentGroupService.deleteById(id);
        return "redirect:/studentGroups";
    }

    @GetMapping("/studentGroup-create")
    public String createStudentGroupForm(StudentGroup studentGroup){
        return "studentGroup/studentGroup-create";
    }

    @PostMapping("/studentGroup-create")
    public String createStudentGroup(StudentGroup studentGroup){
        studentGroupService.saveStudentGroup(studentGroup);
        return "redirect:/studentGroups";
    }

    @GetMapping("/studentGroup-update/{id}")
    public String updateStudentGroupForm(@PathVariable("id") Long id, Model model){
        if (studentGroupService.findById(id) != null) {
            StudentGroup studentGroup = studentGroupService.findById(id);
            model.addAttribute("studentGroup", studentGroup);
            return "studentGroup/studentGroup-update";
        }return "error";
    }

    @PostMapping("/studentGroup-update")
    public String updateStudentGroup(StudentGroup studentGroup){
        studentGroupService.saveStudentGroup(studentGroup);
        return "redirect:/studentGroups";
    }

    @GetMapping("/studentGroup/{id}")
    public String getStudentGroup(@PathVariable("id") Long id, Model model){
        if (studentGroupService.findById(id) != null) {
            StudentGroup studentGroup = studentGroupService.findById(id);
            model.addAttribute("studentGroup", studentGroup);
            return "studentGroup/studentGroup";
        }return "error";
    }


}
