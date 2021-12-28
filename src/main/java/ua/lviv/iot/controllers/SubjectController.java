package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.service.ClusterProgramService;
import ua.lviv.iot.service.SubjectService;

import java.util.List;

@Controller
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public String findAll(Model model){
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        return "subject/subject-list";
    }

    @GetMapping("subject-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }

    @GetMapping("/subject-create")
    public String createSubjectForm(Subject subject){
        return "subject/subject-create";
    }

    @PostMapping("/subject-create")
    public String createSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/subject-update/{id}")
    public String updateSubjectForm(@PathVariable("id") Long id, Model model){
        if (subjectService.findById(id) != null) {
            Subject subject = subjectService.findById(id);
            model.addAttribute("subject", subject);
            return "subject/subject-update";
        }return "error";
    }

    @PostMapping("/subject-update")
    public String updateSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/subject/{id}")
    public String getSubject(@PathVariable("id") Long id, Model model){
        if (subjectService.findById(id) != null) {
            Subject subject = subjectService.findById(id);
            model.addAttribute("subject", subject);
            return "subject/subject";
        }return "error";
    }


}
