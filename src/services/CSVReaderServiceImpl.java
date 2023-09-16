package services;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.Cliente;
import model.Factura;
import model.Producto;
import model.ProductoFactura;

public class CSVReaderServiceImpl implements CSVReaderService {

	@Override
	public List<Cliente> readClientsFromCSV(String filePath) throws IOException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		FileReader fileReader = new FileReader(filePath);

		try (CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
			for (CSVRecord record : csvParser) {
				int id = Integer.parseInt(record.get("idCliente"));
				String nombre = record.get("nombre");
				String email = record.get("email");

				// Crea un objeto Cliente y agrégalo a la lista
				Cliente cliente = new Cliente(id, nombre, email);
				clientes.add(cliente);
			}
		}
		return clientes;
	}

	@Override
	public List<Factura> readFactureFromCSV(String filePath) throws IOException {
		List<Factura> invoices = new ArrayList<Factura>();
		FileReader fileReader = new FileReader(filePath);

		try (CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
			for (CSVRecord record : csvParser) {
				int idFactura = Integer.parseInt(record.get("idFactura"));
				int idCliente = Integer.parseInt(record.get("idCliente"));

				Factura factura = new Factura(idFactura, idCliente);
				invoices.add(factura);
			}
		}
		return invoices;
	}

	@Override
	public List<Producto> readProductsFromCSV(String filePath) throws IOException {
		List<Producto> productos = new ArrayList<Producto>();
		FileReader fileReader = new FileReader(filePath);

		try (CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
			for (CSVRecord record : csvParser) {
				int id = Integer.parseInt(record.get("idProducto"));
				String nombre = record.get("nombre");
				float valor = Float.parseFloat(record.get("valor"));

				Producto producto = new Producto(id, nombre, valor);
				productos.add(producto);
			}
		}
		return productos;
	}

	@Override
	public List<ProductoFactura> readProductFactureFromCSV(String filePath) throws IOException {
		List<ProductoFactura> productoFacturas = new ArrayList<>();
		FileReader fileReader = new FileReader(filePath);

		try (CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
			for (CSVRecord record : csvParser) {
				int idFactura = Integer.parseInt(record.get("idFactura"));
				int idProducto = Integer.parseInt(record.get("idProducto"));
				int cantidad = Integer.parseInt(record.get("cantidad"));

				// Crea un objeto Producto y Factura, luego crea un ProductoFactura con ellos
				Producto producto = new Producto(idProducto, null, 0);
				Factura factura = new Factura(idFactura, 0);
				ProductoFactura productoFactura = new ProductoFactura(cantidad, producto, factura);
				productoFacturas.add(productoFactura);
			}
		}
		return productoFacturas;
	}

}
