package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Profissionais;

public class ProfissionaisDao extends Dao {
	
	public List<Profissionais> findAll(Profissionais prof)throws Exception{
		List<Profissionais> lista = new ArrayList<Profissionais>();
		
		open();
		
		String sql = "select " +
				     "  id_cteprof, tipo_cteprof, nome_cteprof, registro_cteprof,  " + 
				     "  endereco_cteprof, telefone_cteprof, cpf_cteprof, email_cteprof " +
				     "FROM cte_profissionais " +
				     "WHERE 1=1 ";
		
		if ((prof.getNome_cteprof()!=null)&&(!prof.getNome_cteprof().equalsIgnoreCase(""))) {
			sql = sql + " and nome_cteprof like '%" + prof.getNome_cteprof() + "%'";
		}
		
		if ((prof.getTipo_cteprof()!=null)&&(!prof.getTipo_cteprof().equalsIgnoreCase(""))) {
			sql = sql + " and tipo_cteprof = '" + prof.getTipo_cteprof() + "'";
		}
		
		if ((prof.getEmail_cteprof()!=null)&&(!prof.getEmail_cteprof().equalsIgnoreCase(""))) {
			sql = sql + " and email_cteprof like '%" + prof.getEmail_cteprof() + "%'";
		}
		
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Profissionais p = new Profissionais(rs.getInt("id_cteprof"), rs.getString("tipo_cteprof"), 
												rs.getString("nome_cteprof"), rs.getString("registro_cteprof"), 
												rs.getString("endereco_cteprof"), rs.getString("telefone_cteprof"), 
												rs.getString("cpf_cteprof"), rs.getString("email_cteprof"));
		
			lista.add(p);
		}
		
		close();
		
		return lista;
	}
	
	public void gravar(Profissionais prof)throws Exception{
		open();
		
		String sql = "insert into cte_profissionais  " +
				     "  (id_cteprof, tipo_cteprof, nome_cteprof, registro_cteprof, endereco_cteprof, " + 
				     "  telefone_cteprof, cpf_cteprof, email_cteprof) " +
				     "values " +
				     "  (0, ?, ?, ?, ?, ?, ?, ?) ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, prof.getTipo_cteprof());
		stmt.setString(2, prof.getNome_cteprof());
		stmt.setString(3, prof.getRegistro_cteprof());
		stmt.setString(4, prof.getEndereco_cteprof());
		stmt.setString(5, prof.getTelefone_cteprof());
		stmt.setString(6, prof.getCpf_cteprof());
		stmt.setString(7, prof.getEmail_cteprof());

		stmt.executeUpdate();
		
		close();
	}

}
