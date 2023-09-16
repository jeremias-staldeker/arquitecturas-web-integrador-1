package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Factura;

public interface IFacturaDao {

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
	public Factura getFactura(int id) throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Factura> getFactura() throws SQLException;

	/**
	 * 
	 * @param factura
	 * @return
	 * @throws SQLException
	 */
	public Factura createFactura(Factura factura) throws SQLException;

	/**
	 * 
	 * @param facturasACrear
	 * @return
	 * @throws SQLException
	 */
	public List<Factura> createFacturas(List<Factura> facturasACrear) throws SQLException;
}
