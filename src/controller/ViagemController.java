package controller;

import java.util.ArrayList;
import java.util.Calendar;

import model.ViagemBean;
import model.ViagemDao;

public class ViagemController {
	
	public static ArrayList<ViagemBean> listarPorDestino(String cidade, String uf) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		ArrayList<ViagemBean> pordestino = new ArrayList<ViagemBean>();
		for(ViagemBean item : todos) {
			if(item.getCidade().equals(cidade) && item.getUf().equals(uf)) {
				pordestino.add(item);
			}
		}
		return pordestino;
	}
	
	public static ArrayList<ViagemBean> listarPorColaborador(String colaborador) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		ArrayList<ViagemBean> porcolaborador = new ArrayList<ViagemBean>();
		for(ViagemBean item : todos) {
			if(item.getNomecolaborador().equals(colaborador)) {
				porcolaborador.add(item);
			}
		}
		return porcolaborador;
	}
	
	public static ArrayList<ViagemBean> listarPorCliente(String cliente) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		ArrayList<ViagemBean> porcliente = new ArrayList<ViagemBean>();
		for(ViagemBean item : todos) {
			if(item.getNomecliente().equals(cliente)) {
				porcliente.add(item);
			}
		}
		return porcliente;
	}
	
	public static Double saldoDisponivel(String cidade, String uf, int idviagem) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		double saldo = 0;
		for(ViagemBean item : todos) {
			if((item.getCidade().equals(cidade) && item.getUf().equals(uf)) || 
					item.getIdviagem() == idviagem) {
				saldo = saldo + (item.getVldiaria() - item.getTotaldespesas());
			}
		}
		return saldo;
	}
	
	public static ArrayList<ViagemBean> listarPorPeriodo(Calendar data1, Calendar data2) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		ArrayList<ViagemBean> porPeriodo = new ArrayList<ViagemBean>();
		for(ViagemBean item : todos) {
			if(item.getDtinicio().after(data1) && item.getDtencerramento().before(data2)) {
				porPeriodo.add(item);
			}
		}
		return porPeriodo;
	}
	
	public static ArrayList<ViagemBean> listarPorMesAno(Calendar data) {
		ArrayList<ViagemBean> todos = ViagemDao.listar();
		ArrayList<ViagemBean> porMesAno = new ArrayList<ViagemBean>();
		for(ViagemBean item : todos) {
			if(item.getDtinicio().get(Calendar.MONTH) == data.get(Calendar.MONTH) && 
					item.getDtinicio().get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
				porMesAno.add(item);
			}
		}
		return porMesAno;
	}
	
}
