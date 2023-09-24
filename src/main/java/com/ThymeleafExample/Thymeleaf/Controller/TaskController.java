package com.ThymeleafExample.Thymeleaf.Controller;


import com.ThymeleafExample.Thymeleaf.Entity.Task;
import com.ThymeleafExample.Thymeleaf.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

   @Controller
  @RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/list")
    public String listTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task/new";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskRepository.save(task);
        return "redirect:/tasks/list";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
        model.addAttribute("task", task);
        return "task/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks/list";
    }
}

