package entity;

public class Folhaevolutiva {
	private Integer id_folhaevol;	
	private Integer id_cteprof;	
	private Integer id_ctepac;	
	private String data_folhaevol;	
	private String descricao_folhaevol;	
	private String status_folhaevol;
	
	private String nome_ctepac;
	private String nome_cteprof;
	private String tipo_cteprof;
	
	private String dataini;
	private String datafim;
	
	public Folhaevolutiva() {
		super();
	}

	public Folhaevolutiva(Integer id_folhaevol, Integer id_cteprof, Integer id_ctepac, String data_folhaevol,
			String descricao_folhaevol, String status_folhaevol, String nome_ctepac, String nome_cteprof,
			String tipo_cteprof) {
		super();
		this.id_folhaevol = id_folhaevol;
		this.id_cteprof = id_cteprof;
		this.id_ctepac = id_ctepac;
		this.data_folhaevol = data_folhaevol;
		this.descricao_folhaevol = descricao_folhaevol;
		this.status_folhaevol = status_folhaevol;
		this.nome_ctepac = nome_ctepac;
		this.nome_cteprof = nome_cteprof;
		this.tipo_cteprof = tipo_cteprof;
	}

	public String getTipo_cteprof() {
		return tipo_cteprof;
	}

	public void setTipo_cteprof(String tipo_cteprof) {
		this.tipo_cteprof = tipo_cteprof;
	}

	public String getDataini() {
		return dataini;
	}

	public void setDataini(String dataini) {
		this.dataini = dataini;
	}

	public String getDatafim() {
		return datafim;
	}

	public void setDatafim(String datafim) {
		this.datafim = datafim;
	}

	public Integer getId_folhaevol() {
		return id_folhaevol;
	}

	public void setId_folhaevol(Integer id_folhaevol) {
		this.id_folhaevol = id_folhaevol;
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

	public String getData_folhaevol() {
		return data_folhaevol;
	}

	public void setData_folhaevol(String data_folhaevol) {
		this.data_folhaevol = data_folhaevol;
	}

	public String getDescricao_folhaevol() {
		return descricao_folhaevol;
	}

	public void setDescricao_folhaevol(String descricao_folhaevol) {
		this.descricao_folhaevol = descricao_folhaevol;
	}

	public String getStatus_folhaevol() {
		return status_folhaevol;
	}

	public void setStatus_folhaevol(String status_folhaevol) {
		this.status_folhaevol = status_folhaevol;
	}

	public String getNome_ctepac() {
		return nome_ctepac;
	}

	public void setNome_ctepac(String nome_ctepac) {
		this.nome_ctepac = nome_ctepac;
	}

	public String getNome_cteprof() {
		return nome_cteprof;
	}

	public void setNome_cteprof(String nome_cteprof) {
		this.nome_cteprof = nome_cteprof;
	}

	@Override
	public String toString() {
		return "Folhaevolutiva [id_folhaevol=" + id_folhaevol + ", id_cteprof=" + id_cteprof + ", id_ctepac="
				+ id_ctepac + ", data_folhaevol=" + data_folhaevol + ", descricao_folhaevol=" + descricao_folhaevol
				+ ", status_folhaevol=" + status_folhaevol + ", nome_ctepac=" + nome_ctepac + ", nome_cteprof="
				+ nome_cteprof + ", tipo_cteprof=" + tipo_cteprof + ", dataini=" + dataini + ", datafim=" + datafim
				+ "]";
	}
}
