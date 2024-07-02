package entity;

public class Horarios {
	private Integer id_horario;
	private String descricao_horario;
	
	public Horarios() {
		super();
	}

	public Horarios(Integer id_horario, String descricao_horario) {
		super();
		this.id_horario = id_horario;
		this.descricao_horario = descricao_horario;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public String getDescricao_horario() {
		return descricao_horario;
	}

	public void setDescricao_horario(String descricao_horario) {
		this.descricao_horario = descricao_horario;
	}

	@Override
	public String toString() {
		return "Horarios [id_horario=" + id_horario + ", descricao_horario=" + descricao_horario + "]";
	}
}
