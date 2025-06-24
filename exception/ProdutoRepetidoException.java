package exception;

public class ProdutoRepetidoException extends Exception {
  private String message;
  
  public ProdutoRepetidoException (String message) {
    this.message = message;
  }

  public String getMessage () {
    return this.message;
  }
}
