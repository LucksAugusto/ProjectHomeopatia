package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Laboratorio;
import persistencia.LaboratorioDao;

@ManagedBean(name="labmb")
@SessionScoped
public class LaboratorioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Laboratorio lab;
	private LaboratorioDao labDao = new LaboratorioDao();
	
	public LaboratorioMB(){
		setLab(new Laboratorio());
	}

	public Laboratorio getLab() {
		return lab;
	}

	public void setLab(Laboratorio lab) {
		this.lab = lab;
	}

	public void adicionar(){
		try {
			labDao.cadastraLaboratorio(lab);
			lab = new Laboratorio();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório adicionado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
