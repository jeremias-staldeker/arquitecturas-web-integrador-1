package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IClienteDao;
import model.Cliente;
import model.RespuestaClienteFacturacion;

public class ClienteDao implements IClienteDao {

	@Override()
	public void createTable() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE IF NOT EXISTS Cliente (idCliente INT, nombre VARCHAR(255), email VARCHAR(255), PRIMARY KEY (idCliente));");
			PreparedStatement preparedStatement = conn.prepareStatement(sb.toString());
			preparedStatement.execute();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override()
	public Cliente getCliente(int id) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			Cliente cliente = null;
			String query = "SELECT * FROM Cliente WHERE idCliente = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				cliente = new Cliente(result.getInt("idCliente"), result.getString("nombre"),
						result.getString("email"));
			}
			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public ArrayList<Cliente> getClientes() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			ArrayList<Cliente> clientes = new ArrayList<>();
			String query = "SELECT * FROM Cliente";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				clientes.add(new Cliente(result.getInt("id"), result.getString("nombre"), result.getString("email")));
			}

			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public List<RespuestaClienteFacturacion> getClientesPorFacturacion() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			List<RespuestaClienteFacturacion> clientes = new ArrayList<RespuestaClienteFacturacion>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT f.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS recaudacion ");
			sb.append("FROM Factura_Producto fp ");
			sb.append("INNER JOIN Producto p ON fp.idProducto = p.idProducto ");
			sb.append("INNER JOIN Factura f ON fp.idFactura = f.idFactura ");
			sb.append("INNER JOIN Cliente c ON f.idCliente = c.idCliente ");
			sb.append("GROUP BY f.idCliente ");
			sb.append("ORDER BY recaudacion DESC ");
			PreparedStatement preparedStatement = conn.prepareStatement(sb.toString());
			ResultSet result = preparedStatement.executeQuery(sb.toString());
			while (result.next()) {
				clientes.add(new RespuestaClienteFacturacion(result.getInt("idCliente"), result.getString("nombre"),
						result.getString("email"), result.getFloat("recaudacion")));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<RespuestaClienteFacturacion>(0);
		}
	}

	@Override()
	public List<Cliente> createClientes(List<Cliente> clientesACrear) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			for (Cliente cliente : clientesACrear) {
				Cliente newClient = createCliente(cliente, conn);
				if (newClient == null) {
					conn.rollback();
					System.out.println("Error al cargar clientes " + cliente);
					return new ArrayList<>(0);
				}
				clientes.add(newClient);
			}
			conn.commit();
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return new ArrayList<>(0);
		}
	}

	@Override()
	public Cliente createCliente(Cliente cliente) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, cliente.getId());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getEmail());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				conn.commit();
				return cliente;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	@Override()
	public Cliente updateCliente(Cliente cliente) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			String query = "UPDATE FROM Cliente SET (nombre = ?, email = ?) WHERE idCliente = ?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, cliente.getNombre());
			preparedStatement.setString(2, cliente.getEmail());
			preparedStatement.setInt(3, cliente.getId());
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return cliente;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	/**
	 * 
	 * @param cliente
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private Cliente createCliente(Cliente cliente, Connection conn) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, cliente.getId());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getEmail());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return cliente;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}
}
