package model;

public class ProductoFactura {
	private int cantidad;
	private Producto producto;
	private Factura factura;

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param valor
	 */
	public ProductoFactura(int cantidad, Producto producto, Factura factura) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.factura = factura;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}