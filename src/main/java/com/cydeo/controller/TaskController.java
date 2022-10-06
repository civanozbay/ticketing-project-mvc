package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    ProjectService projectService;
    UserService userService;
    TaskService taskService;

    public TaskController(ProjectService projectService,UserService userService,TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String taskHome(Model model){
        model.addAttribute("task",new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("users",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/create";
    }
    @PostMapping("/create")
    public String taskHome(TaskDTO taskDTO){
        taskService.save(taskDTO);
        return "redirect:/task/create";
    }
    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId")Long taskId){
        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId,Model model){
        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }
    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable("taskId") Long taskId,TaskDTO task){
        task.setId(taskId);
        taskService.update(task);
        return "redirect:/task/create";
    }
}
