package report;

import java.util.Iterator;
import java.util.List;

import entity.Profissionais;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DSReportProfissionais implements JRDataSource {
	
	private Iterator<Profissionais> dados;
	
	private Profissionais registro;
	
	public DSReportProfissionais(List<Profissionais> lista) {
		dados = lista.iterator();
	}
	
	/*Função de adicionar um valor para os Fields (campos) do relatorio*/
	public Object getFieldValue(JRField field) throws JRException {
		
		if(field.getName().equalsIgnoreCase("email_cteprof")){
			return registro.getEmail_cteprof();				
		}
		if(field.getName().equalsIgnoreCase("cpf_cteprof")){
			return registro.getCpf_cteprof();
		}
		if(field.getName().equalsIgnoreCase("endereco_cteprof")){
			return registro.getEndereco_cteprof();
		}
		if(field.getName().equalsIgnoreCase("id_cteprof")){
			return registro.getId_cteprof();
		}
		if(field.getName().equalsIgnoreCase("nome_cteprof")){
			return registro.getNome_cteprof();
		}
		if(field.getName().equalsIgnoreCase("registro_cteprof")){
			return registro.getRegistro_cteprof();
		}
		if(field.getName().equalsIgnoreCase("telefone_cteprof")){
			return registro.getTelefone_cteprof();
		}
		if(field.getName().equalsIgnoreCase("tipo_cteprof")){
			
			if (registro.getTipo_cteprof().equalsIgnoreCase("1")) {
				return "Assistente Social";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("2")) {
				return "Fisioterapeuta";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("3")) {
				return "Psicóloga";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("4")) {
				return "Psicopedagoga";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("5")) {
				return "Fonoaudióloga";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("6")) {
				return "Técnica de Enfermagem";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("7")) {
				return "Psicomotricista";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("8")) {
				return "Músico";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("9")) {
				return "Terapeuta Ocupacional";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("10")) {
				return "Nutricionista";
			} else if (registro.getTipo_cteprof().equalsIgnoreCase("11")) {
				return "Dentista";
			}						  									

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
