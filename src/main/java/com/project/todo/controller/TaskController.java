package com.project.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.model.Task;
import com.project.todo.service.TaskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @ApiOperation(value = "Creating a new task.")
    @ApiResponses(value ={
        @ApiResponse(code = 201, message = "Task created successfully"),
        @ApiResponse(code = 500, message = "Error creating task.")
    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask (@RequestBody Task task){
        log.info("Creating a new task with they information");
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listing all classes.")
    @ApiResponses(value ={
        @ApiResponse(code = 200, message = "Tasks listed successfully"),
        @ApiResponse(code = 500, message = "Error listing tasks.")
    })

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasks(){
        log.info("List all tasks and your information");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Search a task by id.")
    @ApiResponses(value ={
        @ApiResponse(code = 200, message = "Tasks found successfully"),
        @ApiResponse(code = 404, message = "No tasks found.")
    })

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id){
        log.info("Searching task by your id [{}]");
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "Updating a task.")
    @ApiResponses(value ={
        @ApiResponse(code = 200, message = "Tasks updated successfully"),
        @ApiResponse(code = 404, message = "Could not update task.")
    })

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
        log.info("Updating task with id [{}] the new information are: [{}]", id, task);

        return taskService.updateTaskById(task, id);
    }
    
    @ApiOperation(value = "Deleting a task.")
    @ApiResponses(value ={
        @ApiResponse(code = 204, message = "Tasks deleted successfully"),
        @ApiResponse(code = 484, message = "Could not delete task.")
    })

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Deleting task with id [{}]", id);
        return taskService.deleteById(id);
    }
}
