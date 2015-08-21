package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryMysql {
	
	public static Connection getConexao() {
		Propriedade.setPath(System.getProperty("user.dir") + "/propriedade/conexao.propertie");
		String url = Propriedade.getValor("url");
		String usuario = Propriedade.getValor("usuario");
		String senha = Propriedade.getValor("senha");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,usuario,senha);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void fecharConexao(Connection conexao) {
		if(conexao != null) {
			try {
				conexao.close();
				conexao = null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
