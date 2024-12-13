/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.data;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lupo
 */
public class ContattoTest {
    
    private Contatto c;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");

        c = new Contatto("Mario", "Rossi", numeri, email, false);
    }
    
    @AfterEach
    public void tearDown() {
    }
   
    /**
     * Test of getNome method, of class Contatto.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        
        String expResult = "Mario";
        String result = c.getNome();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCognome method, of class Contatto.
     */
    @Test
    public void testGetCognome() {
        System.out.println("getCognome");
        
        String expResult = "Rossi";
        String result = c.getCognome();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumeri method, of class Contatto.
     */
    @Test
    public void testGetNumeri() {
        System.out.println("getNumeri");
        
        Set<String> expResult = new HashSet<>();
        expResult.add("3331234567");
        expResult.add("0817654321");
        expResult.add("4569868424");
        
        Set<String> result = c.getNumeri();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Contatto.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        
        Set<String> expResult = new HashSet<>();
        expResult.add("mario.rossi@example.com");
        expResult.add("rossimario@gmail.com");
        expResult.add("tonyeffe@unisa.it");
        
        Set<String> result = c.getEmail();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isPreferito method, of class Contatto.
     */
    @Test
    public void testIsPreferito() {
        System.out.println("isPreferito");

        boolean expResult = false;
        boolean result = c.isPreferito();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Contatto.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Object obj = null;
        boolean expResult = false;
        boolean result = c.equals(obj);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Contatto.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Contatto o = new Contatto("Luigi", "Verdi", null, null, false);

        assertTrue(c.compareTo(o) < 0);
        assertTrue(o.compareTo(c) > 0);
    }

    /**
     * Test of contattoValido method, of class Contatto.
     */
    @Test
    public void testContattoValido() {
        System.out.println("contattoValido");
        
        boolean expResult = true;
        boolean result = c.contattoValido();
        assertEquals(expResult, result);
    }
    
}
