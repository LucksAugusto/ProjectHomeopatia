package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.ReceitaMedicamento;

public class BuilderReceitaMedicamento {

	public static ReceitaMedicamento buildReceitaMedicamento(ResultSet rs) throws SQLException{
		ReceitaMedicamento recMed = new ReceitaMedicamento();
		
		recMed.setIdReceita(rs.getInt("idReceita"));
		recMed.setNomeMedicamento(rs.getString("nomeMedicamento"));
		recMed.setPotencia(rs.getString("potencia"));
		
		return recMed;
		
	}
}
