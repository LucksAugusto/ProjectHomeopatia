package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Medicamento;

public class BuilderMedicamento {

	public static Medicamento buildMedicamento(ResultSet rs) throws SQLException {
		Medicamento medicamento = new Medicamento();
		
		medicamento.setNome(rs.getString("nome"));
		medicamento.setPotencia(rs.getString("potencia"));
		medicamento.setPaciente(rs.getString("paciente"));
		medicamento.setDataManipulacao(rs.getDate("dataManipulucao"));
		medicamento.setDataVencimento(rs.getDate("dataVencimento"));
		
		
		return medicamento;
		
	}
	public static Medicamento buildMedicamentoCompleto(ResultSet rs) throws SQLException {
		Medicamento medicamento = new Medicamento();
		
		medicamento.setIdMedicamento(rs.getInt("idMedicamento"));
		medicamento.setNome(rs.getString("nome"));
		medicamento.setPotencia(rs.getString("potencia"));
		medicamento.setCrmMedico(rs.getInt("idMedico"));
		medicamento.setPaciente(rs.getString("paciente"));
		medicamento.setIdLaboratorio(rs.getInt("idLaboratorio"));
		medicamento.setDataManipulacao(rs.getDate("dataManipulucao"));
		medicamento.setDataVencimento(rs.getDate("dataVencimento"));
		medicamento.setConteudo(rs.getString("conteudo"));
		medicamento.setComposicao(rs.getString("composicao"));
		medicamento.setEmbalagem(rs.getString("embalagem"));
		medicamento.setObservacao(rs.getString("orientacao"));
		
		
		return medicamento;
		
	}
}
