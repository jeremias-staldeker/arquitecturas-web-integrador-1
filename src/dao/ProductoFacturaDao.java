package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IProductoFacturaDao;
import model.ProductoFactura;

public class ProductoFacturaDao implements IProductoFacturaDao {

	@Override
	public void createTable() throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = MySqlDao.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE IF NOT EXISTS Factura_Producto(idFactura INT, idProducto INT, cantidad INT, FOREIGN KEY (idProducto) REFERENCES Producto (idProducto), FOREIGN KEY (idFactura) REFERENCES Factura (idFactura));");
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

	@Override
	public List<ProductoFactura> createProductoFacturas(List<ProductoFactura> productosFacturaACrear)
			throws SQLException {
		Connection conn = MySqlDao.getConnection();
		List<ProductoFactura> productoFacturas = new ArrayList<ProductoFactura>();
		try {
			for (ProductoFactura productoFactura : productosFacturaACrear) {
				ProductoFactura newProductoFactura = createProductoFactura(productoFactura, conn);
				if (newProductoFactura == null) {
					conn.rollback();
					System.out.println("Error al cargar factura producto " + newProductoFactura);
					return new ArrayList<>(0);
				}
				productoFacturas.add(newProductoFactura);
			}
			conn.commit();
			return productoFacturas;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return new ArrayList<>(0);
		}
	}

	/**
	 * 
	 * @param factura
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private ProductoFactura createProductoFactura(ProductoFactura productoFactura, Connection conn)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, productoFactura.getFactura().getId());
			preparedStatement.setInt(2, productoFactura.getProducto().getId());
			preparedStatement.setInt(3, productoFactura.getCantidad());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return productoFactura;
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
