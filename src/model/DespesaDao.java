package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class DespesaDao {
	
	private static Connection conexao;
	
	public static boolean cadastrar(DespesaBean despesa) {
		String sql = "insert into despesa" + "(vldespesa, dthoraentrada, dthorasaida, nomehotel, cidadehotel, idviagem)" + 
				"values" + "(?, ?, ?, ?, ?, ?)";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, despesa.getVldespesa());
			stmt.setTimestamp(2, new Timestamp(despesa.getDthoraentrada().getTimeInMillis()));
			stmt.setTimestamp(3, new Timestamp(despesa.getDthorasaida().getTimeInMillis()));
			stmt.setString(4, despesa.getNomehotel());
			stmt.setString(5, despesa.getCidadehotel());
			stmt.setInt(6, despesa.getIdviagem());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
	public static boolean alterar(int iddespesa, DespesaBean despesa) {
		String sql = "update despesa set vldespesa=?, dthoraentrada=?, dthorasaida=?, nomehotel=?, cidadehotel=?, idviagem=? where iddespesa=?";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, despesa.getVldespesa());
			stmt.setTimestamp(2, new Timestamp(despesa.getDthoraentrada().getTimeInMillis()));
			stmt.setTimestamp(3, new Timestamp(despesa.getDthorasaida().getTimeInMillis()));
			stmt.setString(4, despesa.getNomehotel());
			stmt.setString(5, despesa.getCidadehotel());
			stmt.setInt(6, despesa.getIdviagem());
			stmt.setInt(7, iddespesa);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
	public static boolean remover(int iddespesa) {
		String sql = "delete from despesa where iddespesa=?";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, iddespesa);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
	public static ArrayList<DespesaBean> listar() {
		ArrayList<DespesaBean> despesalist = new ArrayList<DespesaBean>();
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement("select * from despesa");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DespesaBean despesa = new DespesaBean();
				despesa.setIddespesa(rs.getInt("iddespesa"));
				despesa.setVldespesa(rs.getDouble("vldespesa"));
				Calendar data1 = Calendar.getInstance();
				data1.setTime(rs.getTimestamp("dthoraentrada"));
				despesa.setDthoraentrada(data1);
				Calendar data2 = Calendar.getInstance();
				data2.setTime(rs.getTimestamp("dthorasaida"));
				despesa.setDthorasaida(data2);
				despesa.setNomehotel(rs.getString("nomehotel"));
				despesa.setCidadehotel(rs.getString("cidadehotel"));
				despesa.setIdviagem(rs.getInt("idviagem"));
				despesalist.add(despesa);
			}
			rs.close();
			stmt.close();
			return despesalist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
}
