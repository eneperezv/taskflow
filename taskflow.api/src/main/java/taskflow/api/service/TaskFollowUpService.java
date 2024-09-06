package taskflow.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskflow.api.entity.Task;
import taskflow.api.entity.TaskFollowUp;
import taskflow.api.repository.TaskFollowUpRepository;

/*
 * @(#)TaskFollowUpService.java 1.0 04/09/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Service para control de seguimiento de tareas
 *
 * @author eliezer.navarro
 * @version 1.0 | 04/09/2024
 * @since 1.0
 */

@Service
public class TaskFollowUpService {
	
	@Autowired
	TaskFollowUpRepository taskFollowUpRepository;

	public List<TaskFollowUp> getAllSeguimientos(Long idtask) {
		return taskFollowUpRepository.getAllSeguimientos(idtask);
	}

	public TaskFollowUp save(TaskFollowUp taskFollowUp) {
		return taskFollowUpRepository.save(taskFollowUp);
	}

}
