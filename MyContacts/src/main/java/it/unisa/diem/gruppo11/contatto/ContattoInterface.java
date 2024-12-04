/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.contatto;

/**
 *
 * @author lupo
 */
public interface ContattoInterface {
    
    public void modificaContatto(Contatto c);
    
    public void switchPreferiti(Contatto c);
    
    public boolean contattoValido(Contatto c);
    
}
