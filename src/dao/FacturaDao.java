package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IFacturaDao;
import model.Factura;

public class FacturaDao implements IFacturaDao {

	@Override()
	public void createTable() throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"CREATE TABLE IF NOT EXISTS Factura (idFactura INT, idCliente INT, PRIMARY KEY (idFactura), FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente));");
			preparedStatement = conn.prepareStatement(sb.toString());
			preparedStatement.execute();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	@Override()
	public Factura getFactura(int id) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			Factura factura = null;
			String query = "SELECT * FROM Factura WHERE idFactura = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				factura = new Factura(result.getInt("idFactura"), result.getInt("idCliente"));
			}
			return factura;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public ArrayList<Factura> getFactura() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			ArrayList<Factura> facturas = new ArrayList<>();
			String query = "SELECT * FROM Factura";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				facturas.add(new Factura(result.getInt("idFactura"), result.getInt("idCliente")));
			}
			return facturas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public List<Factura> createFacturas(List<Factura> facturasACrear) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		List<Factura> facturas = new ArrayList<Factura>();
		try {
			for (Factura factura : facturasACrear) {
				Factura newFactura = createFactura(factura, conn);
				if (newFactura == null) {
					conn.rollback();
					System.out.println("Error al cargar facturas " + factura);
					return new ArrayList<>(0);
				}
				facturas.add(newFactura);
			}
			conn.commit();
			return facturas;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return new ArrayList<>(0);
		}
	};

	@Override()
	public Factura createFactura(Factura factura) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, factura.getId());
			preparedStatement.setInt(2, factura.getIdCliente());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return factura;
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
	 * @param factura
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private Factura createFactura(Factura factura, Connection conn) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, factura.getId());
			preparedStatement.setInt(2, factura.getIdCliente());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return factura;
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
