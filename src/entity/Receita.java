package entity;

import java.util.Date;

public class Receita {

	private int idReceita;
	private int crmMedico;
	private String paciente;
	private Date dataReceita  = new Date(); ;
	
	public int getIdReceita() {
		return idReceita;
	}
	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
	}
	public int getCrmMedico() {
		return crmMedico;
	}
	public void setCrmMedico(int crmMedico) {
		this.crmMedico = crmMedico;
	}
	public Date getDataReceita() {
		return dataReceita;
	}
	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
}
