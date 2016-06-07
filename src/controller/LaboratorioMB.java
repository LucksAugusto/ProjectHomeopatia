package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Laboratorio;
import persistencia.LaboratorioDao;

@ManagedBean(name = "labmb")
@SessionScoped
public class LaboratorioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Laboratorio lab;
	private LaboratorioDao labDao = new LaboratorioDao();
	private List<Laboratorio> lista = new ArrayList<Laboratorio>();
	private boolean editar;

	public LaboratorioMB() {
		setEditar(false);
		setLab(new Laboratorio());
		try {
			
			setLista(labDao.consultarListaLaboratorio());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Laboratorio getLab() {
		return lab;
	}

	public void setLab(Laboratorio lab) {
		this.lab = lab;
	}

	public void adicionar() {
		try {
			labDao.cadastraLaboratorio(lab);
			lab = new Laboratorio();

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório adicionado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void salvar() {
		FacesMessage msg = null;
		try {
			if (editar) {

				labDao.alterarLaboratorio(lab);
				
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório alterado com sucesso", "");
			setEditar(false);	
			} else {
				labDao.cadastraLaboratorio(lab);
				
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório adicionado com sucesso", "");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		lab = new Laboratorio();
		try {
			setLista(labDao.consultarListaLaboratorio());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String alterar(Laboratorio lab) {
		String pagina = "ConsultaLaboratorio";
		try {
			setLab(labDao.consultarLaboratorioCodigo(lab.getIdLaboratorio()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEditar(true);
		pagina = "laboratorio?faces-redirect=true";

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório alterado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return pagina;
	}

	public List<Laboratorio> getLista() {
		return lista;
	}

	public void setLista(List<Laboratorio> lista) {
		this.lista = lista;
	}

	public void remover(int id) {
		try {
			labDao.excluirLaboratorio(id);
			lab = new Laboratorio();
			setLista(labDao.consultarListaLaboratorio());

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratorio excluido com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}
}
