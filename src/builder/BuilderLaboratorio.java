package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Laboratorio;

public class BuilderLaboratorio {

	public static Laboratorio buildLaboratorio(ResultSet rs) throws SQLException {

		Laboratorio laboratorio = new Laboratorio();

		laboratorio.setIdLaboratorio(rs.getInt("idLaboratorio"));
		laboratorio.setNome(rs.getString("nome"));
		laboratorio.setEndereco(rs.getString("endereco"));
		laboratorio.setNumero(rs.getInt("numero"));
		laboratorio.setBairro(rs.getString("bairro")); 
		laboratorio.setCep(rs.getString("cep")); 
		laboratorio.setCidade(rs.getString("cidade")); 
		laboratorio.setEstado(rs.getString("estado")); 
		laboratorio.setTelefone(rs.getString("telefone")); 
		laboratorio.setFax(rs.getString("fax"));
		laboratorio.setEmail(rs.getString("email"));
		laboratorio.setCoordenadas(rs.getString("coordenadas")); 
		laboratorio.setAtendimento(rs.getString("atendimento"));
		
		return laboratorio;
	}
}
