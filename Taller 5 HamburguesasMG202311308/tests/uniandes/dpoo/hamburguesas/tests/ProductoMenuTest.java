package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu producto1;
	
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		producto1 = new ProductoMenu( "hamburguesa", 18000 );
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
    void testConstructor() {
        String nombre = "hamburguesa";
        int precioBase = 18000;
        ProductoMenu producto1 = new ProductoMenu(nombre, precioBase);

        // Verificar que el nombre se haya asignado correctamente
        assertEquals(nombre, producto1.getNombre());

        // Verificar que el precioBase se haya asignado correctamente
        assertEquals(precioBase, producto1.getPrecio());
    }
	
	
	@Test
    void testGetNombre( )
    {
        assertEquals( "hamburguesa", p