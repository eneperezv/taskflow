package taskflow.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import taskflow.api.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByUsername(String username);
	
	List<User> findAll();
	
	@Query(value = "SELECT * FROM dbo_users u WHERE u.username = :usuario", nativeQuery = true)
	User findByUsuario(String usuario);

}
