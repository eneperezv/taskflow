package taskflow.api.controller;

/*
 * @(#)TaskFollowUpController.java 1.0 04/09/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Controller para control de seguimiento de tareas
 *
 * @author eliezer.navarro
 * @version 1.0 | 04/09/2024
 * @since 1.0
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taskflow.api.entity.ErrorDetails;
import taskflow.api.entity.TaskFollowUp;
import taskflow.api.service.TaskFollowUpService;

@RestController
@RequestMapping("/api/v1/taskflow")
public class TaskFollowUpController {
	
	@Autowired
	TaskFollowUpService taskFollowUpService;
	
	@GetMapping("/task/followup/{idtask}")
	public ResponseEntity<?> taskGUGetAll(@PathVariable("idtask") Long idtask){
		List<TaskFollowUp> lista = new ArrayList<TaskFollowUp>();
		try{
			taskFollowUpService.getAllSeguimientos(idtask).forEach(lista::add);
			if(lista.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"NO CONTENT");
				return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/task/followup")
	public ResponseEntity<?> taskFollowUpCreate(TaskFollowUp taskFollowUp){
		TaskFollowUp savedTaskFU;
		try{
			savedTaskFU = taskFollowUpService.save(taskFollowUp);
			if(savedTaskFU == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Task not created.");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TaskFollowUp>(savedTaskFU, HttpStatus.CREATED);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/task/followup")
	public ResponseEntity<?> taskUpdate(@RequestBody TaskFollowUp taskFollowUp){
		TaskFollowUp savedTaskFU;
		try {
			savedTaskFU = taskFollowUpService.save(taskFollowUp);
			if(savedTaskFU == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Task not updated.");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TaskFollowUp>(savedTaskFU, HttpStatus.OK);
		}catch(Exception e) {
			ErrorDetails err = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
