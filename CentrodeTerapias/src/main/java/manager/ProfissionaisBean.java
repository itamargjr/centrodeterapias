package manager;

import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;

import entity.Profissionais;
import net.sf.jasperreports.engine.JasperRunManager;
import persistence.ProfissionaisDao;
import report.DSReportProfissionais;

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
			
			profissionaislista.add(profissionais);
			
			profissionais = new Profissionais();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void excluir() {
		try {
			
			ProfissionaisDao pd = new ProfissionaisDao(); 
			
			pd.excluir(profissionais);
			
			profissionaislista.remove(profissionais);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public String imprimir() {
		
		if (profissionaislista.size()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sem dados para imprimir", ""));
		} else {
			try{
				
				DSReportProfissionais ds = new DSReportProfissionais(profissionaislista);
				
				Map<String, Object> param = new HashMap<>();
				
				String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/imagens/logonovo.png");
				
				param.put("pLogo", logo);
				
				InputStream	arquivo = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reports/profissionais.jasper");

				byte[] pdf = JasperRunManager.runReportToPdf(arquivo, param, ds);
				HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				
				res.setContentType("application/pdf");
				res.setContentLength(pdf.length);
				OutputStream out = res.getOutputStream();
				out.write(pdf, 0, pdf.length);
				out.flush();
				out.close();
				
				FacesContext.getCurrentInstance().responseComplete();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
