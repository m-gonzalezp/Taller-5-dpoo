package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private Pedido pedido;
	
	ProductoMenu hamburguesa = new ProductoMenu( "hamburguesa", 18000 );
	ProductoMenu papas = new ProductoMenu( "papas", 7000 );
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		pedido = new Pedido( "Mariana Gonzalez", "Carrera 1 #18a-12" );
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	
	@Test
    void testGetIdPedido() {
        int idEsperado = pedido.getIdPedido();
        Pedido otroPedido = new Pedido("Martin Sanz", "Carrera 1 #18a-12");
        assertEquals(idEsperado + 1, otroPedido.getIdPedido(), "El id del pedido debería incrementarse con cada instancia");
    }
	
	
	@Test
    void testGetNombreCliente( )
    {
        assertEquals( "Mariana Gonzalez", pedido.getNombreCliente( ), "El nombre del cliente no es el esperado." );
    }
	
	@Test
    void testAgregarProducto( )
    {
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
		
		
		int precioEsperado = (int) ((18000+7000)+ (18000 + 7000) * 0.19); 
        assertEquals(precioEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no es el esperado después de agregar los productos.");

    }
	
	@Test
    void testGetPrecioNetoPedido( )
    {
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
        assertEquals( 25000, pedido.getPrecioNetoPedido( ), "El precio neto del pedido no es el esperado." );
    }
	
	@Test
    void testGetPrecioTotalPedido( )
    {
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
        assertEquals( 29750, pedido.getPrecioTotalPedido( ), "El precio neto del pedido no es el esperado." );
    }
	
	@Test
    void testGetPrecioIvaPedido( )
    {
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
        assertEquals( 4750, pedido.getPrecioIVAPedido( ), "El IVA del pedido no es el esperado." );
    }
	
	@Test
    public void testGenerarTextoFactura() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        String expected = "Cliente: Mariana Gonzalez\n" +
                          "Dirección: Carrera 1 #18a-12\n" +
                          "----------------\n" +
                          "hamburguesa\n" +
                          "            18000\n" +
                          "papas\n" +
                          "            7000\n" +
                          "----------------\n" +
                          "Precio Neto:  25000\n" +
                          "IVA:          4750\n" +  
                          "Precio Total: 29750\n";

        assertEquals(expected, pedido.generarTextoFactura());
    }
	

    @Test
    public void testGuardarFactura() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);
        File archivo = new File("factura.txt");

        try {
            pedido.guardarFactura(archivo);
            // Verifica que el archivo fue creado y contiene el contenido esperado
            assertTrue(archivo.exists());

            // Leer el contenido del archivo para verificar que sea correcto
            Scanner scanner = new Scanner(archivo);
            StringBuilder contenido = new StringBuilder();
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            String expected = 	"Cliente: Mariana Gonzalez\n" +
			                    "Dirección: Carrera 1 #18a-12\n" +
			                    "----------------\n" +
			                    "hamburguesa\n" +
			                    "            18000\n" +
			                    "papas\n" +
			                    "            7000\n" +
			                    "----------------\n" +
			                    "Precio Neto:  25000\n" +
			                    "IVA:          4750\n" +  
			                    "Precio Total: 29750";

            assertEquals(expected, contenido.toString().trim());
        } catch (FileNotFoundException e) {
            fail("No se pudo guardar la factura: " + e.getMessage());
        } finally {
            archivo.delete();
        }
    }

