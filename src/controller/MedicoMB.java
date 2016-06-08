package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import entity.Especialidade;
import entity.Medico;
import persistencia.EspecialidadeDao;
import persistencia.MedicoDao;

@ManagedBean(name="medmb")
@SessionScoped
public class MedicoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Especialidade esp;
	private MedicoDao medDao = new MedicoDao();
	private Medico med = new Medico();
	private boolean editar;
	private List<Medico> lista = new ArrayList<Medico>();
	
	
	public MedicoMB(){
		setEditar(false);
		setMed(new Medico());
		try {
			setLista(medDao.listarMedico());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<SelectItem> getEspecialidades() {
		EspecialidadeDao espDao = new EspecialidadeDao();
		List<Especialidade> especialidades = null;
		try {
			especialidades = espDao.consultarEspecialidade();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<SelectItem> itens = new ArrayList<SelectItem>(especialidades.size());

		for (Especialidade e : especialidades) {
			itens.add(new SelectItem(e.getIdEspecialidade(), e.getEspecialidade()));
		
		}

		return itens;
	}
	
	public void salvar() {
		FacesMessage msg = null;
		try {
			if (editar) {
			
				medDao.alterarMedico(med);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico alterado com sucesso", "");
				setEditar(false);
			} else {
				
				medDao.adicionarMedico(med);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico adicionado com sucesso", "");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		med = new Medico();
		
		try {
			setLista(medDao.listarMedico());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String alterar(Medico med) {
		String pagina = "ConsultaMedicamento";
		try {
			setMed(medDao.consultaMedicoAlterar(med.getCrm()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEditar(true);
		pagina = "medico?faces-redirect=true";

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico alterado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return pagina;
	}

	public void remover(){
		try {
			medDao.excluirMedico(med.getCrm());
			med = new Medico();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico excluido com sucesso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void pesquisar() { 
		try {
			
			setLista(medDao.listarMedico());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Foram encontrados " + lista.size() + " registros", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Medico getMed() {
		return med;
	}


	public void setMed(Medico med) {
		this.med = med;
	}
	public void setLista(List<Medico> lista) {
		this.lista = lista;
	}
	public List<Medico> getLista() {
		return lista;
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}


	public boolean isEditar() {
		return editar;
	}


	public void setEditar(boolean editar) {
		this.editar = editar;
	}


}
