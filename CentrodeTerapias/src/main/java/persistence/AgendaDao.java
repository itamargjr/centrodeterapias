package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.Agenda;
import entity.Horarios;

public class AgendaDao extends Dao {
	
	public Agenda buscagendaporid(String id) throws Exception {
		Agenda agenda = new Agenda();
		
		open();
		
		String sql = "select "+                                                     
					 "	a.id_agenda, a.data_agenda, a.id_horario, " +
					 "  a.id_cteprof, a.id_ctepac, a.status_agenda, " +
					 "  a.obs_agenda, b.descricao_horario, " +
					 "  c.tipo_cteprof, c.nome_cteprof, d.nome_ctepac "+ 
					 "from "+
					 "	cte_agenda a, cte_agendahorarios b, "+
					 "	cte_profissionais c, cte_pacientes d "+
					 "where "+
					 "  a.id_horario  = b.id_horario and " +
					 "	a.id_cteprof = c.id_cteprof and "+
					 "  a.id_ctepac = d.id_ctepac and a.id_agenda = " + id;
	
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			agenda = new Agenda(rs.getInt("id_agenda"), rs.getString("data_agenda"), rs.getInt("id_horario"), 
								  rs.getInt("id_cteprof"), rs.getInt("id_ctepac"), rs.getString("status_agenda"), 
								  rs.getString("obs_agenda"), rs.getString("descricao_horario"),  
								  rs.getString("tipo_cteprof"),rs.getString("nome_cteprof"), rs.getString("nome_ctepac"));
		}
		
		close();
		
		return agenda;
	}
	
	public List<Agenda> buscagendadomes(String datainicial, Integer idProf)throws Exception{
		List<Agenda> lista = new ArrayList<Agenda>();
		
		open();
		
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		
		java.util.Date datafim = formato.parse(datainicial);	
		
		cal.setTime(datafim);
		
		Integer ultimoDia = cal.getActualMaximum (Calendar.DAY_OF_MONTH);
		
		String datafimS = ultimoDia.toString() + "/" + mes.format(datafim) + "/" + ano.format(datafim);
		
		
		String sql = "select "+                                                     
					 "	a.id_agenda, a.data_agenda, a.id_horario, " +
					 "  a.id_cteprof, a.id_ctepac, a.status_agenda, " +
					 "  a.obs_agenda, b.descricao_horario, " +
					 "  c.tipo_cteprof, c.nome_cteprof, d.nome_ctepac "+ 
					 "from "+
					 "	cte_agenda a, cte_agendahorarios b, "+
					 "	cte_profissionais c, cte_pacientes d "+
					 "where "+
					 "  a.id_horario  = b.id_horario and " +
					 "	a.id_cteprof = c.id_cteprof and "+
					 "  a.id_ctepac = d.id_ctepac and " + 
					 "  STR_TO_DATE(a.data_agenda, '%d/%m/%Y') between " +
					 "  STR_TO_DATE('" + datainicial + "', '%d/%m/%Y') and  " +
					 "  STR_TO_DATE('" + datafimS + "', '%d/%m/%Y') " +
					 "order by a.data_agenda, b.descricao_horario";
		
		stmt = con.prepareStatement(sql);
		
		//System.out.println(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Agenda a = new Agenda(rs.getInt("id_agenda"), rs.getString("data_agenda"), rs.getInt("id_horario"), 
								  rs.getInt("id_cteprof"), rs.getInt("id_ctepac"), rs.getString("status_agenda"), 
								  rs.getString("obs_agenda"), rs.getString("descricao_horario"),  
								  rs.getString("tipo_cteprof"),rs.getString("nome_cteprof"), rs.getString("nome_ctepac"));
			lista.add(a);
		}
		
		close();
		
		return lista;
	}
	
	public List<Horarios> horariosdisponiveis(Agenda agenda)throws Exception{
		List<Horarios> lista = new ArrayList<Horarios>();
		
		open();
		
		String sql = "SELECT id_horario, descricao_horario from cte_agendahorarios " +
					 "where id_horario not in " +
					 "  ( select id_horario " +
					 "    from cte_agenda " +
					 "    where data_agenda = '" + agenda.getData_agenda() + "' and " +
					 "           id_cteprof = " + agenda.getId_cteprof() + ")";
		
		stmt = con.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Horarios h = new Horarios(rs.getInt("id_horario"), rs.getString("descricao_horario"));
			
			lista.add(h);
		}
		
		close();
		
		return lista;
	}
	
	public Integer gravar(Agenda agenda)throws Exception{
		
		Integer id = 0;
		
		open();
		
		String sql = "insert into cte_agenda " +
		             "  (id_agenda, data_agenda, id_horario, id_cteprof, id_ctepac, status_agenda, obs_agenda) " +				
					 "values " +
					 "  (0, ?, ?, ?, ?, ?, ?)";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, agenda.getData_agenda());
		stmt.setInt(2,agenda.getId_horario());
		stmt.setInt(3,agenda.getId_cteprof());
		stmt.setInt(4,agenda.getId_ctepac()); 
		stmt.setString(5,agenda.getStatus_agenda());
		stmt.setString(6,agenda.getObs_agenda());
		
		stmt.executeUpdate();
		
		sql = "select id_agenda from cte_agenda " +
		      "where " +
			  "  data_agenda = ? and " +
		      "  id_horario = ? and " +
			  "  id_cteprof = ? and " +
		      "  id_ctepac = ? and " +
			  "  status_agenda = ? and " +
		      "  obs_agenda = ? ";
		
		stmt = con.prepareStatement(sql);
		
		stmt.setString(1, agenda.getData_agenda());
		stmt.setInt(2,agenda.getId_horario());
		stmt.setInt(3,agenda.getId_cteprof());
		stmt.setInt(4,agenda.getId_ctepac()); 
		stmt.setString(5,agenda.getStatus_agenda());
		stmt.setString(6,agenda.getObs_agenda());
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			id = rs.getInt("id_agenda");
		}
		
		close();
		
		return id;
	}

}
