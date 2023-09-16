package mapper;

import dao.entities.ClienteEntity;
import model.Cliente;

public class ClienteMapper {

	public ClienteMapper() {
		super();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static Cliente mapCliente(ClienteEntity entity) {
		if (entity != null) {
			Cliente cliente = new Cliente(entity.getIdCliente(), entity.getNombre(), entity.getEmail());
			return cliente;
		}
		return null;
	}

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public static ClienteEntity mapClienteEntity(Cliente modelo) {
		if (modelo != null) {
			ClienteEntity entity = new ClienteEntity();
			entity.setIdCliente(modelo.getId());
			entity.setEmail(modelo.getEmail());
			entity.setNombre(modelo.getNombre());
			return entity;
		}
		return null;
	}
}
