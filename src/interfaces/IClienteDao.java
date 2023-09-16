package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.RespuestaClienteFacturacion;

public interface IClienteDao {

	/**
	 * 
	 * @throws SQLException
	 */
	public void createTable() throws SQLException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Cliente getCliente(int id) throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Cliente> getClientes() throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<RespuestaClienteFacturacion> getClientesPorFacturacion() throws SQLException;

	/**
	 * 
	 * @param cliente
	 * @return
	 * @throws SQLException
	 */
	public Cliente createCliente(Cliente cliente) throws SQLException;

	/**
	 * 
	 * @param clientes
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> createClientes(List<Cliente> clientes) throws SQLException;

	/**
	 * 
	 * @param cliente
	 * @return
	 * @throws SQLException
	 */
	public Cliente updateCliente(Cliente cliente) throws SQLException;

}
