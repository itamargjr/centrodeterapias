package entity;

public class Profissionais {
	private Integer id_cteprof;	
	private String tipo_cteprof;	
	private String nome_cteprof;	
	private String registro_cteprof;	
	private String endereco_cteprof;	
	private String telefone_cteprof;	
	private String cpf_cteprof;	
	private String email_cteprof;
	
	public Profissionais() {
		super();
	}

	public Profissionais(Integer id_cteprof, String tipo_cteprof, String nome_cteprof, String registro_cteprof,
			String endereco_cteprof, String telefone_cteprof, String cpf_cteprof, String email_cteprof) {
		super();
		this.id_cteprof = id_cteprof;
		this.tipo_cteprof = tipo_cteprof;
		this.nome_cteprof = nome_cteprof;
		this.registro_cteprof = registro_cteprof;
		this.endereco_cteprof = endereco_cteprof;
		this.telefone_cteprof = telefone_cteprof;
		this.cpf_cteprof = cpf_cteprof;
		this.email_cteprof = email_cteprof;
	}

	public Integer getId_cteprof() {
		return id_cteprof;
	}

	public void setId_cteprof(Integer id_cteprof) {
		this.id_cteprof = id_cteprof;
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

	public String getRegistro_cteprof() {
		return registro_cteprof;
	}

	public void setRegistro_cteprof(String registro_cteprof) {
		this.registro_cteprof = registro_cteprof;
	}

	public String getEndereco_cteprof() {
		return endereco_cteprof;
	}

	public void setEndereco_cteprof(String endereco_cteprof) {
		this.endereco_cteprof = endereco_cteprof;
	}

	public String getTelefone_cteprof() {
		return telefone_cteprof;
	}

	public void setTelefone_cteprof(String telefone_cteprof) {
		this.telefone_cteprof = telefone_cteprof;
	}

	public String getCpf_cteprof() {
		return cpf_cteprof;
	}

	public void setCpf_cteprof(String cpf_cteprof) {
		this.cpf_cteprof = cpf_cteprof;
	}

	public String getEmail_cteprof() {
		return email_cteprof;
	}

	public void setEmail_cteprof(String email_cteprof) {
		this.email_cteprof = email_cteprof;
	}

	@Override
	public String toString() {
		return "Profissionais [id_cteprof=" + id_cteprof + ", tipo_cteprof=" + tipo_cteprof + ", nome_cteprof="
				+ nome_cteprof + ", registro_cteprof=" + registro_cteprof + ", endereco_cteprof=" + endereco_cteprof
				+ ", telefone_cteprof=" + telefone_cteprof + ", cpf_cteprof=" + cpf_cteprof + ", email_cteprof="
				+ email_cteprof + "]";
	}
	
	
}
