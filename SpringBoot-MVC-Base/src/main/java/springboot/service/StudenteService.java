package springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.StudenteDao;
import springboot.model.Studente;

@Service
public class StudenteService {
	
	@Autowired
	/*
	 * Dato che studenteDao è una Interface e dato che ho due implementazioni,
	 * devo dire a Spring quale delle due usare, e lo faccio con l'annotation @Qualifier
	 */
	@Qualifier("mysql_db")
	private StudenteDao studenteDao;
	
	public Collection<Studente> getAllStudenti() {
		return this.studenteDao.getAllStudenti();
	}
	
	public Studente getStudenteById(int id) {
		return this.studenteDao.getStudenteById(id);
	}

	public void removeStudenteById(int id) {
		this.studenteDao.removeStudenteById(id);		
	}
	
	public void updateStudente(Studente studente) {
		this.studenteDao.updateStudente(studente);
	}

	public void insertStudente(Studente studente) {
		this.studenteDao.insertStudente(studente);		
	}

}
