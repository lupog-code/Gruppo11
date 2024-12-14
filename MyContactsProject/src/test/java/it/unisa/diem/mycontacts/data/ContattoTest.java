/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.data;

import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
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
    
    private Contatto c1, c2, c3, c4, c5;

    @BeforeEach
    public void setUp() throws InvalidContactException {
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");

        c1 = new Contatto("Mario", "Rossi", numeri, email, false);
        
        c2 = new Contatto("Mario", "Rossi", null, null, true);
        
        c3 = new Contatto("Mario", "", null, email, true);
        
        c4 = new Contatto("", "Rossi", null, null, true);
    }
   
    @Test
    public void testContatto1() throws InvalidContactException {
        // controllo che c1 sia stato creato correttamente
        assertNotNull(c1);
    }
    
    @Test
    public void testContatto2() throws InvalidContactException {
        //
        assertNotNull(c2);
    }
    
    @Test
    public void testContatto3() throws InvalidContactException {

        assertNotNull(c3);
    }
    
    @Test
    public void testContatto4() throws InvalidContactException {

        assertNotNull(c4);
    }
    
    @Test
    public void testContatto5() throws InvalidContactException {
        
        assertThrows(InvalidContactException.class, () -> {
            new Contatto("", "", null, null, true);});
    }
    
    /**
     * Test of getNome method, of class Contatto.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        
        String expResult = "Mario";
        String result = c1.getNome();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCognome method, of class Contatto.
     */
    @Test
    public void testGetCognome() {
        System.out.println("getCognome");
        
        String expResult = "Rossi";
        String result = c1.getCognome();
        
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
        
        Set<String> result = c1.getNumeri();
        
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
        
        Set<String> result = c1.getEmail();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isPreferito method, of class Contatto.
     */
    @Test
    public void testIsPreferito() {
        System.out.println("isPreferito");

        boolean expResult = false;
        boolean result = c1.isPreferito();
        
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
        boolean result = c1.equals(obj);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Contatto.
     */
    @Test
    public void testCompareTo() throws InvalidContactException {
        System.out.println("compareTo");
        Contatto o = new Contatto("Luigi", "Verdi", null, null, false);

        assertTrue(c1.compareTo(o) < 0);
        assertTrue(o.compareTo(c1) > 0);
    }
    
}
