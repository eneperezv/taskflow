package taskflow.api.repository;

/*
 * @(#)TaskRepository.java 1.0 03/09/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interfaz Repository para la gestion de persistencia de Tareas
 *
 * @author eliezer.navarro
 * @version 1.0 | 03/09/2024
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;

import taskflow.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
