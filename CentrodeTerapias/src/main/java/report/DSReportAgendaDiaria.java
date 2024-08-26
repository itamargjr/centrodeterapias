package report;

import java.util.Iterator;
import java.util.List;

import entity.Agenda;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DSReportAgendaDiaria implements JRDataSource {
	
	private Iterator<Agenda> dados;
	
	private Agenda registro;
	
	public DSReportAgendaDiaria(List<Agenda> lista) {
		dados = lista.iterator();
	}
	
	/*Função de adicionar um valor para os Fields (campos) do relatorio*/
	public Object getFieldValue(JRField field) throws JRException {
		
		if(field.getName().equalsIgnoreCase("data_agenda")){
			return registro.getData_agenda();				
		}
		if(field.getName().equalsIgnoreCase("descricao_horario")){
			return registro.getDescricao_horario();
		}
		if(field.getName().equalsIgnoreCase("id_agenda")){
			return registro.getId_agenda();
		}
		if(field.getName().equalsIgnoreCase("id_ctepac")){
			return registro.getId_ctepac();
		}
		if(field.getName().equalsIgnoreCase("id_cteprof")){
			return registro.getId_cteprof();
		}
		if(field.getName().equalsIgnoreCase("id_horario")){
			return registro.getId_horario();
		}
		if(field.getName().equalsIgnoreCase("nome_ctepac")){
			return registro.getNome_ctepac();
		}
		if(field.getName().equalsIgnoreCase("nome_cteprof")){
			return registro.getNome_cteprof();
		}
		if(field.getName().equalsIgnoreCase("obs_agenda")){
			return registro.getObs_agenda();
		}
		if(field.getName().equalsIgnoreCase("status_agenda")){
			return registro.getStatus_agenda();
		}
		if(field.getName().equalsIgnoreCase("tipo_cteprof")){
			return registro.getTipo_cteprof();
		}

		return null;
	}

	/*Ir para o proximo registro de uma iteração*/
	public boolean next() throws JRException {
		if(dados.hasNext()){
			registro = dados.next();
			return true;
		}
		return false;
	}

}
