package model;

import java.util.Calendar;

public class DespesaBean {
	
	private int iddespesa;
	private Double vldespesa;
	private Calendar dthoraentrada;
	private Calendar dthorasaida;
	private String nomehotel;
	private String cidadehotel;
	private int idviagem;
	
	public int getIddespesa() {
		return iddespesa;
	}
	
	public void setIddespesa(int iddespesa) {
		this.iddespesa = iddespesa;
	}
	
	public Double getVldespesa() {
		return vldespesa;
	}
	
	public void setVldespesa(Double vldespesa) {
		this.vldespesa = vldespesa;
	}
	
	public Calendar getDthoraentrada() {
		return dthoraentrada;
	}
	
	public void setDthoraentrada(Calendar dthoraentrada) {
		this.dthoraentrada = dthoraentrada;
	}
	
	public Calendar getDthorasaida() {
		return dthorasaida;
	}
	
	public void setDthorasaida(Calendar dthorasaida) {
		this.dthorasaida = dthorasaida;
	}
	
	public String getNomehotel() {
		return nomehotel;
	}
	
	public void setNomehotel(String nomehotel) {
		this.nomehotel = nomehotel;
	}
	
	public String getCidadehotel() {
		return cidadehotel;
	}
	
	public void setCidadehotel(String cidadehotel) {
		this.cidadehotel = cidadehotel;
	}
	
	public int getIdviagem() {
		return idviagem;
	}
	
	public void setIdviagem(int idviagem) {
		this.idviagem = idviagem;
	}

}
