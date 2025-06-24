package exception;

public class OpcaoInvalidaException extends Exception {
  private String message;
  
  public OpcaoInvalidaException (String message) {
    this.message = message;
  }

  public String getMessage () {
    return this.message;
  }
}