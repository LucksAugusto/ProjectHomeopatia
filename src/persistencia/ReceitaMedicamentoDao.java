package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import builder.BuilderReceitaMedicamento;
import entity.ReceitaMedicamento;

public class ReceitaMedicamentoDao implements iReceitaMedicamentoDao {

	private Connection con;
	private GenericDao gDao;

	public ReceitaMedicamentoDao() {
		gDao = new GenericDao();
		con = gDao.getConnection();
	}

	@Override
	public void adicionarReceita(List<ReceitaMedicamento> itens) throws SQLException {
			
		String sql ="INSERT INTO ReceitaMedicamento values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement( sql );
						
			for(ReceitaMedicamento itensReceita: itens){
				stmt.setInt(1, itensReceita.getIdReceita());
				stmt.setString(2, itensReceita.getNomeMedicamento());
				stmt.setString(3, itensReceita.getPotencia());
				stmt.executeUpdate();
			}
			
		stmt.close();
		gDao.fechaConexao();				
	}

	@Override
	public List<ReceitaMedicamento> consultarItensReceitas(int receita) throws SQLException {

		String sql = "select * from ReceitaMedicamento where idReceita=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, receita);
		ResultSet rs = stmt.executeQuery();
		List<ReceitaMedicamento> listaItensReceita = new ArrayList<ReceitaMedicamento>();
		while(rs.next()){
			ReceitaMedicamento itens = new ReceitaMedicamento();
			itens = BuilderReceitaMedicamento.buildReceitaMedicamento(rs);
			listaItensReceita.add(itens);
		}
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		return listaItensReceita;
	}
	

	@Override
	public List<ReceitaMedicamento> consultarItensReceitaMedicamentos(String medicamento) throws SQLException {
		String sql = "select * from ReceitaMedicamento where nomeMedicamento=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%" + medicamento +"%");
		ResultSet rs = stmt.executeQuery();
		List<ReceitaMedicamento> listaItensReceita = new ArrayList<ReceitaMedicamento>();
		while(rs.next()){
			ReceitaMedicamento itens = new ReceitaMedicamento();
			itens = BuilderReceitaMedicamento.buildReceitaMedicamento(rs);
			listaItensReceita.add(itens);
		}
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		return listaItensReceita;
	}


	@Override
	public void excluirItensReceita(int receita) throws SQLException {
		String sql = "delete from ReceitaMedicamento where idReceita=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, receita);
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();

	}

}
