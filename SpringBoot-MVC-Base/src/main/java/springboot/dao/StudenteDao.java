package springboot.dao;

import java.util.Collection;

import springboot.model.Studente;

public interface StudenteDao {

	Collection<Studente> getAllStudenti();

	Studente getStudenteById(int id);

	void removeStudenteById(int id);

	void updateStudente(Studente studente);

	void insertStudente(Studente studente);

}