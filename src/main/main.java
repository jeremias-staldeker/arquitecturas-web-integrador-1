package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.FacturaDao;
import dao.ProductoDao;
import dao.ProductoFacturaDao;
import enums.DataBase.TypesDB;
import model.Cliente;
import model.Factura;
import model.Producto;
import model.ProductoFactura;
import model.RespuestaClienteFacturacion;
import services.CSVReaderServiceImpl;

public class main {

	public static void main(String[] args) throws SQLException {
		DaoFactory dao = DaoFactory.getDaoFactory(TypesDB.MYSQL);
		ClienteDao clienteDao = dao.getClienteDao();
		FacturaDao facturaDao = dao.getFacturaDao();
		ProductoDao productoDao = dao.getProductoDao();
		ProductoFacturaDao productoFacturaDao = dao.getProductoFacturaDao();
		CSVReaderServiceImpl csvReader = new CSVReaderServiceImpl();

		try {
			// 1) Crear las tablas en base de datos
			clienteDao.createTable();
			productoDao.createTable();
			facturaDao.createTable();
			productoFacturaDao.createTable();
			System.out.println("Tablas creadas correctamente.");

			// 2) Rutas a los archivos CSV
			String clientesCSVPath = "resources\\clientes.csv";
			String facturasCSVPath = "resources\\facturas.csv";
			String productosCSVPath = "resources\\productos.csv";
			String productoFacturasCSVPath = "resources\\facturas-productos.csv";

			// 2) Cargar los datos de los archivos CSV
			List<Cliente> clientes = csvReader.readClientsFromCSV(clientesCSVPath);
			List<Producto> productos = csvReader.readProductsFromCSV(productosCSVPath);
			List<Factura> facturas = csvReader.readFactureFromCSV(facturasCSVPath);
			List<ProductoFactura> productoFacturas = csvReader.readProductFactureFromCSV(productoFacturasCSVPath);

			// 2) Guardar los datos en la base de datos
			System.out.println("Inicio de carga de datos.");
			clienteDao.createClientes(clientes);
			productoDao.createProducts(productos);
			facturaDao.createFacturas(facturas);
			productoFacturaDao.createProductoFacturas(productoFacturas);
			System.out.println("Datos cargados en la base de datos correctamente.");

			// 4) Obtner el producto que mas recaudó.
			Producto producto = productoDao.getProductoMayorRecaudacion();
			if (producto != null) {
				System.out
						.println("Producto con mayor recaudación: " + producto.getId() + " - " + producto.getNombre());
			}

			System.out.println("----------------------------------");

			// 5) Listado de clientes ordenados segun la recaudación
			List<RespuestaClienteFacturacion> clienteFacturacion = clienteDao.getClientesPorFacturacion();
			for (RespuestaClienteFacturacion cliente : clienteFacturacion) {
				System.out.println("Cliente: " + cliente.getNombre());
				System.out.println("Email: " + cliente.getEmail());
				System.out.println("Recaudación: " + cliente.getRecaudacion());
				System.out.println("----------------------------------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
