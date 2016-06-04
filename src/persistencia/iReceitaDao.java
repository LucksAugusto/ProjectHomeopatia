package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.Receita;

public interface iReceitaDao {

	public void adicionarReceita(Receita receita) throws SQLException;
	public List<Receita> consultarReceitas() throws SQLException;
	public List<Receita> consultarReceitaPaciente(String paciente) throws SQLException;
	public List<Receita> consultarReceitaMedico(String medico) throws SQLException;
	public void alterarReceita(Receita receita) throws SQLException;
	public void excluirReceita(int codigo) throws SQLException;
	
}
