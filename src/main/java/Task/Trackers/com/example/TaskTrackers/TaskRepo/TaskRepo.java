package Task.Trackers.com.example.TaskTrackers.TaskRepo;

import Task.Trackers.com.example.TaskTrackers.TaskModel.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Long> {
    List<Tasks> findByStatus(String status);
    List<Tasks> findByPriority(String Priority);
    Optional<Tasks> findByTitle(String title);


}
