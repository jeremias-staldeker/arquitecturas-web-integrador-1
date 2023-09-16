package dao;

import java.sql.SQLException;

import enums.DataBase.TypesDB;

public abstract class DaoFactory {

	public static DaoFactory getDaoFactory(TypesDB database) {
		switch (database) {
		case MYSQL:
			return new MySqlDao();
		default:
			return null;
		}
	}

	public abstract void closeConnection() throws SQLException ;

	public abstract ProductoDao getProductoDao();

	public abstract FacturaDao getFacturaDao();

	public abstract ClienteDao getClienteDao();

	public abstract ProductoFacturaDao getProductoFacturaDao();

}
