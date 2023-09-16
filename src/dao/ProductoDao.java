package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IProductoDao;
import model.Producto;

public class ProductoDao implements IProductoDao {

	@Override()
	public void createTable() throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE IF NOT EXISTS Producto (idProducto INT, nombre VARCHAR(255), valor FLOAT, PRIMARY KEY (idProducto));");
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
	public Producto getProducto(int id) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			Producto producto = null;
			String query = "SELECT * FROM Producto WHERE id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				producto = new Producto(result.getInt("idProducto"), result.getString("nombre"),
						result.getFloat("valor"));
			}
			return producto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public ArrayList<Producto> getProductos() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			ArrayList<Producto> productos = new ArrayList<>();
			String query = "SELECT * FROM Producto";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				productos.add(new Producto(result.getInt("idProducto"), result.getString("nombre"),
						result.getFloat("valor")));
			}

			return productos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override()
	public List<Producto> createProducts(List<Producto> productosACrear) throws SQLException {
		Connection conn = MySqlDao.getConnection();
		List<Producto> productos = new ArrayList<Producto>();
		try {
			for (Producto producto : productosACrear) {
				Producto newProduct = createProduct(producto, conn);
				if (newProduct == null) {
					conn.rollback();
					System.out.println("Error al cargar productos " + producto);
					return new ArrayList<>(0);
				}
				productos.add(newProduct);
			}
			conn.commit();
			return productos;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return new ArrayList<>(0);
		}
	};

	@Override()
	public Producto createProduct(Producto producto) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, producto.getId());
			preparedStatement.setString(2, producto.getNombre());
			preparedStatement.setDouble(3, producto.getValor());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return producto;
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
	public Producto updateProduct(Producto producto) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			String query = "UPDATE FROM Producto SET (nombre = ?, valor = ?) WHERE idProducto = ?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, producto.getNombre());
			preparedStatement.setDouble(2, producto.getValor());
			preparedStatement.setInt(3, producto.getId());
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				conn.commit();
				return producto;
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
	public Producto getProductoMayorRecaudacion() throws SQLException {
		Connection conn = MySqlDao.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS recaudacion ");
			sb.append("FROM Producto p ");
			sb.append("INNER JOIN Factura_Producto fp ON p.idProducto = fp.idProducto ");
			sb.append("GROUP BY p.idProducto ORDER BY recaudacion DESC ");
			PreparedStatement preparedStatement = conn.prepareStatement(sb.toString());
			ResultSet result = preparedStatement.executeQuery(sb.toString());
			Producto producto = null;
			while (result.next()) {
				producto = new Producto(result.getInt("idProducto"), result.getString("nombre"),
						result.getFloat("valor"));
			}
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param producto
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private Producto createProduct(Producto producto, Connection conn) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, producto.getId());
			preparedStatement.setString(2, producto.getNombre());
			preparedStatement.setDouble(3, producto.getValor());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return producto;
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
