package Task.Trackers.com.example.TaskTrackers.Controller;


import Task.Trackers.com.example.TaskTrackers.TaskModel.Tasks;
import Task.Trackers.com.example.TaskTrackers.TaskService.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //To add new task i will use post mapping
    @PostMapping("/addTask")
    public void addTask(@RequestBody Tasks newTask){
        taskService.addNewTask(newTask);
    }
    //To Display all tasks i will use get mapping
    @GetMapping("/allTask")
    public List<Tasks> getAllTasks(){
        return taskService.getAllTasks();
    }

    @DeleteMapping(path = "/deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") long taskId ){
                taskService.deleteTask(taskId);
    }
    @PutMapping(path = "/editTask/{taskId}")
    public void updateTask(@PathVariable("taskId") long taskId ,@RequestBody Tasks updatedTask){
            taskService.updateTask(taskId , updatedTask);
    }

    @GetMapping("/getTask/{taskId}")
    public Tasks getTask(@PathVariable("taskId") Long taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/getTasksByPriority/{Priority}")
    public List<Tasks> getTaskByPriority(@PathVariable("Priority") String Priority){
        return taskService.findByPriority(Priority);
    }

    @GetMapping("/getTasksByStatus/{Status}")
    public List<Tasks> getTaskByStatus(@PathVariable("Status") String Status){
        return taskService.findByStatus(Status);
    }

}
