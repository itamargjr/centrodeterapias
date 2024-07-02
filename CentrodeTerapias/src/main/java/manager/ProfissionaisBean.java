package manager;

import java.util.List;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Profissionais;
import persistence.ProfissionaisDao;

@ManagedBean
@ViewScoped
public class ProfissionaisBean {
	
	Profissionais profissionais;
	
	List<Profissionais> profissionaislista;
	
	public ProfissionaisBean() {
		profissionais = new Profissionais();
		
		profissionaislista = new ArrayList<Profissionais>();
	}

	public Profissionais getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(Profissionais profissionais) {
		this.profissionais = profissionais;
	}

	public List<Profissionais> getProfissionaislista() {
		return profissionaislista;
	}

	public void setProfissionaislista(List<Profissionais> profissionaislista) {
		this.profissionaislista = profissionaislista;
	}
	
	public void Buscar() {
		try {
			
			profissionaislista = new ProfissionaisDao().findAll(profissionais);
			
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
		try {
			
			ProfissionaisDao pd = new ProfissionaisDao(); 
			
			pd.gravar(profissionais);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
}
