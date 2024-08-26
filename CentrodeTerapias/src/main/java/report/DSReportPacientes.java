package report;

import java.util.Iterator;
import java.util.List;

import entity.Pacientes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DSReportPacientes implements JRDataSource {
	
	private Iterator<Pacientes> dados;
	
	private Pacientes registro;
	
	public DSReportPacientes(List<Pacientes> lista) {
		dados = lista.iterator();
	}
	
	/*Função de adicionar um valor para os Fields (campos) do relatorio*/
	public Object getFieldValue(JRField field) throws JRException {
		
		if(field.getName().equalsIgnoreCase("bairro_ctepac")){
			return registro.getBairro_ctepac();				
		}
		if(field.getName().equalsIgnoreCase("cartsus_ctepac")){
			return registro.getCartsus_ctepac();
		}
		if(field.getName().equalsIgnoreCase("cep_ctepac")){
			return registro.getCep_ctepac();
		}
		if(field.getName().equalsIgnoreCase("cpf_ctepac")){
			return registro.getCpf_ctepac();
		}
		if(field.getName().equalsIgnoreCase("dataencam_ctepac")){
			return registro.getDataencam_ctepac();
		}
		if(field.getName().equalsIgnoreCase("datanasc_ctepac")){
			return registro.getDatanasc_ctepac();
		}
		if(field.getName().equalsIgnoreCase("emailresp_ctepac")){
			return registro.getEmailresp_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("status_ctepac")){			
			if (registro.getStatus_ctepac().equalsIgnoreCase("E")) {
				return "Encaminhado";
			} else {
				return "Cadastrado";
			}					  									

		}		

		if(field.getName().equalsIgnoreCase("endereco_ctepac")){
			return registro.getEndereco_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("escola_ctepac")){
			return registro.getEscola_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("escolmae_ctepac")){
			return registro.getEscolmae_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("escolpai_ctepac")){
			return registro.getEscolpai_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("id_ctepac")){
			return registro.getId_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("identidade_ctepac")){
			return registro.getIdentidade_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("irmaos_ctepac")){
			return registro.getIrmaos_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("mae_ctepac")){
			return registro.getMae_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("nome_ctepac")){
			return registro.getNome_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("pai_ctepac")){
			return registro.getPai_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("pessoasconv_ctepac")){
			return registro.getPessoasconv_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("primfilho_ctepac")){
			return registro.getPrimfilho_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("respencam_ctepac")){
			return registro.getRespencam_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("responsavel_ctepac")){
			return registro.getResponsavel_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("telmae_ctepac")){
			return registro.getTelmae_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("telpai_ctepac")){
			return registro.getTelpai_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("telresp_ctepac")){
			return registro.getTelresp_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("turma_ctepac")){
			return registro.getTurma_ctepac();
		}
		
		if(field.getName().equalsIgnoreCase("turno_ctepac")){
			return registro.getTurno_ctepac();
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