package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Receita;
import persistencia.ReceitaDao;

@ManagedBean(name="recmb")
@SessionScoped
public class ReceitaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Receita rec = new Receita();
	private ReceitaDao recDao = new ReceitaDao();
	
	public void adicionar(){
		try {
			recDao.adicionarReceita(rec);
			rec = new Receita();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Receita adicionado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Receita getRec() {
		return rec;
	}

	public void setRec(Receita rec) {
		this.rec = rec;
	}
}
