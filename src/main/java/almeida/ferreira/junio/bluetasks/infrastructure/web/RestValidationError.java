package almeida.ferreira.junio.bluetasks.infrastructure.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestValidationError {
	
	@Getter
	private String errors;
	
	public static RestValidationError fromValidationError(Errors erros) {
		StringBuilder sb = new StringBuilder();
		
		for (ObjectError error : erros.getAllErrors()) {
			sb.append(error.getDefaultMessage()).append("| ");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		
		return new RestValidationError(sb.toString());
	}	
	
	public static RestValidationError fromMessage(String message) {
		return new RestValidationError(message);
	}
}
