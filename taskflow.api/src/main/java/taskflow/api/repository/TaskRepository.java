package taskflow.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskflow.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
