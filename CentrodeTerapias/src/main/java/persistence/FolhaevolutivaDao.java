package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Folhaevolutiva;

public class FolhaevolutivaDao extends Dao {
	
	public List<Folhaevolutiva> buscar(Folhaevolutiva folha)throws Exception{               
		List<Folhaevolutiva> lista = new ArrayList<Folhaevolutiva>();                
		open();
		
		String sql = "select "+                                                     
					 "	a.id_folhaevol, a.id_cteprof, a.id_ctepac, " +
					 "  a.data_folhaevol, a.descricao_folhaevol,  " +
					 "  a.status_folhaevol, b.tipo_cteprof, " +
					 "  b.nome_cteprof, c.nome_ctepac "+ 
					 "from "+
					 "	cte_folhaevolutiva a, cte_profissionais b, "+
					 "	cte_pacientes c "+
					 "where "+
					 "	a.id_cteprof = b.id_cteprof and "+
					 "  a.id_ctepac = c.id_ctepac ";
			
		if ((folha.getId_cteprof()!= null)&&(folha.getId_cteprof()>0)) {		
			sql = sql + " and a.id_cteprof = " + folha.getId_cteprof();		
		}
		
		if ((folha.getId_ctepac()!= null)&&(folha.getId_ctepac()>0)) {		
			sql = sql + " and a.id_ctepac = " + folha.getId_ctepac();		
		}
		
		if ((folha.getNome_cteprof()!= null) &&(!folha.getNome_cteprof().equalsIgnoreCase(""))){
			sql = sql + " and b.nome_cteprof like '%" + folha.getNome_cteprof() + "%'";				
		}
		
		if ((folha.getNome_ctepac()!= null) &&(!folha.getNome_ctepac().equalsIgnoreCase(""))){
			sql = sql + " and c.nome_ctepac like '%" + folha.getNome_ctepac() + "%'";				
		}
		
		if ((folha.getTipo_cteprof()!= null) &&(!folha.getTipo_cteprof().equalsIgnoreCase(""))){
			sql = sql + " and b.tipo_cteprof = '" + folha.getTipo_cteprof() + "'";				
		}
		
		if ((!folha.getDataini().equalsIgnoreCase("")) && (!folha.getDatafim().equalsIgnoreCase(""))) {
			sql = sql + " and a.data_folhaevol between to_date('" + folha.getDataini() + "') and to_date('" + folha.getDatafim() + "')";						
		} else if (!folha.getDataini().equalsIgnoreCase("")) {
			sql = sql + " and trunc(a.data_folhaevol) = to_Date('" + folha.getDataini() + "')";
		}
		
		sql = sql + " order by b.tipo_cteprof, b.nome_cteprof, c.nome_ctepac, a.data_folhaevol";
		
		//System.out.println(sql);
		
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {   
			
			Folhaevolutiva f = new Folhaevolutiva(rs.getInt("id_folhaevol"), rs.getInt("id_cteprof"), rs.getInt("id_ctepac"), 
												  rs.getString("data_folhaevol"), rs.getString("descricao_folhaevol"),
												  rs.getString("status_folhaevol"), rs.getString("nome_ctepac"),
												  rs.getString("nome_cteprof"), rs.getString("tipo_cteprof"));
		
			lista.add(f);
		}	

		close();
		return lista;		
	}
	
	public void gravar(Folhaevolutiva folha)throws Exception{  
		open();
		
		String sql = "insert into cte_folhaevolutiva " +
				     "  (id_folhaevol, id_cteprof, id_ctepac, data_folhaevol, descricao_folhaevol, status_folhaevol) " +
			         "values  " +
			         "  (?, ?, ?, ?, ?, ?) ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, 0);
		stmt.setInt(2,folha.getId_cteprof());
		stmt.setInt(3,folha.getId_ctepac());
		stmt.setString(4,folha.getData_folhaevol()); 
		stmt.setString(5,folha.getDescricao_folhaevol());
		stmt.setString(6,folha.getStatus_folhaevol());
		
		stmt.executeUpdate();
		
		close();
	}
	
	public void alterar(Folhaevolutiva folha)throws Exception{  
		open();
		
		String sql = "update cte_folhaevolutiva " +
				     "set " +
					 "  id_cteprof = ?, " +
				     "  id_ctepac = ?, " +
					 "  data_folhaevol = ?, " +
				     "  descricao_folhaevol = ?, " + 
					 "  status_folhaevol = ? " +
			         "where  " +
			         "  id_folhaevol = ? ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setInt(1,folha.getId_cteprof());
		stmt.setInt(2,folha.getId_ctepac());
		stmt.setString(3,folha.getData_folhaevol()); 
		stmt.setString(4,folha.getDescricao_folhaevol());
		stmt.setString(5,folha.getStatus_folhaevol());
		stmt.setInt(6, folha.getId_folhaevol());
		
		stmt.executeUpdate();
		
		close();
	}
	
	public void excluir(Integer id)throws Exception{  
		open();
		
		String sql = "delete from cte_folhaevolutiva " +
				     "where  " +
			         "  id_folhaevol = ? ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setInt(1,id);
		
		stmt.executeUpdate();
		
		close();
	}
}
