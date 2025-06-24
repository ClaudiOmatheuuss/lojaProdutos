package exception;

public class ProdutoInexistenteException extends Exception {
  private String message;

  public ProdutoInexistenteException (String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
