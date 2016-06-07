package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Medico;

public class BuilderMedico {

	public static Medico buildMedico(ResultSet rs) throws SQLException {

		Medico medico = new Medico();

		medico.setNome(rs.getString("nome"));
		medico.setEspecialidade(rs.getString("especialidade"));
		medico.setEndereco(rs.getString("endereco"));
		medico.setNumero(rs.getInt("numero"));
		medico.setTelefone(rs.getString("telefone"));
		medico.setCelular(rs.getString("celular"));
		medico.setEmail(rs.getString("email"));
	

		return medico;
	}

}
