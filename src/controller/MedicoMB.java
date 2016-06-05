package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Medico;
import persistencia.MedicoDao;

@ManagedBean(name="medmb")
@SessionScoped
public class MedicoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MedicoDao medDao;
	private Medico med;
	
	public MedicoMB(){
		med = new Medico();
		medDao =new MedicoDao();
		
	}
	
	

	
	public void adicionar(){
		try {
			medDao.adicionarMedico(med);
			med = new Medico();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico adicionado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


	public Medico getMed() {
		return med;
	}


	public void setMed(Medico med) {
		this.med = med;
	}

}
