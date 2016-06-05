package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.Medico;

public interface iMedicoDao {

	public void adicionarMedico(Medico medico) throws SQLException;
	public List<Medico> listarMedico() throws SQLException;
	public  List<Medico> consultaMedicoCRM(int crm) throws SQLException;
	public List<Medico> consultaMedicoNome(String nome) throws SQLException;
	public void alterarMedico(Medico medico) throws SQLException;
	public void excluirMedico(int crm) throws SQLException;

}
