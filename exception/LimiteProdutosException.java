package exception;

public class LimiteProdutosException extends Exception {
  private String message;

  public LimiteProdutosException (String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
