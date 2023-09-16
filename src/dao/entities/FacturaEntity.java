package dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Factura")
@NamedQuery(name = "FacturaEntity.findAll", query = "SELECT f FROM FacturaEntity f")
public class FacturaEntity {

	@Id
	@Column(name = "idFactura")
	private int idFactura;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private ClienteEntity cliente;

	public FacturaEntity() {
		super();
	}

	/**
	 * @return the idFactura
	 */
	public int getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the cliente
	 */
	public ClienteEntity getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
}
