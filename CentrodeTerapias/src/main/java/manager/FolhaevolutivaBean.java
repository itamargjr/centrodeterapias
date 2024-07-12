package manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Folhaevolutiva;
import entity.Pacientes;
import entity.Profissionais;
import persistence.FolhaevolutivaDao;
import persistence.PacientesDao;
import persistence.ProfissionaisDao;

@ManagedBean
@ViewScoped
public class FolhaevolutivaBean {
	
	public Folhaevolutiva folhaevolutiva;
	public Folhaevolutiva folhaevolutivaalteracao;
	
	public List<Folhaevolutiva> folhaevolutivalista;
	public List<Profissionais> profissionaislista;
	public List<Pacientes> pacienteslista;
	
	public FolhaevolutivaBean() {
		
		folhaevolutiva = new Folhaevolutiva();
		folhaevolutivaalteracao = new Folhaevolutiva();
		
		folhaevolutivalista = new ArrayList<Folhaevolutiva>();
		
		try {
			pacienteslista = new PacientesDao().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public List<Pacientes> getPacienteslista() {
		return pacienteslista;
	}

	public void setPacienteslista(List<Pacientes> pacienteslista) {
		this.pacienteslista = pacienteslista;
	}

	public Folhaevolutiva getFolhaevolutivaalteracao() {
		return folhaevolutivaalteracao;
	}

	public void setFolhaevolutivaalteracao(Folhaevolutiva folhaevolutivaalteracao) {
		this.folhaevolutivaalteracao = folhaevolutivaalteracao;
	}

	public List<Profissionais> getProfissionaislista() {
		return profissionaislista;
	}

	public void setProfissionaislista(List<Profissionais> profissionaislista) {
		this.profissionaislista = profissionaislista;
	}

	public Folhaevolutiva getFolhaevolutiva() {
		return folhaevolutiva;
	}

	public void setFolhaevolutiva(Folhaevolutiva folhaevolutiva) {
		this.folhaevolutiva = folhaevolutiva;
	}

	public List<Folhaevolutiva> getFolhaevolutivalista() {
		return folhaevolutivalista;
	}

	public void setFolhaevolutivalista(List<Folhaevolutiva> folhaevolutivalista) {
		this.folhaevolutivalista = folhaevolutivalista;
	}
	
	public void atualizalistaprofissionais() {
		try {
			ProfissionaisDao pd = new ProfissionaisDao();
			
			profissionaislista =  pd.buscaportipo(folhaevolutiva.getTipo_cteprof());
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void buscar() {
		
		FolhaevolutivaDao fd = new FolhaevolutivaDao();
		
		try {
			
			folhaevolutivalista = fd.buscar(folhaevolutiva);
			
		} catch (Exception e) {
			e.printStackTrace();  
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void abredialogoalterar() {
		
		folhaevolutivalista.remove(folhaevolutivaalteracao);
		
		PrimeFaces.current().executeScript("PF('dialogoalterar').show();");
	}
	
	public void abredialogoexclusao() {
		PrimeFaces.current().executeScript("PF('dialogoexcluir').show();");
	}
	
	public void abrirdialogocadastrar() {
		
		if (folhaevolutiva.getId_cteprof()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o profissional corretamente", ""));
		} else {
			try {
				
				java.util.Date data = new java.util.Date();
				
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				
				folhaevolutiva.setData_folhaevol(formatador.format(data));
				
				ProfissionaisDao pd = new ProfissionaisDao();
				
				folhaevolutiva.setNome_cteprof(pd.buscanomepeloid(folhaevolutiva.getId_cteprof()));
				
				PrimeFaces.current().executeScript("PF('dialogocadastrar').show();");
				
			} catch (Exception e) {
				e.printStackTrace();  
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
		}
	}
	
	public void gravar() {
		
		try {
			
			FolhaevolutivaDao fd = new FolhaevolutivaDao();
			
			fd.gravar(folhaevolutiva);
			
			folhaevolutivalista.add(folhaevolutiva);
			
			folhaevolutiva = new Folhaevolutiva();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravacao efetuada", ""));

			
		} catch (Exception e) {
			e.printStackTrace();  
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void alterar() {
		
		try {
			
			FolhaevolutivaDao fd = new FolhaevolutivaDao();
			
			fd.alterar(folhaevolutivaalteracao);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," Alteracao efetuada!", ""));
			
			PrimeFaces.current().executeScript("PF('dialogoalterar').hide();");
			
			folhaevolutivalista.add(folhaevolutivaalteracao);
			
		} catch (Exception e) {
			e.printStackTrace();  
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
			
	}
	
	public void excluir() {

		try {
			
			FolhaevolutivaDao fd = new FolhaevolutivaDao();
			
			fd.excluir(folhaevolutivaalteracao.getId_folhaevol());
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," Exclusao efetuada!", ""));
			
			PrimeFaces.current().executeScript("PF('dialogoexcluir').hide();");
			
			folhaevolutivalista.remove(folhaevolutivaalteracao);
			
		} catch (Exception e) {
			e.printStackTrace();  
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
}
