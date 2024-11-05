package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoAjustadoTest {

	private ProductoMenu productobase;
	private ProductoAjustado producto1;
	
	Ingrediente queso = new Ingrediente("Queso", 2000);
    Ingrediente tocino = new Ingrediente("Tocino", 5000);
    Ingrediente tomate = new Ingrediente("Tomate", 500);
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		productobase = new ProductoMenu( "hamburguesa", 18000 );
		producto1 = new ProductoAjustado(productobase);
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
    void testConstructor() {
        ProductoMenu productoBase = new ProductoMenu("hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        assertEquals(productoBase, productoAjustado.getProductoBase());
        
        // Verificar que las listas de agregados y eliminados están vacías
        assertNotNull(productoAjustado.getAgregados());
        assertTrue(productoAjustado.getAgregados().isEmpty());
        
        assertNotNull(productoAjustado.getEliminados());
        assertTrue(productoAjustado.getEliminados().isEmpty());
    }
	
	
	
	@Test
    void testGetNombre( )
    {
        assertEquals( "hamburguesa", productobase.getNombre( ), "El nombre del ingrediente no es el esperado." );
    }
	
	@Test
    void testGetPrecio( )
    {
        assertEquals( 18000, producto1.getPrecio( ), "El precio del producto ajustado no es el esperado." );
    }
	
	@Test
    void testGetPrecio2( )
    {
		producto1.agregar_ingrediente(queso);
        producto1.agregar_ingrediente(tocino);
        assertEquals( 25000, producto1.getPrecio( ), "El precio del producto ajustado no es el esperado." );
    }
	
	
	@Test
	void testAgregarIngrediente() {

        // Agregar ingredientes al producto ajustado
        producto1.agregar_ingrediente(queso);
        producto1.agregar_ingrediente(tocino);

        // Verificar que el precio total se actualiza correctamente
        int expectedPrecio = 18000 + 2000 + 5000;
        assertEquals(expectedPrecio,         producto1.getPrecio(), "El precio del producto ajustado es erroneo");
	}
	
	@Test
	void testEliminarIngrediente() {
        
        producto1.agregar_ingrediente(tocino);
        producto1.agregar_ingrediente(queso);
        producto1.eliminar_ingrediente(tomate);
        
        
        int expectedPrecio = 18000 + 2000 + 5000; 
        assertEquals(expectedPrecio, producto1.getPrecio(), "El precio del producto ajustado es erroneo");
	}
	
	@Test
    void testGenerarTextoFactura() {
		producto1.agregar_ingrediente(queso);
		producto1.agregar_ingrediente(tocino);
		

        // Texto de factura esperado
        String expectedTextoFactura = """
            hamburguesa
                        18000
                +Queso     