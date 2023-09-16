package model;


public class Producto {
    private int id;
    private String nombre;
    private float valor;

    /**
     * 
     * @param id
     * @param nombre
     * @param valor
     */
    public Producto(int id, String nombre, float valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}



	/**
	 * @param valor the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}


    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", valor=" + valor + "]";
    }
}
