package almeida.ferreira.junio.bluetasks;

import static almeida.ferreira.junio.bluetasks.utils.LoggerUtils.getLoggerFromClass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BluetasksBackendApplication {

	public static void main(String[] args) {
		//TODO Remover dependência gradle runtimeOnly 'com.h2database:h2'
		SpringApplication.run(BluetasksBackendApplication.class, args);
		getLoggerFromClass(BluetasksBackendApplication.class).info("Bluetasks is active.");;
	}

}
