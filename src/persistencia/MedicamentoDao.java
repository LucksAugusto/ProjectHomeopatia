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
		stmt.setString(3, medicamento.getPaciente());
		;
		stmt.setInt(4, medicamento.getCrmMedico());
		stmt.setInt(5, medicamento.getIdLaboratorio());
		;
		java.sql.Date sd = new java.sql.Date(medicamento.getDataManipulacao().getTime());
		stmt.setDate(6, sd);
		java.sql.Date sd2 = new java.sql.Date(medicamento.getDataVencimento().getTime());
		stmt.setDate(7, sd2);
		stmt.setString(8, medicamento.getComposicao());
		;
		stmt.setString(9, medicamento.getConteudo());
		stmt.setString(10, medicamento.getEmbalagem());
		;
		stmt.setString(11, medicamento.getObservacao());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public List<Medicamento> listarMedicamento() throws SQLException {
		String sql = "select nome, potencia, paciente,dataManipulucao, dataVencimento from Medicamento";
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
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoComposicao(String composicao) throws SQLException {
		String sql = "select nome, potencia, paciente,dataManipulucao, dataVencimento from Medicamento where composicao=?";
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
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoNome(String nome) throws SQLException {
		String sql = "select nome, potencia, paciente,dataManipulucao, dataVencimento from Medicamento where nome=?";
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
		return listaMedicamento;
	}

	@Override
	public List<Medicamento> consultaMedicamentoVencidos() throws SQLException {
		String sql = "select nome, potencia, paciente,dataManipulucao, dataVencimento from Medicamento where dataVencimento< CURRENT_TIMESTAMP";
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
		return listaMedicamento;
	}

	@Override
	public Medicamento consultaMedicamentoAlterar(int id) throws SQLException {
		String sql = "select * from Medicamento where idMedicamento=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Medicamento medicamento = null;
		if (rs.next()) {
		 medicamento = new Medicamento();
			medicamento = BuilderMedicamento.buildMedicamentoCompleto(rs);
		}

		rs.close();
		stmt.close();

		return medicamento;
	}

	@Override
	public void alterarMedicamento(Medicamento medicamento) throws SQLException {
		String sql = "update Medicamento set nome=?, potencia=?, paciente=?, idMedico=?, idLaboratorio=?, dataManipulucao=?, dataVencimento=?, conteudo=?, composicao=?, embalagem=?, orientacao=? where idMedicamento=? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, medicamento.getNome());
		stmt.setString(2, medicamento.getPotencia());
		stmt.setInt(3, medicamento.getCrmMedico());
		stmt.setString(4, medicamento.getPaciente());

		stmt.setInt(5, medicamento.getIdLaboratorio());

		java.sql.Date sd = new java.sql.Date(medicamento.getDataManipulacao().getTime());
		stmt.setDate(6, sd);
		java.sql.Date sd2 = new java.sql.Date(medicamento.getDataVencimento().getTime());
		stmt.setDate(7, sd2);
		stmt.setString(8, medicamento.getComposicao());

		stmt.setString(9, medicamento.getConteudo());
		stmt.setString(10, medicamento.getEmbalagem());

		stmt.setString(11, medicamento.getObservacao());
		stmt.setInt(12, medicamento.getIdMedicamento());
		stmt.executeQuery();
		stmt.close();

	}

	@Override
	public void excluirMedicamento(int codigo) throws SQLException {
		String sql = "delete from Medicamento where idMedicamento=? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		stmt.executeUpdate();
		stmt.close();

	}

}
