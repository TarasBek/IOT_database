package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.service.ClusterProgramService;

import java.util.List;

@Controller
public class ClusterProgramController {

    private final ClusterProgramService clusterProgramService;

    @Autowired
    public ClusterProgramController(ClusterProgramService clusterProgramService) {
        this.clusterProgramService = clusterProgramService;
    }

    @GetMapping("/clusterPrograms")
    public String findAll(Model model){
        List<ClusterProgram> clusterPrograms = clusterProgramService.findAll();
        model.addAttribute("clusterPrograms", clusterPrograms);
        return "clusterProgram/clusterProgram-list";
    }
    
    @GetMapping("clusterProgram-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        clusterProgramService.deleteById(id);
        return "redirect:/clusterPrograms";
    }

    @GetMapping("/clusterProgram-create")
    public String createClusterProgramForm(ClusterProgram clusterProgram){
        return "clusterProgram/clusterProgram-create";
    }

    @PostMapping("/clusterProgram-create")
    public String createClusterProgram(ClusterProgram clusterProgram){
        clusterProgramService.saveClusterProgram(clusterProgram);
        return "redirect:/clusterPrograms";
    }

    @GetMapping("/clusterProgram-update/{id}")
    public String updateClusterProgramForm(@PathVariable("id") Long id, Model model){
        if (clusterProgramService.findById(id) != null) {
            ClusterProgram clusterProgram = clusterProgramService.findById(id);
            model.addAttribute("clusterProgram", clusterProgram);
            return "clusterProgram/clusterProgram-update";
        }return "error";
    }

    @PostMapping("/clusterProgram-update")
    public String updateClusterProgram(ClusterProgram clusterProgram){
        clusterProgramService.saveClusterProgram(clusterProgram);
        return "redirect:/clusterPrograms";
    }

    @GetMapping("/clusterProgram/{id}")
    public String getClusterProgram(@PathVariable("id") Long id, Model model){
        if (clusterProgramService.findById(id) != null) {
            ClusterProgram clusterProgram = clusterProgramService.findById(id);
            model.addAttribute("clusterProgram", clusterProgram);
            return "clusterProgram/clusterProgram";
        }return "error";
    }


}
