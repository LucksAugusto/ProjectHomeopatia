package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import builder.BuilderLaboratorio;
import entity.Laboratorio;

public class LaboratorioDao implements iLaboratorioDao {

	private Connection con;
	private GenericDao gDao;

	public LaboratorioDao() {
		gDao = new GenericDao();
		con = gDao.getConnection();

	}

	@Override
	public void cadastraLaboratorio(Laboratorio lab) throws SQLException {
		String sql = "insert into Laboratorio values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, lab.getNome());
		stmt.setString(2, lab.getEndereco());
		stmt.setInt(3, lab.getNumero());
		stmt.setString(4, lab.getBairro());
		stmt.setString(5, lab.getCep());
		stmt.setString(6, lab.getCidade());
		stmt.setString(7, lab.getEstado());
		stmt.setString(8, lab.getTelefone());
		stmt.setString(9, lab.getFax());
		stmt.setString(10, lab.getEmail());
		stmt.setString(11, lab.getCoordenadas());
		stmt.setString(12, lab.getAtendimento());
		stmt.executeUpdate();
		stmt.close();
		gDao.fechaConexao();
	}

	@Override
	public void alterarLaboratorio(Laboratorio lab) throws SQLException {
		String sql = "update Laboratorio set nome=?, endereco=?, numero=?, bairro=?, cep=?, cidade=?, estado=?, telefone=?, fax=?, email=?, coordenadas=?, atendimento=? where idLaboratorio =?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, lab.getNome());
		stmt.setString(2, lab.getEndereco());
		stmt.setInt(3, lab.getNumero());
		stmt.setString(4, lab.getBairro());
		stmt.setString(5, lab.getCep());
		stmt.setString(6, lab.getCidade());
		stmt.setString(7, lab.getEstado());
		stmt.setString(8, lab.getTelefone());
		stmt.setString(9, lab.getFax());
		stmt.setString(10, lab.getEmail());
		stmt.setString(11, lab.getCoordenadas());
		stmt.setString(12, lab.getAtendimento());
		stmt.setInt(13, lab.getIdLaboratorio());
		
		stmt.close();
		gDao.fechaConexao();
	}

	@Override
	public List<Laboratorio> consultarListaLaboratorio() throws SQLException {
		String sql = "select * from Laboratorio";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
		while (rs.next()) {
			Laboratorio laboratorio = new Laboratorio();
			laboratorio = BuilderLaboratorio.buildLaboratorio(rs);
			listaLaboratorio.add(laboratorio);
		}
		
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaLaboratorio;
	}

	@Override
	public List<Laboratorio> consultarLaboratorioNome(String nome) throws SQLException {
		String sql = "select * from Laboratorio where nome like ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + nome + "%");
		ResultSet rs = stmt.executeQuery();
		List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
		while (rs.next()) {
			Laboratorio laboratorio = new Laboratorio();
			laboratorio = BuilderLaboratorio.buildLaboratorio(rs);
			listaLaboratorio.add(laboratorio);
		}
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaLaboratorio;
	}

	@Override
	public List<Laboratorio> consultarLaboratorioCodigo(int codigo) throws SQLException {
		String sql = "select * from Laboratorio where idLaboratorio=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		ResultSet rs = stmt.executeQuery();
		List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
		while (rs.next()) {
			Laboratorio laboratorio = new Laboratorio();
			laboratorio = BuilderLaboratorio.buildLaboratorio(rs);
			listaLaboratorio.add(laboratorio);
		}
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		
		return listaLaboratorio;
	}

	@Override
	public void excluirLaboratorio(int codigo) throws SQLException {
		String sql = "delete from Laboratorio where idLaboratorio=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		stmt.executeQuery();
		
		stmt.close();
		gDao.fechaConexao();

	}

}
