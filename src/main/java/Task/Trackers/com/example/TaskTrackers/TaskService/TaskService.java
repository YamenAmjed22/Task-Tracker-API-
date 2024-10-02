package Task.Trackers.com.example.TaskTrackers.TaskService;

import Task.Trackers.com.example.TaskTrackers.TaskModel.Tasks;
import Task.Trackers.com.example.TaskTrackers.TaskRepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void addNewTask(Tasks newTask){
        Optional<Tasks> existingTask = taskRepo.findByTitle(newTask.getTitle());
        if ( existingTask.isPresent() ){
            throw new IllegalStateException("The task title is taken!");
        }
        else {
            taskRepo.save(newTask);
        }

    }

    public List<Tasks> getAllTasks() {
        return taskRepo.findAll();
    }

    public void deleteTask(long taskId) {
        boolean exist = taskRepo.existsById(taskId);
        if (!exist){
            throw  new IllegalStateException("This Task Doesn't Exist");
        }
        else {
            taskRepo.deleteById(taskId);

        }
    }

    public void updateTask(Long taskId, Tasks updatedTask) {
        Tasks task = taskRepo.findById(taskId).orElseThrow(() -> new IllegalStateException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        taskRepo.save(task);
    }

    public Tasks getTaskById(Long taskId) {
        Tasks task = taskRepo.findById(taskId).orElseThrow(() -> new IllegalStateException("Task not found"));
        return task;
    }

    public Tasks getTaskByTilte(String taskTitle) {
        Tasks task = taskRepo.findByTitle(taskTitle).orElseThrow(() -> new IllegalStateException("Task not found"));
        return task;
    }

    public List<Tasks> findByPriority(String Priority) {
        List<Tasks> tasks = taskRepo.findByPriority(Priority);
        if (tasks.size()==0){
            throw new IllegalStateException("we don't have any " + Priority + " tasks !");
        }
        else {
            return taskRepo.findByPriority(Priority);
        }
    }

    public List<Tasks> findByStatus(String status) {
        List<Tasks> tasks = taskRepo.findByStatus(status);
        if (tasks.size()==0){
            throw new IllegalStateException("we don't have any " + status + " tasks !");
        }
        else {
            return taskRepo.findByStatus(status);
        }
    }
}

