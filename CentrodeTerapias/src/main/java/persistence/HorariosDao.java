package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Horarios;

public class HorariosDao extends Dao {
	public List<Horarios> findAll(Horarios hor)throws Exception{
		List<Horarios> lista = new ArrayList<Horarios>();
		
		open();
		
		String sql = "select " +
				     "  id_horario, descricao_horario " +
				     "FROM cte_agendahorarios " +
				     "WHERE 1=1 ";
		
		if ((hor.getDescricao_horario()!=null)&&(!hor.getDescricao_horario().equalsIgnoreCase(""))) {
			sql = sql + " and descricao_horario like '%" + hor.getDescricao_horario() + "%'";
		}
		
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Horarios h = new Horarios(rs.getInt("id_horario"), rs.getString("descricao_horario"));
		
			lista.add(h);
		}
		
		close();
		
		return lista;
	}
	
	public void gravar(Horarios hor)throws Exception{
		open();
		
		String sql = "insert into cte_agendahorarios  " +
				     "  (id_horario, descricao_horario) " +
				     "values " +
				     "  (0, ?) ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, hor.getDescricao_horario());

		stmt.executeUpdate();
		
		close();
	}
	
	public void excluir(Horarios hor)throws Exception{
		open();
		
		String sql = "delete from cte_agendahorarios where id_horario = ? ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, hor.getId_horario());

		stmt.executeUpdate();
		
		close();
	}
	
	public String retornadescricaohorario(Integer id)throws Exception{
		String horario = "";
		
		open();
		
		String sql = "select descricao_horario " +
				     "FROM cte_agendahorarios " +
				     "WHERE id_horario=? ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			horario = rs.getString("descricao_horario");
		}
		
		close();
		
		return horario;
	}
}
