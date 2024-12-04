/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.rubrica;

import it.unisa.diem.gruppo11.contatto.Contatto;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author lupo
 */
public interface OperazioniRubricaInterface {

        /**
         * Ricerca i contatti nella rubrica il cui nome o cognome inizia con il testo fornito.
         *
         * @param text Il testo da confrontare con i nomi o cognomi dei contatti.
         * @return Un insieme di contatti che corrispondono al criterio di ricerca.
         */
        public Set<Contatto> ricercaContatti(String text);

        /**
         * Aggiunge un nuovo contatto alla rubrica.
         *
         * @param c Il contatto da aggiungere.
         * @return true se il contatto è stato aggiunto con successo, false altrimenti.
         */
        public boolean aggiungiContatto(Contatto c);

        /**
         * Rimuove un contatto dalla rubrica.
         *
         * @param c Il contatto da rimuovere.
         * @return true se il contatto è stato rimosso con successo, false altrimenti.
         */
        public boolean rimuoviContatto(Contatto c);

        /**
         * Resetta completamente la rubrica, eliminando tutti i contatti e i preferiti.
         */
        public void resetTotale();

        /**
         * Salva lo stato corrente della rubrica in un file.
         *
         * @param nomefile Il nome del file in cui salvare la rubrica.
         * @throws IOException Se si verifica un errore durante il salvataggio del file.
         */
        public void esportaRubrica(String nomefile) throws IOException;

        /**
         * Legge lo stato della rubrica da un file.
         *
         * @param nomefile Il nome del file da cui leggere la rubrica.
         * @throws IOException Se si verifica un errore durante la lettura del file.
         */
        public static void importaRubrica(String nomefile) throws IOException {
            throw new UnsupportedOperationException("Metodo statico da implementare nella classe concreta.");
        }
  
}
