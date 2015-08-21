package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ViagemDao {
	
	private static Connection conexao;
	
	public static boolean cadastrar(ViagemBean viagem) {
		String sql = "insert into viagem" + "(tipo, dtinicio, dtencerramento, cidade, uf, vldiaria, nomecolaborador, nomecliente)" + 
	"values" + "(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, viagem.getTipo());
			stmt.setTimestamp(2, new Timestamp(viagem.getDtinicio().getTimeInMillis()));
			stmt.setTimestamp(3, new Timestamp(viagem.getDtencerramento().getTimeInMillis()));
			stmt.setString(4, viagem.getCidade());
			stmt.setString(5, viagem.getUf());
			stmt.setDouble(6, viagem.getVldiaria());
			stmt.setString(7, viagem.getNomecolaborador());
			stmt.setString(8, viagem.getNomecliente());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
	public static boolean alterar(int idviagem, ViagemBean viagem) {
		String sql = "update viagem set tipo=?, dtinicio=?, dtencerramento=?, cidade=?, uf=?, vldiaria=?, " +
	"nomecolaborador=?, nomecliente=?" + "where idviagem=?";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, viagem.getTipo());
			stmt.setTimestamp(2, new Timestamp(viagem.getDtinicio().getTimeInMillis()));
			stmt.setTimestamp(3, new Timestamp(viagem.getDtencerramento().getTimeInMillis()));
			stmt.setString(4, viagem.getCidade());
			stmt.setString(5, viagem.getUf());
			stmt.setDouble(6, viagem.getVldiaria());
			stmt.setString(7, viagem.getNomecolaborador());
			stmt.setString(8, viagem.getNomecliente());
			stmt.setInt(9, idviagem);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
	}
	
	public static ArrayList<ViagemBean> listar() {
		ArrayList<ViagemBean> viagemlist = new ArrayList<ViagemBean>();
		String sql = "select * from viagem";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ViagemBean viagem = new ViagemBean();
				viagem.setIdviagem(rs.getInt("idviagem"));
				viagem.setTipo(rs.getString("tipo"));
				Calendar datainicio = Calendar.getInstance();
				datainicio.setTime(rs.getTimestamp("dtinicio"));
				viagem.setDtinicio(datainicio);
				Calendar dataencerramento = Calendar.getInstance();
				dataencerramento.setTime(rs.getTimestamp("dtencerramento"));
				viagem.setDtencerramento(dataencerramento);
				viagem.setCidade(rs.getString("cidade"));
				viagem.setUf(rs.getString("uf"));
				viagem.setVldiaria(rs.getDouble("vldiaria"));
				viagem.setNomecolaborador(rs.getString("nomecolaborador"));
				viagem.setNomecliente(rs.getString("nomecliente"));
				viagem.setTotaldespesas(rs.getDouble("totaldespesas"));
				viagemlist.add(viagem);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
		return viagemlist;
	}
	
	public static Double totalDiariasColaborador(String colaborador) {
		double totaldiarias = 0;
		String sql = "select sum(vldiaria) as totaldiarias from viagem where nomecolaborador=?";
		try {
			if(conexao == null || conexao.isClosed()) conexao = FactoryMysql.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, colaborador);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				totaldiarias = rs.getDouble("totaldiarias");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			FactoryMysql.fecharConexao(conexao);
		}
		return totaldiarias;
	}
	
}
