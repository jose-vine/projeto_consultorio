package modelo.entidade;

public class Consulta {
	
	private Medico medico;
	private Paciente paciente;
	private Object horario;
	private float valor;
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Object getHorario() {
		return horario;
	}
	
	public void setHorario(Object horario) {
		this.horario = horario;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
