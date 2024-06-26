package manager;

import java.util.List;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Pacientes;

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
	
	public void gravar() {
		
	}
}
