package controller;

import exception.LimiteProdutosException;
import exception.ProdutoInexistenteException;
import exception.ProdutoRepetidoException;
import model.*;

public final class ProdutosController {
  private ProdutosData produtos;
  
  public ProdutosController (int quantidadeProdutos) {
    produtos = new ProdutosData(quantidadeProdutos);
  }

  public void criaProduto (String codigo, String nome, double preco) throws LimiteProdutosException, ProdutoRepetidoException, ProdutoInexistenteException {
    produtos.criaProduto(codigo, nome, preco);  
  }
  
  public String getProduto (String codigo) throws ProdutoInexistenteException {
    return produtos.getProdutoInfo(codigo);
  }

  public void atualizaProduto (String codigo, String novoNome, double novoPreco) throws ProdutoInexistenteException {
    produtos.atualizaProduto(codigo, novoNome, novoPreco);
  }  

  public void deletaProduto (String codigo) throws ProdutoInexistenteException {
    produtos.deletaProduto(codigo);  
  }

  public String[] getListaProdutos () throws ProdutoInexistenteException {
    return produtos.getListaProdutos();
  }

  public int getQuantidadeProdutos () {
    return produtos.getQuantidadeProdutos();
  }
}
