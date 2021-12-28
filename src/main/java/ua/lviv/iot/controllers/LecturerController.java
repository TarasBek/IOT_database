package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.service.LecturerService;
import ua.lviv.iot.service.SubjectService;

import java.util.List;

@Controller
public class LecturerController {

    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturers")
    public String findAll(Model model){
        List<Lecturer> lecturers = lecturerService.findAll();
        model.addAttribute("lecturers", lecturers);
        return "lecturer/lecturer-list";
    }

    @GetMapping("lecturer-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        lecturerService.deleteById(id);
        return "redirect:/lecturers";
    }

    @GetMapping("/lecturer-create")
    public String createLecturerForm(Lecturer lecturer){
        return "lecturer/lecturer-create";
    }

    @PostMapping("/lecturer-create")
    public String createLecturer(Lecturer lecturer){
        lecturerService.saveLecturer(lecturer);
        return "redirect:/lecturers";
    }

    @GetMapping("/lecturer-update/{id}")
    public String updateLecturerForm(@PathVariable("id") Long id, Model model){
        if (lecturerService.findById(id) != null) {
            Lecturer lecturer = lecturerService.findById(id);
            model.addAttribute("lecturer", lecturer);
            return "lecturer/lecturer-update";
        }return "error";
    }

    @PostMapping("/lecturer-update")
    public String updateLecturer(Lecturer lecturer){
        lecturerService.saveLecturer(lecturer);
        return "redirect:/lecturers";
    }

    @GetMapping("/lecturer/{id}")
    public String getLecturer(@PathVariable("id") Long id, Model model){
        if (lecturerService.findById(id) != null) {
            Lecturer lecturer = lecturerService.findById(id);
            model.addAttribute("lecturer", lecturer);
            return "lecturer/lecturer";
        }return "error";
    }


}
