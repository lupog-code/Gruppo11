/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.rubrica;

import it.unisa.diem.gruppo11.contatto.Contatto;
import java.util.Set;

/**
 *
 * @author lupo
 */
public interface RubricaInterface {

    boolean addContatto(Contatto c);

    boolean removeContatto(Contatto c);

    Set<Contatto> getElenco();

    void resetRubrica();

    boolean isRubricaVuota();
}
