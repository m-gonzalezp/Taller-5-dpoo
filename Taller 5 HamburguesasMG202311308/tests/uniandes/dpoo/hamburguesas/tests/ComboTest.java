package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

	private Combo combo1;
    private ArrayList<ProductoMenu> items;
 
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 18000));
        items.add(new ProductoMenu("Papas", 7000));
        
        // Inicializar combo con descuento del 10%
        combo1 = new Combo("Combo Básico", 10.0, items);
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	
	
	@Test
    void testConstructor() {
        String nombre = "Combo Especial";
        double descuento = 15.0;
        
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 18000));
        items.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo(nombre, descuento, items);
        assertEquals(nombre, combo.getNombre());
        assertEquals(descuento, combo.getDescuento());
        assertEquals(items.size(), combo.getItems().size());
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i).getNombre(), combo.getItems().get(i).getNombre());
        }
    }
	
	
	@Test
    void testGetNombre( )
    {
        assertEquals( "Combo Básico", combo1.getNombre( ), "El nombre del combo no es el espe