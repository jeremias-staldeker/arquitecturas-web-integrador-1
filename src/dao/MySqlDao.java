package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDao extends DaoFactory {

	private final static String Driver = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://app.facu-sanchez.com:3306/u619253451_tudai";
	private final static String USER = "u619253451_tudai";
	private final static String PASSWORD = "Tudai2023";
	private static MySqlDao instance;
	private static Connection connection;

	@Override
	public ProductoDao getProductoDao() {
		return new ProductoDao();
	}

	@Override
	public FacturaDao getFacturaDao() {
		return new FacturaDao();
	}

	@Override
	public ClienteDao getClienteDao() {
		return new ClienteDao();
	}

	@Override
	public ProductoFacturaDao getProductoFacturaDao() {
		return new ProductoFacturaDao();
	}

	private static Connection createConnection() {
		try {
			if (connection != null) {
				return connection;
			}

			Class.forName(Driver).getDeclaredConstructor().newInstance();
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al crear una nueva conexion.");
		}
	}

//	@Override()
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			Connection conn = createConnection();
			connection = conn;
			return conn;
		}
		return connection;
	}

	public static DaoFactory getInstance() throws SQLException {
		if (instance == null) {
			instance = new MySqlDao();
		}
		return instance;
	}

	public static void commit(Connection connection) throws SQLException {
		try {
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al confirmar la transaccion");
		}
	}

	public static void rollback(Connection connection) throws SQLException {
		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al deshacer la transaccion");
		}
	}

	@Override()
	public void closeConnection() throws SQLException {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al cerrar la conexion.");
		}
	}
}
