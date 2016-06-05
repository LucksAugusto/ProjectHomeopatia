package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import builder.BuilderEspecialidade;
import entity.Especialidade;

public class EspecialidadeDao implements iEspecialidadeDao {

	private Connection con;
	private GenericDao gDao;

	public EspecialidadeDao() {
		gDao = new GenericDao();
		con = gDao.getConnection();
	}

	@Override
	public List<Especialidade> consultarEspecialidade() throws SQLException {
 
		String sql = "select * from Especialidade";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Especialidade> listaEspecialidade = new ArrayList<Especialidade>();
		while(rs.next()){
			Especialidade especialidade = new Especialidade();
			especialidade = BuilderEspecialidade.buildEspecialidade(rs);
			listaEspecialidade.add(especialidade);
		}
		rs.close();
		stmt.close();
		gDao.fechaConexao();
		return listaEspecialidade;
	}

	@Override
	public void cadastraEspecialidade(Especialidade especialidade) throws SQLException {
		String sql = "insert into Especialidade values (?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, especialidade.getEspecialidade());
		stmt.executeQuery();
		stmt.close();
		gDao.fechaConexao();
		
		
	}

}
