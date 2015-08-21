package view;

import java.util.ArrayList;
import java.util.Calendar;

import helper.DataHelper;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DespesaController;
import controller.ViagemController;
import model.DespesaBean;
import model.DespesaDao;
import model.ViagemBean;
import model.ViagemDao;

public class Teste {
	
	public static void main(String[] args) {
		
		menu();
		
	}
	
	public static void menu() {
		int op = 0;
		String opcoes = "1-Cadastrar Viagem\n2-Alterar viagem\n3-Listar viagens por destino\n"
				+ "4-Listar viagens por periodo\n5-Listar viagens por mês/ano\n6-Listar viagens por colaborador\n"
				+ "7-Listar viagens por cliente\n8-Total em diarias para um colaborador\n9-Cadastrar uma despesa\n"
				+ "10-Alterar uma despesa\n11-Remover uma despesa\n12-Listar movimentações de despesa\n"
				+ "13-Saldo disponivel para uma viagem\n14-Sair";
		while(op != 14) {
			op = new Integer(JOptionPane.showInputDialog(opcoes));
			switch(op) {
			case 1:cadastrarViagem();break;
			case 2:alterarViagem();break;
			case 3:listarViagemPorDestino();break;
			case 4:listarViagemPorPeriodo();break;
			case 5:listarViagemPorMesAno();break;
			case 6:listarViagemPorColaborador();break;
			case 7:listarViagemPorCliente();break;
			case 8:totalDiariasColaborador();break;
			case 9:cadastrarDespesa();break;
			case 10:alterarDespesa();break;
			case 11:removerDespesa();break;
			case 12:listarMovDespesa();break;
			case 13:saldoDisponivelViagem();break;
			}
		}
	}
	
	public static void cadastrarViagem() {
		ViagemBean viagem = setViagem();
		if(ViagemDao.cadastrar(viagem)) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
		}
	}
	
	public static void alterarViagem() {
		int idviagem = new Integer(JOptionPane.showInputDialog("Id a alterar:"));
		ViagemBean viagem = setViagem();
		if(ViagemDao.alterar(idviagem, viagem)) {
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao alterar!");
		}
	}
	
	public static ViagemBean setViagem() {
		ViagemBean viagem = new ViagemBean();
		String[] tipos = {"Desenvolvimento", "Produto", "Comerciais", "Relacionamento"}; 
		viagem.setTipo((String)JOptionPane.showInputDialog(null, "Tipo:", "Tipo", JOptionPane.PLAIN_MESSAGE, null, tipos, ""));
		viagem.setDtinicio(DataHelper.StringToCalendar("dd/MM/yyyy", JOptionPane.showInputDialog("Data de inicio: dd/mm/yyyy")));
		viagem.setDtencerramento(DataHelper.StringToCalendar("dd/MM/yyyy", JOptionPane.showInputDialog("Data de encerramento: dd/mm/yyyy")));
		viagem.setCidade(JOptionPane.showInputDialog("Cidade:"));
		viagem.setUf(JOptionPane.showInputDialog("UF:"));
		viagem.setVldiaria(new Double(JOptionPane.showInputDialog("Valor da diaria:")));
		viagem.setNomecolaborador(JOptionPane.showInputDialog("Nome do colaborador:"));
		viagem.setNomecliente(JOptionPane.showInputDialog("Nome do cliente:"));
		return viagem;
	}
	
	public static void listarViagemPorDestino() {
		String cidade = JOptionPane.showInputDialog("Cidade:");
		String uf = JOptionPane.showInputDialog("UF:");
		ArrayList<ViagemBean> viagem = ViagemController.listarPorDestino(cidade, uf);
		listarViagem(viagem);
	}
	
	public static void listarViagemPorPeriodo() {
		Calendar data1 = DataHelper.StringToCalendar("dd/MM/yyyy", JOptionPane.showInputDialog("Data Inicial:"));
		Calendar data2 = DataHelper.StringToCalendar("dd/MM/yyyy", JOptionPane.showInputDialog("Data Final:"));
		ArrayList<ViagemBean> viagem = ViagemController.listarPorPeriodo(data1, data2);
		listarViagem(viagem);
	}
	
	public static void listarViagemPorMesAno() {
		Calendar data = DataHelper.StringToCalendar("MM/yyyy", JOptionPane.showInputDialog("Mês/Ano:"));
		ArrayList<ViagemBean> viagem = ViagemController.listarPorMesAno(data);
		listarViagem(viagem);
	}
	
	public static void listarViagemPorColaborador() {
		String colaborador = JOptionPane.showInputDialog("Colaborador:");
		ArrayList<ViagemBean> viagem = ViagemController.listarPorColaborador(colaborador);
		listarViagem(viagem);
	}
	
	public static void listarViagemPorCliente() {
		String cliente = JOptionPane.showInputDialog("Cliente:");
		ArrayList<ViagemBean> viagem = ViagemController.listarPorCliente(cliente);
		listarViagem(viagem);
	}
	
	public static void totalDiariasColaborador() {
		String colaborador = JOptionPane.showInputDialog("Colaborador:");
		double saldo = ViagemDao.totalDiariasColaborador(colaborador);
		JOptionPane.showMessageDialog(null, "Colaborador: " + colaborador + "\nTotal diarias: " + saldo);
	}
	
	public static void listarViagem(ArrayList<ViagemBean> todos){
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addRow(new String[]{"IdViagem","Tipo","DtInicio","DtEncerramento","Cidade", "Uf", "VlDiaria", "NomeColaborador", "NomeCliente", 
				"TotalDespesas"});
		if(todos == null){
		  modelo.addRow(new String[]{"","","","","","","","","",""});	
		}else{
			for(ViagemBean item:todos){
				String dados[] = new String[10];
				dados[0] = item.getIdviagem()+"";
				dados[1] = item.getTipo();
				dados[2] = DataHelper.CalendarToString("dd/MM/yyyy",
						   item.getDtinicio())+"";
				dados[3] = DataHelper.CalendarToString("dd/MM/yyyy",
						   item.getDtencerramento())+"";
				dados[4] = item.getCidade();
				dados[5] = item.getUf();
				dados[6] = item.getVldiaria()+"";
				dados[7] = item.getNomecolaborador();
				dados[8] = item.getNomecliente();
				dados[9] = item.getTotaldespesas()+"";
				modelo.addRow(dados);
			}
		}
		JTable tabela = new JTable();
		tabela.setModel(modelo);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(100);
		tabela.setEnabled(false);
		JOptionPane.showMessageDialog(null, tabela);
	}
	
	public static void cadastrarDespesa() {
		DespesaBean despesa = setDespesa();
		if(DespesaDao.cadastrar(despesa)) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
		}
	}
	
	public static DespesaBean setDespesa() {
		DespesaBean despesa = new DespesaBean();
		despesa.setVldespesa(new Double(JOptionPane.showInputDialog("Valor da despesa:")));
		despesa.setDthoraentrada(DataHelper.StringToCalendar("dd/MM/yyyy HH:mm", 
				JOptionPane.showInputDialog("Data e hora entrada: dd/mm/yyyy hh:mm")));
		despesa.setDthorasaida(DataHelper.StringToCalendar("dd/MM/yyyy HH:mm", 
				JOptionPane.showInputDialog("Data hora saida: dd/mm/yyyy hh:mm")));
		despesa.setNomehotel(JOptionPane.showInputDialog("Hotel:"));
		despesa.setCidadehotel(JOptionPane.showInputDialog("Cidade:"));
		despesa.setIdviagem(new Integer(JOptionPane.showInputDialog("IdViagem:")));
		return despesa;
	}
	
	public static void alterarDespesa() {
		int iddespesa = new Integer(JOptionPane.showInputDialog("Id a alterar:"));
		DespesaBean despesa = setDespesa();
		if(DespesaDao.alterar(iddespesa, despesa)) {
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao alterar!");
		}
	}
	
	public static void removerDespesa() {
		int iddespesa = new Integer(JOptionPane.showInputDialog("Id a remover:"));
		if(DespesaDao.remover(iddespesa)) {
			JOptionPane.showMessageDialog(null, "Removido com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao remover!");
		}
	}
	
	public static void listarMovDespesa() {
		int iddespesa = new Integer(JOptionPane.showInputDialog("Id viagem:"));
		ArrayList<DespesaBean> despesa = DespesaController.listarMovimentacoes(null, iddespesa);
		listarDespesa(despesa);
	}
	
	public static void listarDespesa(ArrayList<DespesaBean> todos){
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addRow(new String[]{"IdDespesa","VlDespesa","DtHoraEntrada","DtHoraSaida","NomeHotel", "CidadeHotel", "IdViagem"});
		if(todos == null){
		  modelo.addRow(new String[]{"","","","","","",""});	
		}else{
			for(DespesaBean item:todos){
				String dados[] = new String[10];
				dados[0] = item.getIddespesa()+"";
				dados[1] = item.getVldespesa()+"";
				dados[2] = DataHelper.CalendarToString("dd/MM/yyyy HH:mm",
						   item.getDthoraentrada())+"";
				dados[3] = DataHelper.CalendarToString("dd/MM/yyyy HH:mm",
						   item.getDthorasaida())+"";
				dados[4] = item.getNomehotel();
				dados[5] = item.getCidadehotel();
				dados[6] = item.getIdviagem()+"";
				modelo.addRow(dados);
			}
		}
		JTable tabela = new JTable();
		tabela.setModel(modelo);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
		tabela.setEnabled(false);
		JOptionPane.showMessageDialog(null, tabela);
	}
	
	public static void saldoDisponivelViagem() {
		int idviagem = new Integer(JOptionPane.showInputDialog("Id viagem:"));
		double saldo = ViagemController.saldoDisponivel(null, null, idviagem);
		JOptionPane.showMessageDialog(null, "Idviagem: " + idviagem + "\nSaldo: " + saldo);
	}
	
}
