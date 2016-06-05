package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Especialidade;
import persistencia.EspecialidadeDao;

@ManagedBean(name="espmb")
@SessionScoped
public class EspecialidadeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EspecialidadeDao especidalidadeDao;
	private Especialidade esp;
	
	public EspecialidadeMB(){
		esp = new Especialidade();
		especidalidadeDao =new EspecialidadeDao();
		
	}
	
	public EspecialidadeDao getEspecidalidadeDao() {
		return especidalidadeDao;
	}

	public void setEspecidalidadeDao(EspecialidadeDao especidalidadeDao) {
		this.especidalidadeDao = especidalidadeDao;
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}

	
	public void adicionar(){
		try {
			especidalidadeDao.cadastraEspecialidade(esp);
			esp = new Especialidade();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade adicionada com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


}
