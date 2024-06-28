package manager;

import java.util.List;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Pacientes;
import persistence.PacientesDao;

@ManagedBean
@ViewScoped
public class PacientesBean {
	
	Pacientes pacientes;
	
	List<Pacientes> pacienteslista;
	
	public PacientesBean() {
		pacientes = new Pacientes();
		
		pacienteslista = new ArrayList<Pacientes>();
	}

	public Pacientes getPacientes() {
		return pacientes;
	}

	public void setPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}

	public List<Pacientes> getPacienteslista() {
		return pacienteslista;
	}

	public void setPacienteslista(List<Pacientes> pacienteslista) {
		this.pacienteslista = pacienteslista;
	}
	
	public void Buscar() {
		try {
			
			pacienteslista = new PacientesDao().findAll(pacientes);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void AbrirDialogoCadastro() {
		PrimeFaces.current().executeScript("PF('dialogocadastro').show();");
	}
	
	public void abrirdialogodetalhe() {
		PrimeFaces.current().executeScript("PF('dialogodetalhe').show();");
	}
	
	public void gravar() {
		
	}
}
