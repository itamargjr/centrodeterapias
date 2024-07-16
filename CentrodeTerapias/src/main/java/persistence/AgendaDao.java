package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.Agenda;

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
	
	public List<Agenda> buscagendadomes(String datainicial)throws Exception{
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

}
