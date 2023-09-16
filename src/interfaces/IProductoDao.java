package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Producto;

public interface IProductoDao {

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
	public Producto getProducto(int id) throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Producto> getProductos() throws SQLException;

	/**
	 * 
	 * @param producto
	 * @return
	 * @throws SQLException
	 */
	public Producto createProduct(Producto producto) throws SQLException;

	/**
	 * 
	 * @param productosACrear
	 * @return
	 * @throws SQLException
	 */
	public List<Producto> createProducts(List<Producto> productosACrear) throws SQLException;

	/**
	 * 
	 * @param producto
	 * @return
	 * @throws SQLException
	 */
	public Producto updateProduct(Producto producto) throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Producto getProductoMayorRecaudacion() throws SQLException;
}
