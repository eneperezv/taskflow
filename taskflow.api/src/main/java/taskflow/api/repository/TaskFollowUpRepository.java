package taskflow.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskflow.api.entity.TaskFollowUp;

public interface TaskFollowUpRepository extends JpaRepository<TaskFollowUp,Long> {

}
