package model;

import java.util.Calendar;

public class ViagemBean {
	
	private int idviagem;
	private String tipo;	//desenvolvimento, produto, comerciais, relacionamento.
	private Calendar dtinicio;
	private Calendar dtencerramento;
	private String cidade;
	private String uf;
	private Double vldiaria;
	private String nomecolaborador;
	private String nomecliente;
	private Double totaldespesas;
	
	public int getIdviagem() {
		return idviagem;
	}
	
	public void setIdviagem(int idviagem) {
		this.idviagem = idviagem;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Calendar getDtinicio() {
		return dtinicio;
	}
	
	public void setDtinicio(Calendar dtinicio) {
		this.dtinicio = dtinicio;
	}
	
	public Calendar getDtencerramento() {
		return dtencerramento;
	}
	
	public void setDtencerramento(Calendar dtencerramento) {
		this.dtencerramento = dtencerramento;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Double getVldiaria() {
		return vldiaria;
	}
	
	public void setVldiaria(Double vldiaria) {
		this.vldiaria = vldiaria;
	}
	
	public String getNomecolaborador() {
		return nomecolaborador;
	}
	
	public void setNomecolaborador(String nomecolaborador) {
		this.nomecolaborador = nomecolaborador;
	}
	
	public String getNomecliente() {
		return nomecliente;
	}
	
	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}
	
	public Double getTotaldespesas() {
		return totaldespesas;
	}
	
	public void setTotaldespesas(Double totaldespesas) {
		this.totaldespesas = totaldespesas;
	}
	
}
