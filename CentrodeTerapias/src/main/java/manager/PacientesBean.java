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

import entity.Pacientes;
import net.sf.jasperreports.engine.JasperRunManager;
import persistence.FolhaevolutivaDao;
import persistence.PacientesDao;
import report.DSReportPacientes;

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
	
	public void BuscarAlunosEncaminhados() {
		try {
			
			pacientes.setStatus_ctepacString("E");
			
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
		try {
			
			PacientesDao pd = new PacientesDao(); 
			
			pacientes.setStatus_ctepacString("C");
			
			pd.gravar(pacientes);
			
			pacienteslista.add(pacientes);
			
			pacientes = new Pacientes();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void excluir() {
		try {
			
			if (pacientes.getId_ctepac()!=null) {
				FolhaevolutivaDao fd = new FolhaevolutivaDao();
				
				if (fd.temfolha(pacientes.getId_ctepac())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existe folha evolutiva para este paciente, não pode excluir", ""));
				} else {
					PacientesDao pd = new PacientesDao();
					
					pd.excluir(pacientes);
					
					pacienteslista.remove(pacientes);
					
					pacientes = new Pacientes();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void gravarEncaminhado() {
		try {
			
			PacientesDao pd = new PacientesDao(); 
			
			pacientes.setStatus_ctepacString("E");
			
			pd.gravar(pacientes);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public String imprimir() {
		
		if (pacienteslista.size()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sem dados para imprimir", ""));
		} else {
			try{
				
				DSReportPacientes ds = new DSReportPacientes(pacienteslista);
				
				Map<String, Object> param = new HashMap<>();
				
				String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/imagens/logonovo.png");
				
				param.put("pLogo", logo);
				
				InputStream	arquivo = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reports/pacientes.jasper");

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
