package springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import springboot.model.Studente;

@Repository
@Qualifier("fake_db")
public class FakeStudenteDaoImpl implements StudenteDao {
	
	// fake DB
	private static Map<Integer, Studente> studenti;
	
	// inizializzo il DB
	static {
		studenti = new HashMap<Integer, Studente>() {
			{
				put(1, new Studente(1, "Dario", "D'Agostino", "Spring Boot"));
				put(2, new Studente(2, "Mario", "Rossi", "Linux"));
				put(3, new Studente(3, "Paolo", "Verdi", "Rete"));
			}
		};
	}
	
	
	/* (non-Javadoc)
	 * @see springboot.dao.StudenteDao#getAllStudenti()
	 */
	@Override
	public Collection<Studente> getAllStudenti() {
		return this.studenti.values();
	}
	
	/* (non-Javadoc)
	 * @see springboot.dao.StudenteDao#getStudenteById(int)
	 */
	@Override
	public Studente getStudenteById(int id) {
		return this.studenti.get(id);
	}

	/* (non-Javadoc)
	 * @see springboot.dao.StudenteDao#removeStudenteById(int)
	 */
	@Override
	public void removeStudenteById(int id) {
		this.studenti.remove(id);		
	}
	
	/* (non-Javadoc)
	 * @see springboot.dao.StudenteDao#updateStudente(springboot.model.Studente)
	 */
	@Override
	public void updateStudente(Studente studente) {
		Studente s = this.studenti.get(studente.getId());
		s.setNome(studente.getNome());
		s.setCognome(studente.getCognome());
		s.setEsame(studente.getEsame());
		this.studenti.put(studente.getId(), s);
	}

	/* (non-Javadoc)
	 * @see springboot.dao.StudenteDao#insertStudente(springboot.model.Studente)
	 */
	@Override
	public void insertStudente(Studente studente) {
		this.studenti.put(studente.getId(), studente);
	}

}
