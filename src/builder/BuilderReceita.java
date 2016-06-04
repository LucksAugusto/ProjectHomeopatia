package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Receita;

public class BuilderReceita {

	public static Receita buildReceita(ResultSet rs) throws SQLException{
		Receita receita = new Receita();
		
		receita.setIdReceita(rs.getInt("idReceita"));
		receita.setCrmMedico(rs.getInt("crmMedico"));
		receita.setPaciente(rs.getString("paciente"));
		receita.setDataReceita(rs.getDate("data"));
		
		return receita;
	}
}
