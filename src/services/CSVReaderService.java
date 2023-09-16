package services;

import java.io.IOException;
import java.util.List;

import model.Cliente;
import model.Factura;
import model.Producto;
import model.ProductoFactura;

public interface CSVReaderService {
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
    public List<Cliente> readClientsFromCSV(String filePath) throws IOException;
    
    /**
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public List<Factura> readFactureFromCSV(String filePath) throws IOException;
    
    /**
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public List<Producto> readProductsFromCSV(String filePath) throws IOException;
    
    /**
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public List<ProductoFactura> readProductFactureFromCSV(String filePath) throws IOException;
}