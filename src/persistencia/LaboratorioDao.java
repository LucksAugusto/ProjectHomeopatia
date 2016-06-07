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
		stmt.executeUpdate();
		
		stmt.close();
		
	}

	@Override
	public List<Laboratorio> consultarListaLaboratorio() throws SQLException {
		String sql = "select idLaboratorio, nome from Laboratorio";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
		while (rs.next()) {
			Laboratorio laboratorio = new Laboratorio();
			laboratorio.setIdLaboratorio(rs.getInt("idLaboratorio"));
			laboratorio.setNome(rs.getString("nome"));
			listaLaboratorio.add(laboratorio);
		}
		
		rs.close();
		stmt.close();
	
		
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
		
		
		return listaLaboratorio;
	}

	@Override
	public Laboratorio consultarLaboratorioCodigo(int codigo) throws SQLException {
		String sql = "select * from Laboratorio where idLaboratorio=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		ResultSet rs = stmt.executeQuery();
		Laboratorio laboratorio = null;
		if(rs.next()){
			laboratorio = new Laboratorio();
			laboratorio = BuilderLaboratorio.buildLaboratorio(rs);
		}
		
		rs.close();
		stmt.close();
		
		return laboratorio;
	}

	@Override
	public void excluirLaboratorio(int codigo) throws SQLException {
		System.out.println(codigo);
		String sql = "delete from Laboratorio where idLaboratorio=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		stmt.executeUpdate();
		
		stmt.close();
		
	}

}
