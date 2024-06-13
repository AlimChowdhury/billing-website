package com.example.billingWebsite.controller;

import com.example.billingWebsite.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskResultController {

    private final ScheduledTasks scheduledTasks;

    @Autowired
    public TaskResultController(ScheduledTasks scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    @GetMapping("/task-results/{taskName}")
    public Object getTaskResult(@PathVariable String taskName) {
        return scheduledTasks.getTaskResult(taskName);
    }
}
