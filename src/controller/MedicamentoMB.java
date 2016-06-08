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

import entity.Laboratorio;
import entity.Medicamento;
import entity.Medico;
import persistencia.LaboratorioDao;
import persistencia.MedicamentoDao;
import persistencia.MedicoDao;

@ManagedBean(name = "medimb")
@SessionScoped
public class MedicamentoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Medico medico;
	private Laboratorio laboratorio = new Laboratorio();
	private Medicamento med;
	private MedicamentoDao medDao = new MedicamentoDao();
	private List<Medicamento> lista = new ArrayList<Medicamento>();
	private boolean editar;

	public MedicamentoMB() {
		setEditar(false);
		setMed(new Medicamento());
		try {
			setLista(medDao.listarMedicamento());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SelectItem> getMedicos() {
		MedicoDao mecDao = new MedicoDao();
		List<Medico> medicos = null;
		try {
			medicos = mecDao.listarMedico();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<SelectItem> itens = new ArrayList<SelectItem>(medicos.size());

		for (Medico m : medicos) {
			itens.add(new SelectItem(m.getCrm(), m.getNome()));
		}

		return itens;
	}
	
	public List<SelectItem> getLaboratorios() {
		LaboratorioDao labDao = new LaboratorioDao();
		List<Laboratorio> laboratorios = null;
		try {
			laboratorios = labDao.consultarListaLaboratorioID();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<SelectItem> itens = new ArrayList<SelectItem>(laboratorios.size());

		for (Laboratorio l : laboratorios) {
			itens.add(new SelectItem(l.getIdLaboratorio(), l.getNome()));
			System.out.println(l.getIdLaboratorio());
		}

		return itens;
	}
	
	public Medicamento getMed() {
		return med;
	}

	public void setMed(Medicamento med) {
		this.med = med;
	}


	public void salvar() {
		FacesMessage msg = null;
		try {
			if (editar) {
				System.out.println(med.getConteudo());
				medDao.alterarMedicamento(med);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medicamento alterado com sucesso", "");
				setEditar(false);
			} else {
				
				medDao.adicionarMedicamento(med);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medicamento adicionado com sucesso", "");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		med = new Medicamento();
		
		try {
			setLista(medDao.listarMedicamento());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String alterar(Medicamento med) {
		String pagina = "ConsultaMedicamento";
		try {
			setMed(medDao.consultaMedicamentoAlterar(med.getIdMedicamento()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEditar(true);
		pagina = "medicamento?faces-redirect=true";

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medicamento alterado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return pagina;
	}

	public List<Medicamento> getLista() {
		return lista;
	}

	public void setLista(List<Medicamento> lista) {
		this.lista = lista;
	}

	public void remover(int id) {
		try {
			System.out.println(id);
			medDao.excluirMedicamento(id);
			med = new Medicamento();
			setLista(medDao.listarMedicamento());

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medicamento excluido com sucesso", "");
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

	
	public MedicamentoDao getMedDao() {
		return medDao;
	}

	public void setMedDao(MedicamentoDao medDao) {
		this.medDao = medDao;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
}
