package mapper;

import dao.entities.FacturaProductoEntity;
import model.ProductoFactura;

public class FacturaProductoMapper {

	public FacturaProductoMapper() {
		super();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static ProductoFactura mapProductoFactura(FacturaProductoEntity entity) {
		if (entity != null) {
//			ProductoFactura productoFactura = new ProductoFactura();
//			productoFactura.setCantidad(entity.getCantidad());
//			productoFactura.setFactura(FacturaMapper.mapFactura(entity.getFactura()));
//			productoFactura.setProducto(ProductoMapper.mapProducto(entity.getProducto()));
//			return productoFactura;
			return null;
		}
		return null;
	}

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public static FacturaProductoEntity mapProductoFacturaEntity(ProductoFactura modelo) {
		if (modelo != null) {
			FacturaProductoEntity entity = new FacturaProductoEntity();
			entity.setCantidad(modelo.getCantidad());
			entity.setFactura(FacturaMapper.mapFacturaEntity(modelo.getFactura()));
			entity.setProducto(ProductoMapper.mapProductoEntity(modelo.getProducto()));
			return entity;
		}
		return null;
	}
}
