package mapper;

import dao.entities.ClienteEntity;
import dao.entities.FacturaEntity;
import model.Cliente;
import model.Factura;

public class FacturaMapper {

	public FacturaMapper() {
		super();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static Factura mapFactura(FacturaEntity entity) {
		if (entity != null) {
			Factura factura = new Factura(entity.getIdFactura(), entity.getCliente().getIdCliente());
			return factura;
		}
		return null;
	}

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public static FacturaEntity mapFacturaEntity(Factura modelo) {
		if (modelo != null) {
			FacturaEntity entity = new FacturaEntity();
			entity.setIdFactura(modelo.getId());
			ClienteEntity clienteEntity = ClienteMapper
					.mapClienteEntity(new Cliente(modelo.getIdCliente(), null, null));
			entity.setCliente(clienteEntity);
			return entity;
		}
		return null;
	}
}
