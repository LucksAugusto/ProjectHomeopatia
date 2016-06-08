package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Medico;

public class BuilderMedico {

	public static Medico buildMedico(ResultSet rs) throws SQLException {

		Medico medico = new Medico();

		medico.setCrm(rs.getInt("crm"));
		medico.setNome(rs.getString("nome"));
		medico.setEspecialidade(rs.getString("especialidade"));
		medico.setEndereco(rs.getString("endereco"));
		medico.setNumero(rs.getInt("numero"));
		medico.setTelefone(rs.getString("telefone"));
		medico.setCelular(rs.getString("celular"));
		medico.setEmail(rs.getString("email"));

		return medico;
	}

	public static Medico buildMedicoCompleto(ResultSet rs) throws SQLException {

		Medico medico = new Medico();

			medico.setNome(rs.getString("nome"));
			medico.setCrm(rs.getInt("crm"));
		 		medico.setEspecialidade(rs.getString("idEspecialidade"));
				medico.setEndereco(rs.getString("endereco"));
				medico.setNumero(rs.getInt("numero"));
				medico.setBairro(rs.getString("bairro"));
				medico.setCep(rs.getString("cep"));
				medico.setCidade(rs.getString("cidade"));
				medico.setEstado(rs.getString("estado"));
				medico.setTelefone(rs.getString("telefone"));
				medico.setCelular(rs.getString("celular"));
				medico.setEmail(rs.getString("email"));
				medico.setCoordenadas(rs.getString("coordenadas"));

		return medico;
	}

}
