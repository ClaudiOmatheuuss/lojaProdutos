package model;

public final class Produto {
  private String codigo;
  private String nome;
  private double preco;
  private int id;

  public Produto(String codigo, String nome, double preco, int id) {
    this.codigo = codigo;
    this.nome = nome;
    this.preco = preco;
    this.id = id;
  }

  public String getCodigo () {
    return this.codigo;
  }

  public String getNome () {
    return this.nome;
  }

  public double getPreco () {
    return this.preco;
  }

  public int getId () {
    return this.id;
  }

  public String[] getInfo () {
    String[] informacoes = new String[3];
    informacoes[0] = getCodigo();
    informacoes[1] = getNome();
    informacoes[2] = String.valueOf(getPreco());
    return informacoes;
  }

  public void setNome(String novoNome) {
    this.nome = novoNome;
  }

  public void setPreco(double novoPreco) {
    this.preco = novoPreco;
  }
}
