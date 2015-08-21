package model;

import java.io.FileInputStream;
import java.util.Properties;

public class Propriedade {
	
	public static String path;
	
	public static void setPath(String caminhoarquivo) {
		path = caminhoarquivo;
	}
	
	public static String getValor(String chave) {
		FileInputStream fis = null;
		String valor = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(path);
			prop.load(fis);
			valor = prop.getProperty(chave);
			if(fis != null) fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return valor;
	}
	
}
