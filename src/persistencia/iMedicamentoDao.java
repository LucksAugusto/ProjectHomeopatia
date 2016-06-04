package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.Medicamento;

public interface iMedicamentoDao {

	public void adicionarMedicamento(Medicamento medicamento) throws SQLException;
	public List<Medicamento> listarMedicamento() throws SQLException;
	public  List<Medicamento> consultaMedicamentoComposicao(String composicao) throws SQLException;
	public  List<Medicamento> consultaMedicamentoMedico(int crm) throws SQLException;
	public List<Medicamento> consultaMedicamentoNome(String nome) throws SQLException;
	public List<Medicamento> consultaMedicamentoVencidos() throws SQLException;
	public void alterarMedicamento(Medicamento medicamento) throws SQLException;
	public void excluirMedicamento(int codigo) throws SQLException;
}
