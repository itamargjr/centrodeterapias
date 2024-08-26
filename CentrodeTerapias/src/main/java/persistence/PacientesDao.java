package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
				     "  turma_ctepac, dataencam_ctepac, respencam_ctepac, status_ctepac " +
				     "FROM cte_pacientes " +
				     "WHERE 1=1 ";
		
		if (pac !=null) {
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
			
			if ((pac.getStatus_ctepac()!=null)&&(!pac.getStatus_ctepac().equalsIgnoreCase(""))) {
				sql = sql + " and status_ctepac = '" + pac.getStatus_ctepac() + "'";
			}
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
										rs.getString("respencam_ctepac"), rs.getString("status_ctepac"));
		
			lista.add(p);
		}
		
		close();
		
		return lista;
	}
	
	public void gravar(Pacientes pac)throws Exception{
		open();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		String usuario = "NAO IDENTIFICADO";
		
		if (session.getAttribute("loginusulogado")!=null) {
			usuario = session.getAttribute("loginusulogado").toString();
		}
		
		String sql = "insert into cte_pacientes  " +
				     "  (id_ctepac, nome_ctepac, datanasc_ctepac, identidade_ctepac, cpf_ctepac, " + 
				     "  cartsus_ctepac, pai_ctepac, escolpai_ctepac, telpai_ctepac, mae_ctepac, " + 
				     "  escolmae_ctepac, telmae_ctepac, responsavel_ctepac, emailresp_ctepac, " + 
				     "  telresp_ctepac, primfilho_ctepac, irmaos_ctepac, pessoasconv_ctepac, " + 
				     "  endereco_ctepac, bairro_ctepac, cep_ctepac, escola_ctepac, turno_ctepac,  " +
				     "  turma_ctepac, dataencam_ctepac, respencam_ctepac, status_ctepac) " +
				     "values " +
				     "  (0, ?, ?, ?, ?, " + 
				     "  ?, ?, ?, ?, ?, " + 
				     "  ?, ?, ?, ?, " + 
				     "  ?, ?, ?, ?, " + 
				     "  ?, ?, ?, ?, ?,  " +
				     "  ?, ?, ?, ?) ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, pac.getNome_ctepac());
		stmt.setString(2, pac.getDatanasc_ctepac());
		stmt.setString(3, pac.getIdentidade_ctepac());
		stmt.setString(4, pac.getCpf_ctepac());
		stmt.setString(5, pac.getCartsus_ctepac());
		stmt.setString(6, pac.getPai_ctepac());
		stmt.setString(7, pac.getEscolpai_ctepac());
		stmt.setString(8, pac.getTelpai_ctepac());
		stmt.setString(9, pac.getMae_ctepac());
		stmt.setString(10, pac.getEscolmae_ctepac());
		stmt.setString(11, pac.getTelmae_ctepac());
		stmt.setString(12, pac.getResponsavel_ctepac());
		stmt.setString(13, pac.getEmailresp_ctepac());
		stmt.setString(14, pac.getTelresp_ctepac());
		stmt.setString(15, pac.getPrimfilho_ctepac());
		stmt.setString(16, pac.getIrmaos_ctepac());
		stmt.setString(17, pac.getPessoasconv_ctepac());
		stmt.setString(18, pac.getEndereco_ctepac());
		stmt.setString(19, pac.getBairro_ctepac());
		stmt.setString(20, pac.getCep_ctepac());
		stmt.setString(21, pac.getEscola_ctepac());
		stmt.setString(22, pac.getTurno_ctepac());
		stmt.setString(23, pac.getTurma_ctepac());
		stmt.setString(24, pac.getDataencam_ctepac());
		stmt.setString(25, usuario);
		stmt.setString(25, pac.getStatus_ctepac());

		stmt.executeUpdate();
		
		close();
	}
}
