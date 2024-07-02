package persistence;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Ctrl_usuarios;

public class AcessoDao extends Dao {
	public Ctrl_usuarios login(Ctrl_usuarios usu)throws Exception{
		
		Ctrl_usuarios u = new Ctrl_usuarios();
		
		open();
		
		stmt = con.prepareStatement("select id_usu, cpf_usu, login_usu, nome_usu, datanasc_usu, tipo_usu, senha_usu " +
		                            "from ctrl_usuarios where upper(login_usu) = ? and upper(senha_usu) = ?");
		
		stmt.setString(1, usu.getLogin_usu().toUpperCase());
		stmt.setString(2, usu.getSenha_usu().toUpperCase());
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			
			u = new Ctrl_usuarios(rs.getInt("id_usu"), rs.getString("cpf_usu"), rs.getString("login_usu"), rs.getString("nome_usu"), 
					              rs.getString("datanasc_usu"), rs.getString("tipo_usu"), rs.getString("senha_usu"));
			
		}
		
		return u;
		
	}
	
	public List<String> CarregaListadeAcessos(Integer Idsistema, Integer Idusuario, String tipoUsu) throws Exception{
		List<String> lista = new ArrayList<String>();
		
		open();

		stmt = con.prepareStatement("select a.codigo_tela, COALESCE(b.id_usu, 0) as id_usu " +
			                        "from " +
				                    "  ctrl_sistemas_telas a " +
			                        "  left outer join ctrl_usuarios_acesso_telas b on " +
				                    "    (a.codigo_tela  = b.codigo_tela and " +
			                        "     b.id_usu = " + Idusuario + ")" +
				                    "where a.codigo_sistema = " + Idsistema + 
				                    " order by a.codigo_tela ");

		rs = stmt.executeQuery();
		
		while (rs.next()) {
			if (tipoUsu.equalsIgnoreCase("A")) {
				lista.add("false");
			} else if (rs.getInt("id_usu")==0) {
				lista.add("true");
			} else {
				lista.add("false");
			}
		};

		close();
		
		return lista;
	}
	
	public String validaItemUsuario (Integer usu, Integer item) throws Exception{
		
		String sql, disable = "true"; // disabled do menu = true. Desabilitado

		open();
		
		sql = "select count(codigo_tela) as acesso"
		    + " from ctrl_usuarios_acesso_telas"
		    + " where id_usu = " + usu
		    + " and codigo_tela = " + item;
		
		stmt = con.prepareStatement(sql);

		rs = stmt.executeQuery();
		
		while (rs.next()) { // usuario tem acesso, passo false para o "disabled". Menu fica habilitado
			if (rs.getInt("acesso")== 0) {
				disable = "false";
			}
		};

		close();

		return disable;
	}
	
	public Ctrl_usuarios RetornaUsuario(Integer usu) throws Exception{
		Ctrl_usuarios usuario =  new Ctrl_usuarios();
		
		open();
		
		String statement = "select id_usu, cpf_usu, login_usu, nome_usu, datanasc_usu, tipo_usu, senha_usu " + 
                           "from ctrl_usuarios  where id_usu = " + usu;
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		SimpleDateFormat dataMySQL = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dataNormal = new SimpleDateFormat("MM/dd/yyyy");
		
		java.util.Date date;
		
		if (rs.next()) {
			
			date = dataMySQL.parse(rs.getString("datanasc_usu"));					
			
			usuario = new Ctrl_usuarios(rs.getInt("id_usu"), rs.getString("cpf_usu"), 
					                            rs.getString("login_usu"), rs.getString("nome_usu"), 
					                            dataNormal.format(date), rs.getString("tipo_usu"), 
					                            rs.getString("senha_usu"));
		}
		
		close();
		
		return usuario;
	}
	
	public List<Ctrl_usuarios> findAll(Ctrl_usuarios usu) throws Exception{
		List<Ctrl_usuarios> lista =  new ArrayList<Ctrl_usuarios>();
		
		open();
		
		String statement = "select id_usu, cpf_usu, login_usu, nome_usu, datanasc_usu, tipo_usu, senha_usu " + 
                           "from ctrl_usuarios  where 1 = 1 ";
		
		if ((usu.getCpf_usu()!=null)&&(!usu.getCpf_usu().equalsIgnoreCase(""))) {
			statement = statement + " and cpf_usu = '" + usu.getCpf_usu() + "'";
		}
		
		if ((usu.getLogin_usu()!=null)&&(!usu.getLogin_usu().equalsIgnoreCase(""))) {
			statement = statement + " and login_usu like '%" + usu.getLogin_usu() + "%'";
		}
		
		if ((usu.getNome_usu()!=null)&&(!usu.getNome_usu().equalsIgnoreCase(""))) {
			statement = statement + " and nome_usu like '%" + usu.getNome_usu() + "%'";
		}
		
		if ((usu.getTipo_usu()!=null)&&(!usu.getTipo_usu().equalsIgnoreCase(""))) {
			statement = statement + " and tipo_usu = '" + usu.getTipo_usu() + "'";
		}
		
		//System.out.println(statement);
		
		stmt = con.prepareStatement(statement + " order by login_usu");
		
		rs = stmt.executeQuery();
		
		SimpleDateFormat dataMySQL = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dataNormal = new SimpleDateFormat("MM/dd/yyyy");
		
		java.util.Date date;
		
		while (rs.next()) {
			
			date = dataMySQL.parse(rs.getString("datanasc_usu"));					
			
			Ctrl_usuarios u = new Ctrl_usuarios(rs.getInt("id_usu"), rs.getString("cpf_usu"), 
					                            rs.getString("login_usu"), rs.getString("nome_usu"), 
					                            dataNormal.format(date), rs.getString("tipo_usu"), 
					                            rs.getString("senha_usu"));
			lista.add(u);
		}
		
		close();
		
		return lista;
	}
	
	public void gravarusuario(Ctrl_usuarios usu) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date data = new java.sql.Date(sdf.parse(usu.getDatanasc_usu()).getTime());
			
		String smtp = "insert into ctrl_usuarios (id_usu, cpf_usu, login_usu, nome_usu, datanasc_usu, tipo_usu, senha_usu) " +
			          "values (?, ?, ?, ?, ?, ?, ?)";	

		open();	
		
		stmt = con.prepareStatement(smtp);
		
		stmt.setInt(1, 0);
		stmt.setString(2, usu.getCpf_usu());
		stmt.setString(3, usu.getLogin_usu());
		stmt.setString(4, usu.getNome_usu());
		stmt.setDate(5, data);
		stmt.setString(6, usu.getTipo_usu());
		stmt.setString(7, usu.getCpf_usu());
		
		stmt.execute();
		
		close();
	
	}
	
	public Integer alterarusuario(Ctrl_usuarios usu) throws Exception {
		
		final Integer linhasafetadas; 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date data = new java.sql.Date(sdf.parse(usu.getDatanasc_usu()).getTime());
			
		String smtp = "update ctrl_usuarios set cpf_usu = ?, login_usu = ?, nome_usu = ?, datanasc_usu = ?, tipo_usu = ? " +
		              "where id_usu = ?";	

		open();	
		
		stmt = con.prepareStatement(smtp);
		
		stmt.setString(1, usu.getCpf_usu());
		stmt.setString(2, usu.getLogin_usu());
		stmt.setString(3, usu.getNome_usu());
		stmt.setDate(4, data);
		stmt.setString(5, usu.getTipo_usu());
		stmt.setInt(6, usu.getId_usu());
		
		linhasafetadas = stmt.executeUpdate();
		
		close();
		
		return linhasafetadas;
	
	}
	
	public Integer excluirUsuario(Ctrl_usuarios usu) throws Exception {
		
		final Integer linhasafetadas; 
		
		String smtp = "DELETE FROM ctrl_usuarios WHERE id_usu = ?";	
		
		open();	
		
		stmt = con.prepareStatement(smtp); 
		
		stmt.setInt(1, usu.getId_usu());
		
		linhasafetadas = stmt.executeUpdate();
		
		close();
		
		return linhasafetadas;
	
	}
}
