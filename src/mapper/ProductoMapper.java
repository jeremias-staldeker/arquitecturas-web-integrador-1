package mapper;

import dao.entities.ProductoEntity;
import model.Producto;

public class ProductoMapper {

	public ProductoMapper() {
		super();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static Producto mapProducto(ProductoEntity entity) {
		if (entity != null) {
			Producto producto = new Producto(entity.getIdProducto(), entity.getNombre(), entity.getValor());
			return producto;
		}
		return null;
	}

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public static ProductoEntity mapProductoEntity(Producto modelo) {
		if (modelo != null) {
			ProductoEntity entity = new ProductoEntity();
			entity.setIdProducto(modelo.getId());
			entity.setNombre(modelo.getNombre());
			entity.setValor(modelo.getValor());
			return entity;
		}
		return null;
	}
}
