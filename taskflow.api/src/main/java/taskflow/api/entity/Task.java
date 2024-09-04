package taskflow.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="dbo_tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="id_task", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name", length = 100)
	private String name;
	
	@Column(name="description", length = 1500)
	private String description;
	
	@Column(name="date_create", length = 10)
	private String dateCreate;
	
	@Column(name="date_expiration", length = 10)
	private String dateExpiration;

	@JoinColumn(name = "id_usuario", nullable = false)
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@Column(name="status", length = 10)
	private String status;
	
	public Task() {
		
	}

	public Task(String name, String description, String dateCreate, String dateExpiration, User user, String status) {
		super();
		this.name = name;
		this.description = description;
		this.dateCreate = dateCreate;
		this.dateExpiration = dateExpiration;
		this.user = user;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", dateCreate=" + dateCreate
				+ ", dateExpiration=" + dateExpiration + ", user=" + user + ", status=" + status + "]";
	}

}
