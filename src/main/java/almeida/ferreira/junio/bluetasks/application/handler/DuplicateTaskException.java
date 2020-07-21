package almeida.ferreira.junio.bluetasks.application.handler;

@SuppressWarnings("serial")
public class DuplicateTaskException extends Exception {

	public DuplicateTaskException(String message) {
		super(message);
	}

	public DuplicateTaskException(Throwable cause) {
		super(cause);
	}

	public DuplicateTaskException(String message, Throwable cause) {
		super(message, cause);
	}
}
