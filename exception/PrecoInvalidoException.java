package exception;

public class PrecoInvalidoException extends Exception {
  private String message;

  public PrecoInvalidoException (String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
