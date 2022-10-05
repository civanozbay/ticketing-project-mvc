package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    UserService userService;

    public ProjectController(UserService userService,ProjectService projectService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());

        return "/project/create";
    }

    @PostMapping("/create")
    public String addProject(@ModelAttribute("project") ProjectDTO projectDTO,Model model){

        projectService.save(projectDTO);

        return "redirect:/project/create";
    }
    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode")String projectcode){
        projectService.deleteById(projectcode);
        return "redirect:/project/create";
    }
    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode")String projectcode){
        projectService.complete(projectService.findById(projectcode));
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode")String projectcode,Model model){

        model.addAttribute("project",projectService.findById(projectcode));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());

        return "/project/update";
    }

    @PostMapping("/update")
    public String editProject(ProjectDTO projectDTO){

        projectService.update(projectDTO);

        return "redirect:/project/create";
    }
}
