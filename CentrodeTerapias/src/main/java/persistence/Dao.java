package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

/**
 * Classe ancestral de acesso a base de dados
 * @author itamar
 *
 */

public class Dao { 
	
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	CallableStatement cstmt;
	
	private final String URL = "jdbc:mysql://15.235.55.109:3306/prefe3015_prefnil"; // URL Prefeitura 
//	private final String URL = "jdbc:mysql://15.235.55.109:3306/protagoras_prefnil"; // URL Protagoras
	
	private final String USER = "prefe3015_protagoras"; // Usuario Prefeitura 
	private final String PASS = "Protagoras23*/"; // Usuario Prefeitura 
	
//	private final String USER = "protagoras_protagoras"; // Usuario protagoras 
//	private final String PASS = "Protagoras23*/"; // Usuario Perdido 
	
	public String caminho(){
		return URL;
	}

	protected void open() throws Exception{
		
		Locale.setDefault(new Locale("pt", "BR"));
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL, USER, PASS);
	}
	
	protected void close() throws Exception{
		con.close();
	}

}
