package controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Medicamento;
import persistencia.MedicamentoDao;

@ManagedBean(name="medimb")
@SessionScoped
public class MedicamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Medicamento med = new Medicamento();
	private MedicamentoDao medDao= new MedicamentoDao();
	
	

	public void cadastrar(){
		try {
			
			medDao.adicionarMedicamento(med);
			med = new Medicamento();
			

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medicamento adicionado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Medicamento getMed() {
		return med;
	}

	public void setMed(Medicamento med) {
		this.med = med;
	}
	

}
