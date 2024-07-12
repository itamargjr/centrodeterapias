package entity;

public class Agenda {
	private Integer id_agenda;	
	private String data_agenda;	
	private Integer id_horario;	
	private Integer id_cteprof;	
	private Integer id_ctepac;	
	private String status_agenda;
	
	private String descricao_horario;
	private String tipo_cteprof;	
	private String nome_cteprof;
	private String nome_ctepac;
	
	public Agenda() {
		super();
	}

	public Agenda(Integer id_agenda, String data_agenda, Integer id_horario, Integer id_cteprof, Integer id_ctepac,
			String status_agenda, String descricao_horario, String tipo_cteprof, String nome_cteprof,
			String nome_ctepac) {
		super();
		this.id_agenda = id_agenda;
		this.data_agenda = data_agenda;
		this.id_horario = id_horario;
		this.id_cteprof = id_cteprof;
		this.id_ctepac = id_ctepac;
		this.status_agenda = status_agenda;
		this.descricao_horario = descricao_horario;
		this.tipo_cteprof = tipo_cteprof;
		this.nome_cteprof = nome_cteprof;
		this.nome_ctepac = nome_ctepac;
	}

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}

	public String getData_agenda() {
		return data_agenda;
	}

	public void setData_agenda(String data_agenda) {
		this.data_agenda = data_agenda;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public Integer getId_cteprof() {
		return id_cteprof;
	}

	public void setId_cteprof(Integer id_cteprof) {
		this.id_cteprof = id_cteprof;
	}

	public Integer getId_ctepac() {
		return id_ctepac;
	}

	public void setId_ctepac(Integer id_ctepac) {
		this.id_ctepac = id_ctepac;
	}

	public String getStatus_agenda() {
		return status_agenda;
	}

	public void setStatus_agenda(String status_agenda) {
		this.status_agenda = status_agenda;
	}

	public String getDescricao_horario() {
		return descricao_horario;
	}

	public void setDescricao_horario(String descricao_horario) {
		this.descricao_horario = descricao_horario;
	}

	public String getTipo_cteprof() {
		return tipo_cteprof;
	}

	public void setTipo_cteprof(String tipo_cteprof) {
		this.tipo_cteprof = tipo_cteprof;
	}

	public String getNome_cteprof() {
		return nome_cteprof;
	}

	public void setNome_cteprof(String nome_cteprof) {
		this.nome_cteprof = nome_cteprof;
	}

	public String getNome_ctepac() {
		return nome_ctepac;
	}

	public void setNome_ctepac(String nome_ctepac) {
		this.nome_ctepac = nome_ctepac;
	}

	@Override
	public String toString() {
		return "Agenda [id_agenda=" + id_agenda + ", data_agenda=" + data_agenda + ", id_horario=" + id_horario
				+ ", id_cteprof=" + id_cteprof + ", id_ctepac=" + id_ctepac + ", status_agenda=" + status_agenda
				+ ", descricao_horario=" + descricao_horario + ", tipo_cteprof=" + tipo_cteprof + ", nome_cteprof="
				+ nome_cteprof + ", nome_ctepac=" + nome_ctepac + "]";
	}
}
