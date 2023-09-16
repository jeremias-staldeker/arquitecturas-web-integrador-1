package dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Factura_Producto")
@NamedQuery(name = "FacturaProductoEntity.findAll", query = "SELECT fp FROM FacturaProductoEntity fp")
public class FacturaProductoEntity {

	@ManyToOne
	@JoinColumn(name = "idFactura")
	private FacturaEntity factura;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private ProductoEntity producto;

	@Column(name = "cantidad")
	private int cantidad;

	public FacturaProductoEntity() {
		super();
	}

	/**
	 * @return the factura
	 */
	public FacturaEntity getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	/**
	 * @return the producto
	 */
	public ProductoEntity getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
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
}
