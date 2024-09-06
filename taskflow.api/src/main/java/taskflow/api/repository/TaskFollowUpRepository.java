package taskflow.api.repository;

/*
 * @(#)TaskFollowUpRepository.java 1.0 04/09/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interface Repository para control de seguimiento de tareas y su persistencia JPA
 *
 * @author eliezer.navarro
 * @version 1.0 | 04/09/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import taskflow.api.entity.TaskFollowUp;

public interface TaskFollowUpRepository extends JpaRepository<TaskFollowUp,Long> {
	
	@Query(value = "SELECT a.* FROM dbo_task_followups a WHERE a.id_task = :idtask", nativeQuery = true)
	List<TaskFollowUp> getAllSeguimientos(Long idtask);

}
