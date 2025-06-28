package model;
import exception.LimiteProdutosException;
import exception.ProdutoInexistenteException;
import exception.ProdutoRepetidoException;

public interface IProdutosData {
  void criaProduto(String codigo, String nome, double preco)  throws LimiteProdutosException, ProdutoRepetidoException, ProdutoInexistenteException;
  
  String getProdutoInfo(String codigo) throws ProdutoInexistenteException;
  
  void atualizaProduto(String codigo, String novoNome, double novoPreco) throws ProdutoInexistenteException;
  
  void deletaProduto (String codigo) throws ProdutoInexistenteException;

  String[] getListaProdutos () throws ProdutoInexistenteException;

  int getQuantidadeProdutos ();
}
