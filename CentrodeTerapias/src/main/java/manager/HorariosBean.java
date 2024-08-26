package manager;

import java.util.List;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Horarios;
import persistence.HorariosDao;

@ManagedBean
@ViewScoped
public class HorariosBean {
	
	Horarios horarios;
	
	List<Horarios> horarioslista;
	
	public HorariosBean() {
		
		horarios = new Horarios();
		
		horarioslista = new ArrayList<Horarios>();

	}

	public Horarios getHorarios() {
		return horarios;
	}

	public void setHorarios(Horarios horarios) {
		this.horarios = horarios;
	}

	public List<Horarios> getHorarioslista() {
		return horarioslista;
	}

	public void setHorarioslista(List<Horarios> horarioslista) {
		this.horarioslista = horarioslista;
	}
	
	public void Buscar() {
		try {
			
			horarioslista = new HorariosDao().findAll(horarios);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void AbrirDialogoCadastro() {
		PrimeFaces.current().executeScript("PF('dialogocadastro').show();");
	}
	
	public void gravar() {
		try {
			
			HorariosDao pd = new HorariosDao(); 
			
			pd.gravar(horarios);
			
			horarioslista.add(horarios);
			
			horarios = new Horarios();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void excluir() {
		try {
			
			HorariosDao pd = new HorariosDao(); 
			
			pd.excluir(horarios);
			
			horarioslista.remove(horarios);
			
			horarios = new Horarios();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
}
