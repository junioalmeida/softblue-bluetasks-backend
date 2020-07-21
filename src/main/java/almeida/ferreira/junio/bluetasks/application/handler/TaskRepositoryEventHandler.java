package almeida.ferreira.junio.bluetasks.application.handler;

import static almeida.ferreira.junio.bluetasks.utils.LoggerUtils.getLoggerFromClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import almeida.ferreira.junio.bluetasks.domain.task.Task;
import almeida.ferreira.junio.bluetasks.domain.task.TaskRepository;;

@Component
@RepositoryEventHandler(Task.class)
public class TaskRepositoryEventHandler {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@HandleBeforeSave
	@HandleBeforeCreate
	public void handle(Task task) throws DuplicateTaskException{
		
		Task taskBD = taskRepository.findByDescription(task.getDescription());
		
		if(taskBD != null) {
			if(task.getId() != taskBD.getId()) {
				throw new DuplicateTaskException("Já existe uma tarefa com a mesma descrição.");
			}
		}
		
		getLoggerFromClass(TaskRepositoryEventHandler.class).info("Task is Valid...OK");
	}
	
}
