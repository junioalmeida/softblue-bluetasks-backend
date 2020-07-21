package almeida.ferreira.junio.bluetasks.domain.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class AppUser implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter 
	private Integer id;
	
	@NotEmpty(message = "O nome de usuário é obrigatório.")
	@Getter 
	@Setter 
	private String username;
	
	@NotEmpty(message = "A senha é obrigatória.")
	@Getter 
	@Setter
	private String password;
	
	@NotEmpty(message = "O nome de exibição é obrigatório")
	@Getter 
	@Setter
	private String displayName;

	public AppUser(String username, String password, String displayName) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}
}
