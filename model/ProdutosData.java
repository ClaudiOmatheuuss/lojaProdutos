package model;

import exception.LimiteProdutosException;
import exception.ProdutoInexistenteException;
import exception.ProdutoRepetidoException;

public final class ProdutosData {
  private Produto[] listaProdutos;
  private int contador;
  
  public ProdutosData(int quantidadeProdutos) {
    listaProdutos = new Produto[quantidadeProdutos];
    contador = 0;
  }

  public void criaProduto(String codigo, String nome, double preco) throws LimiteProdutosException, ProdutoRepetidoException, ProdutoInexistenteException {
    Boolean produtoExiste = false;
    try {
      produtoExiste = getProduto(codigo) instanceof Produto;
    } catch (ProdutoInexistenteException e) {} 

    if (produtoExiste) {
      throw new ProdutoRepetidoException("Já existe um produto com o mesmo codigo, caso deseje alterar selecione a opcao 3.");
    } else if (contador == listaProdutos.length) {
     throw new LimiteProdutosException("Limite de produtos atingido, para aumentar a capacidade reinicie o sistema.");
    }

    listaProdutos[contador] = new Produto(codigo, nome, preco, contador);
    contador++;
  }

  public void criaProduto(String codigo, String nome, double preco, int idProduto) {
    listaProdutos[idProduto] = new Produto(codigo, nome, preco, idProduto);
  }

  public Produto getProduto(String codigo) throws ProdutoInexistenteException {
    Produto p = null;
    for (Produto produto : listaProdutos) {
      if (produto instanceof Produto) {
        if (produto.getCodigo().equals(codigo)) {
          p = produto;
        }
      }
    }

    if (p == null) {
      throw new ProdutoInexistenteException("Produto inexistente, tente novamente.");   
    }
    return p;
  }  

  public String getProdutoInfo (String codigo) throws ProdutoInexistenteException {
    Produto p = getProduto(codigo);
    String[] produtoInfo = p.getInfo();

    String codigoProduto = ("Codigo: " + produtoInfo[0]);
    String nome = ("\nNome: " + produtoInfo[1]);
    String preco = ("\nPreco: " + produtoInfo[2]);

    return codigoProduto + nome + preco;
  }  

  public String[] getListaProdutos () throws ProdutoInexistenteException {
    String[] novaLista = new String[listaProdutos.length];
    String informacoes;
    int i = 0;
    for (Produto produto : listaProdutos) {
      if (produto instanceof Produto) {
        String codigo = produto.getCodigo();
        informacoes = getProdutoInfo(codigo).replace("\n", "     ");
      } else {
        informacoes = null;
      }
      novaLista[i] = informacoes;
      i++;
    }
    return novaLista;
  }

  public int getQuantidadeProdutos() {
    int count = 0;
    for (Produto produto : listaProdutos) {
      if (produto != null) {
       count++;
      }
    }
    return count;
  }

  public void atualizaProduto(String codigo, String novoNome, double novoPreco) throws ProdutoInexistenteException {
    Produto p = getProduto(codigo);
    int idProduto = p.getId();
    criaProduto(codigo, novoNome, novoPreco, idProduto);
  }

  public void deletaProduto(String codigo) throws ProdutoInexistenteException {
    Produto p = getProduto(codigo);
    listaProdutos[p.getId()] = null;
  }

}
