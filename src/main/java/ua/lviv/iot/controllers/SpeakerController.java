package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.Speaker;
import ua.lviv.iot.service.LecturerService;
import ua.lviv.iot.service.SpeakerService;

import java.util.List;

@Controller
public class SpeakerController {

    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping("/speakers")
    public String findAll(Model model){
        List<Speaker> speakers = speakerService.findAll();
        model.addAttribute("speakers", speakers);
        return "speaker/speaker-list";
    }

    @GetMapping("speaker-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        speakerService.deleteById(id);
        return "redirect:/speakers";
    }

    @GetMapping("/speaker-create")
    public String createSpeakerForm(Speaker speaker){
        return "speaker/speaker-create";
    }

    @PostMapping("/speaker-create")
    public String createSpeaker(Speaker speaker){
        speakerService.saveSpeaker(speaker);
        return "redirect:/speakers";
    }

    @GetMapping("/speaker-update/{id}")
    public String updateSpeakerForm(@PathVariable("id") Long id, Model model){
        if (speakerService.findById(id) != null) {
            Speaker speaker = speakerService.findById(id);
            model.addAttribute("speaker", speaker);
            return "speaker/speaker-update";
        }return "error";
    }

    @PostMapping("/speaker-update")
    public String updateLecturer(Speaker speaker){
        speakerService.saveSpeaker(speaker);
        return "redirect:/speakers";
    }

    @GetMapping("/speaker/{id}")
    public String getSpeaker(@PathVariable("id") Long id, Model model){
        if (speakerService.findById(id) != null) {
            Speaker speaker = speakerService.findById(id);
            model.addAttribute("speaker", speaker);
            return "speaker/speaker";
        }return "error";
    }


}
