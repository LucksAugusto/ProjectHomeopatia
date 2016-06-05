package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.Especialidade;

public interface iEspecialidadeDao {

	public List<Especialidade> consultarEspecialidade() throws SQLException;
	public void cadastraEspecialidade(Especialidade especialidade) throws SQLException;
	
}
