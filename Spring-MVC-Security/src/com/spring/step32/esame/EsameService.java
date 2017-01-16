package com.spring.step32.esame;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.step32.model.Esame;


@Service
public class EsameService {
	
	private static List<Esame> esami = new ArrayList<Esame>();
    private static int esameCount = 3;
    
    static {
    	esami.add(new Esame(1, "Learn Spring MVC", new Date(), false, "admin"));
    	esami.add(new Esame(2, "Learn Struts", new Date(), false, "admin"));
    	esami.add(new Esame(3, "Learn Hibernate", new Date(), false, "admin"));
    }
    
    
    public Esame cercaEsame(int id) {
        for (Esame esame : esami) {
            if (esame.getId() == id)
            	return esame;
        }
        return null;
    }
    
    
    public List<Esame> cercaEsami(String studente) {
        List<Esame> filteredEsami = new ArrayList<Esame>();
        for (Esame esame : esami) {
            if (esame.getStudente().equals(studente))
            	filteredEsami.add(esame);
        }
        return filteredEsami;
    }
    
    
    public void aggiungiEsame(String nome, String desc, Date data, boolean superato) {
        esami.add(new Esame(++esameCount, desc, data, superato, "admin"));
    }
    
    
    public void aggiornaEsame(Esame esame) {
        esami.remove(esame);
        esami.add(esame);
    }
    
    
    public void rimuoviEsame(int id) {
        Iterator<Esame> iterator = esami.iterator();
        while (iterator.hasNext()) {
        	Esame esame = iterator.next();
            if (esame.getId() == id) {
                iterator.remove();
            }
        }
    }

}
