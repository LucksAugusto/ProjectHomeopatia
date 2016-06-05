package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Especialidade;
import persistencia.EspecialidadeDao;

@ManagedBean
@SessionScoped
public class EspecialidadeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EspecialidadeDao especidalidadeDao = new EspecialidadeDao();
	private Especialidade especialidade = new Especialidade();
	
	
	
	public String adicionar(){
		try {
			especidalidadeDao.cadastraEspecialidade(especialidade);
			especialidade = new Especialidade();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade adicionada com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return "";
	}

}
