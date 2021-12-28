package ua.lviv.iot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.Response;
import ua.lviv.iot.service.LecturerService;
import ua.lviv.iot.service.ResponseService;

import java.util.List;

@Controller
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/responses")
    public String findAll(Model model){
        List<Response> responses = responseService.findAll();
        model.addAttribute("responses", responses);
        return "response/response-list";
    }

    @GetMapping("response-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        responseService.deleteById(id);
        return "redirect:/responses";
    }

    @GetMapping("/response-create")
    public String createResponseForm(Response response){
        return "response/response-create";
    }

    @PostMapping("/response-create")
    public String createResponse(Response response){
        responseService.saveResponse(response);
        return "redirect:/responses";
    }

    @GetMapping("/response-update/{id}")
    public String updateResponseForm(@PathVariable("id") Long id, Model model){
        if (responseService.findById(id) != null) {
            Response response = responseService.findById(id);
            model.addAttribute("response", response);
            return "response/response-update";
        }return "error";
    }

    @PostMapping("/response-update")
    public String updateResponse(Response response){
        responseService.saveResponse(response);
        return "redirect:/responses";
    }

    @GetMapping("/response/{id}")
    public String getResponse(@PathVariable("id") Long id, Model model){
        if (responseService.findById(id) != null) {
            Response response = responseService.findById(id);
            model.addAttribute("response", response);
            return "response/response";
        }return "error";
    }


}
