package packt.book.jee.eclipse.ch4.bean;

public class EnrolmentFullException extends Exception {

	public EnrolmentFullException() {
		// TODO Auto-generated constructor stub
	}

	public EnrolmentFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EnrolmentFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EnrolmentFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EnrolmentFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return "Too many people. EnrolmentFullException";
	}

}
