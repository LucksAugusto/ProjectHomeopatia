package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.ReceitaMedicamento;

public interface iReceitaMedicamentoDao {

	public void adicionarReceita(List<ReceitaMedicamento> itens) throws SQLException;
	public List<ReceitaMedicamento> consultarItensReceitas(int receita) throws SQLException;
	public List<ReceitaMedicamento> consultarItensReceitaMedicamentos(String medicamento) throws SQLException;
	public void excluirItensReceita(int receita) throws SQLException;
}
