package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Especialidade;

public class BuilderEspecialidade {

	public static Especialidade buildEspecialidade(ResultSet rs) throws SQLException {

		Especialidade especialidade = new Especialidade();
		
		especialidade.setIdEspecialidade(rs.getInt("idEspecialidade"));
		especialidade.setEspecialidade(rs.getString("especialidade"));

		return especialidade;
	}
}
