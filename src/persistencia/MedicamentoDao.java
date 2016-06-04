package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import builder.BuilderMedicamento;
import entity.Medicamento;

public class MedicamentoDao implements iMedicamentoDao {


	private Connection con;
	private GenericDao gDao;

	public MedicamentoDao() {
		gDao = new GenericDao();
		con = gDao.getConnection();
	
	}
	
	@Override
	public void adicionarMedicamento(Medicamento medicamento) throws SQLException {
		String sql = "insert into Medicamento values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, medicamento.getNome());
		stmt.setString(2, medicamento.getPotencia());
		stmt.setInt (3, medicamento.getCrmMedico());
		stmt.setString (4, medicamento.getPaciente());;
		stmt.setInt (5, medicamento.getIdLaboratorio());;
		stmt.setDate (6, medicamento.getDataManipulacao());
		stmt.setDate (7, medicamento.getDataVencimento());
		stmt.setString (8, medicamento.getComposicao());;
		stmt.setString(9, medicamento.getConteudo());
		stmt.setString (10, medicamento.getEmbalagem());;
		stmt.setString(11, medicamento.getObservacao());
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();
	}

	@Override
	public List<Medicamento> listarMedicamento() throws SQLException {
		String sql = "select * from Medicamento";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		while (rs.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamento(rs);
			listaMedicamento.add(medicamento);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaMedicamento;
	}

	@Override
	public  List<Medicamento> consultaMedicamentoComposicao(String composicao) throws SQLException {
		String sql = "select * from Medicamento where composicao=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + composicao + "%");
		ResultSet rs = stmt.executeQuery();
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		while (rs.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamento(rs);
			listaMedicamento.add(medicamento);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoNome(String nome) throws SQLException {
		String sql = "select * from Medicamento where nome=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + nome + "%");
		ResultSet rs = stmt.executeQuery();
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		while (rs.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamento(rs);
			listaMedicamento.add(medicamento);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoVencidos() throws SQLException {
		String sql = "select * from Medicamento where dataVencimento< CURRENT_TIMESTAMP";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		while (rs.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamento(rs);
			listaMedicamento.add(medicamento);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoMedico(int crm) throws SQLException {
		String sql = "select * from Medicamento where idMedico=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,crm);
		ResultSet rs = stmt.executeQuery();
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		while (rs.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamento(rs);
			listaMedicamento.add(medicamento);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaMedicamento;
	}
	
	@Override
	public void alterarMedicamento(Medicamento medicamento) throws SQLException {
		String sql = "update Medicamento set nome=?, potencia=?, paciente=?, idMedico=?, idLaboratorio=?, dataManipulucao=?, dataVencimento=?, conteudo=?, composicao=?, embalagem=?, orientacao=? where idMedicamento=? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, medicamento.getNome());
		stmt.setString(2, medicamento.getPotencia());
		stmt.setInt (3, medicamento.getCrmMedico());
		stmt.setString (4, medicamento.getPaciente());;
		stmt.setInt (5, medicamento.getIdLaboratorio());;
		stmt.setDate (6, medicamento.getDataManipulacao());
		stmt.setDate (7, medicamento.getDataVencimento());
		stmt.setString (8, medicamento.getComposicao());;
		stmt.setString(9, medicamento.getConteudo());
		stmt.setString (10, medicamento.getEmbalagem());;
		stmt.setString(11, medicamento.getObservacao());
		stmt.setInt(12, medicamento.getIdMedicamento());
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();

	}

	@Override
	public void excluirMedicamento(int codigo) throws SQLException {
		String sql = "delete from Medicamento where idMedicamento=? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();
		
	}

	

}
