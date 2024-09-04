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
@Table(name="dbo_task_followups")
public class TaskFollowUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="id_task_followup", unique=true, nullable=false)
	private Long id;

	@JoinColumn(name = "id_task", nullable = false)
	@ManyToOne(targetEntity=Task.class, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Task task;
	
	@Column(name="description", length = 1500)
	private String description;
	
	@Column(name="date_create", length = 10)
	private String dateCreate;

	@JoinColumn(name = "id_usuario", nullable = false)
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	public TaskFollowUp() {
		
	}

	public TaskFollowUp(Task task, String description, String dateCreate, User user) {
		super();
		this.task = task;
		this.description = description;
		this.dateCreate = dateCreate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TaskFollowUp [id=" + id + ", task=" + task + ", description=" + description + ", dateCreate="
				+ dateCreate + ", user=" + user + "]";
	}

}
