package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import builder.BuilderReceita;
import entity.Receita;

public class ReceitaDao implements iReceitaDao{


	private Connection con;
	private GenericDao gDao;

	public ReceitaDao() {
		gDao = new GenericDao();
		con = gDao.getConnection();
	}

	@Override
	public void adicionarReceita(Receita receita) throws SQLException {
		String sql = "insert into Receita values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, receita.getCrmMedico());
		stmt.setString(1, receita.getPaciente());
		stmt.setDate(1, receita.getDataReceita());
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();
		
	}

	@Override
	public List<Receita> consultarReceitas() throws SQLException {
		String sql = "select * from Receita";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Receita> listaReceita = new ArrayList<Receita>();
		while (rs.next()) {
			Receita receita = new Receita();
			receita = BuilderReceita.buildReceita(rs);
			listaReceita.add(receita);
		}

		rs.close();
		stmt.close();
		gDao.fechaConexao();

		return listaReceita;
	}

	@Override
	public List<Receita> consultarReceitaPaciente(String paciente) throws SQLException {
		String sql = "select * from Receita where paciente=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + paciente + "%");
		ResultSet rs = stmt.executeQuery();
		List<Receita> listaReceita = new ArrayList<Receita>();
		while (rs.next()) {
			Receita receita = new Receita();
			receita = BuilderReceita.buildReceita(rs);
			listaReceita.add(receita);
		}

		rs.close();
		stmt.close();
		gDao.fechaConexao();

		return listaReceita;
	}

	@Override
	public List<Receita> consultarReceitaMedico(String medico) throws SQLException {
		String sql = "select * from Receita inner join Medico on Receita.crmMedico=Medico.crm where Medico.nome=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + medico + "%");
		ResultSet rs = stmt.executeQuery();
		List<Receita> listaReceita = new ArrayList<Receita>();
		while (rs.next()) {
			Receita receita = new Receita();
			receita = BuilderReceita.buildReceita(rs);
			listaReceita.add(receita);
		}

		rs.close();
		stmt.close();
		gDao.fechaConexao();

		return listaReceita;
	}

	@Override
	public void alterarReceita(Receita receita) throws SQLException {
		String sql = "update Receita set crmMedico=?, paciente=?, data=? where idReceita=? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, receita.getCrmMedico());
		stmt.setString(1, receita.getPaciente());
		stmt.setDate(1, receita.getDataReceita());
		stmt.setInt(1, receita.getIdReceita());
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();
		
	}

	@Override
	public void excluirReceita(int codigo) throws SQLException {
		String sql = "select * from Receita where idReceita=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, codigo);
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();

		
	}


}
