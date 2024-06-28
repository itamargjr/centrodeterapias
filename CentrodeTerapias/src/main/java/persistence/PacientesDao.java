package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Pacientes;

public class PacientesDao extends Dao {
	public List<Pacientes> findAll(Pacientes pac)throws Exception{
		List<Pacientes> lista = new ArrayList<Pacientes>();
		
		open();
		
		String sql = "select " +
				     "  id_ctepac, nome_ctepac, datanasc_ctepac, identidade_ctepac, cpf_ctepac, " + 
				     "  cartsus_ctepac, pai_ctepac, escolpai_ctepac, telpai_ctepac, mae_ctepac, " + 
				     "  escolmae_ctepac, telmae_ctepac, responsavel_ctepac, emailresp_ctepac, " + 
				     "  telresp_ctepac, primfilho_ctepac, irmaos_ctepac, pessoasconv_ctepac, " + 
				     "  endereco_ctepac, bairro_ctepac, cep_ctepac, escola_ctepac, turno_ctepac,  " +
				     "  turma_ctepac, dataencam_ctepac, respencam_ctepac " +
				     "FROM cte_pacientes " +
				     "WHERE 1=1 ";
		
		if ((pac.getNome_ctepac()!=null)&&(!pac.getNome_ctepac().equalsIgnoreCase(""))) {
			sql = sql + " and nome_ctepac like '%" + pac.getNome_ctepac() + "%'";
		}
		
		if ((pac.getDatanasc_ctepac()!=null)&&(!pac.getDatanasc_ctepac().equalsIgnoreCase(""))) {
			sql = sql + " and datanasc_ctepac = '" + pac.getDatanasc_ctepac() + "'";
		}
		
		if ((pac.getPai_ctepac()!=null)&&(!pac.getPai_ctepac().equalsIgnoreCase(""))) {
			sql = sql + " and pai_ctepac like '%" + pac.getPai_ctepac() + "%'";
		}
		
		if ((pac.getMae_ctepac()!=null)&&(!pac.getMae_ctepac().equalsIgnoreCase(""))) {
			sql = sql + " and mae_ctepac like '%" + pac.getMae_ctepac() + "%'";
		}
		
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Pacientes p = new Pacientes(rs.getInt("id_ctepac"), rs.getString("nome_ctepac"), rs.getString("datanasc_ctepac"), 
										rs.getString("identidade_ctepac"), rs.getString("cpf_ctepac"), rs.getString("cartsus_ctepac"), 
										rs.getString("pai_ctepac"), rs.getString("escolpai_ctepac"), rs.getString("telpai_ctepac"), 
										rs.getString("mae_ctepac"), rs.getString("escolmae_ctepac"), rs.getString("telmae_ctepac"), 
										rs.getString("responsavel_ctepac"), rs.getString("emailresp_ctepac"), 
										rs.getString("telresp_ctepac"), rs.getString("primfilho_ctepac"), rs.getString("irmaos_ctepac"), 
										rs.getString("pessoasconv_ctepac"), rs.getString("endereco_ctepac"), 
										rs.getString("bairro_ctepac"), rs.getString("cep_ctepac"), rs.getString("escola_ctepac"), 
										rs.getString("turno_ctepac"), rs.getString("turma_ctepac"), rs.getString("dataencam_ctepac"), 
										rs.getString("respencam_ctepac"));
		
			lista.add(p);
		}
		
		close();
		
		return lista;
	}
}
