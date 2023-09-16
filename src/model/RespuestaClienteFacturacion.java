package model;

public class RespuestaClienteFacturacion extends Cliente {

	private float recaudacion;

	public RespuestaClienteFacturacion(int id, String nombre, String email, Float recaudacion) {
		super(id, nombre, email);
		this.recaudacion = recaudacion;
	}

	/**
	 * 
	 * @return
	 */
	public float getRecaudacion() {
		return recaudacion;
	}

	/**
	 * 
	 * @param recaudacion
	 */
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

}
