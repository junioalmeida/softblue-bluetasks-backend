package almeida.ferreira.junio.bluetasks.domain.task;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import almeida.ferreira.junio.bluetasks.domain.user.AppUser;
import almeida.ferreira.junio.bluetasks.domain.user.AppUserRepository;

@Component
public class TaskListener {
	
	private static AppUserRepository appUserRepository;
	
	@Autowired
	private void init(AppUserRepository repo) {
		appUserRepository = repo;
	}
	
	@PrePersist
	public void onPrePersistTask(Task t) {
		
		if(t.getAppUser() == null) {
			String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
			AppUser user = appUserRepository.findByUsername(loggedUsername);
			
			if(user == null) {
				throw new EntityNotFoundException("O usuário " + loggedUsername + " não foi encontrado.");
			}
			
			t.setAppUser(user);
		}
	}
}
