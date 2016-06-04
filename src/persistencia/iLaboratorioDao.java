package persistencia;

import java.sql.SQLException;
import java.util.List;

import entity.Laboratorio;

public interface iLaboratorioDao {

	public void cadastraLaboratorio(Laboratorio lab) throws SQLException;
	public void alterarLaboratorio(Laboratorio lab) throws SQLException;
	public List<Laboratorio> consultarListaLaboratorio() throws SQLException;
	public List<Laboratorio> consultarLaboratorioNome(String nome) throws SQLException;
	public List<Laboratorio> consultarLaboratorioCodigo(int codigo) throws SQLException;
	public void excluirLaboratorio(int codigo)throws SQLException;
}
