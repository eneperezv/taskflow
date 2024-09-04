package taskflow.api.service;

/*
 * @(#)UserService.java 1.0 03/09/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Service para la gestion de Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 03/09/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskflow.api.entity.User;
import taskflow.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findByUsuario(String usuario) {
		return userRepository.findByUsuario(usuario);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

}
