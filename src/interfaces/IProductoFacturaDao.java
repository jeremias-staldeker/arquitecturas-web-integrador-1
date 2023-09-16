package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.ProductoFactura;

public interface IProductoFacturaDao {

	/**
	 * 
	 * @throws SQLException
	 */
	public void createTable() throws SQLException;

	/**
	 * 
	 * @param productosFacturaACrear
	 * @return
	 * @throws SQLException
	 */
	public List<ProductoFactura> createProductoFacturas(List<ProductoFactura> productosFacturaACrear)
			throws SQLException;

}
