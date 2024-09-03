package taskflow.api.service;

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
