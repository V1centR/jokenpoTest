package br.com.jokenpo.model;

public class Player {
	
	
	private String nome;
	private String hand;
	private String status;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	  public String toString() {
	    return String.format(
	        "{nome='%s', hand='%s',status='%s'}",
	        nome, hand, status);
	  }
}
