package almeida.ferreira.junio.bluetasks.domain.task;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import almeida.ferreira.junio.bluetasks.domain.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@EntityListeners(TaskListener.class)
@NoArgsConstructor
public class Task implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@NotEmpty(message = "A descrição da tarefa é obrigatória.")
	@Length(min = 3, max = 40, message = "O tamanho da descrição é inválido.")
	@Getter
	@Setter
	private String description;
	
	@NotNull(message = "A data é obrigatória.")
	@FutureOrPresent(message = "A data não pode estar no passado.")
	@Getter
	@Setter
	private LocalDate whenToDo;
	
	@Getter
	@Setter
	private Boolean done = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	@JsonIgnore
	@Getter
	@Setter
	private AppUser appUser;

	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}
}
