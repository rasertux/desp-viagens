package controller;

import java.util.ArrayList;

import model.DespesaBean;
import model.DespesaDao;

public class DespesaController {
	
	public static ArrayList<DespesaBean> listarMovimentacoes(String cidade, int idviagem) {
		ArrayList<DespesaBean> todos = DespesaDao.listar();
		ArrayList<DespesaBean> porLocalOuIdviagem = new ArrayList<DespesaBean>();
		for(DespesaBean item : todos) {
			if(item.getCidadehotel().equals(cidade) || item.getIdviagem() == idviagem) {
				porLocalOuIdviagem.add(item);
			}
		}
		return porLocalOuIdviagem;
	}
	
}
