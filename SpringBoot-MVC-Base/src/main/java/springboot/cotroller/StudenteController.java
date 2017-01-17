package springboot.cotroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.model.Studente;
import springboot.service.StudenteService;

@RestController
@RequestMapping("/studenti")
public class StudenteController {
	
	@Autowired
	private StudenteService studenteService;
	
	/*
	 * non specificando value="..." questo metodo mapperà la url /studenti
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Studente> getAllStudenti() {
		return studenteService.getAllStudenti();
	}
	
	/*
	 * mappa la url "/studenti/{id}"
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Studente getStudenteById(@PathVariable("id") int id) {
		return studenteService.getStudenteById(id);
	}
	
	
	/*
	 * mappa una richiesta REST di tipo DELETE con url "/studenti/{id}"
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStudenteById(@PathVariable("id") int id) {
		studenteService.removeStudenteById(id);
	}
	
	
	/*
	 * mappa una richiesta REST di tipo PUT con url "/studenti"
	 * 
	 * i dati dello studente sono all'interno del body della richiesta,
	 * e questi devono essere formattati in JSON nella forma:
	 * 		{
	 * 			"id": <id_esistente>,
	 * 			"nome": "Pippo",
	 * 			"cognome": "Pluto",
	 * 			"esame": "Paperino"
	 * 		}
	 * se esiste uno studente con quell'id, verrà fatto l'aggiornamento,
	 * altrimenti andrà in errore
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void updateStudente(@RequestBody Studente studente) {
		this.studenteService.updateStudente(studente);
	}
	
	
	/*
	 * mappa una richiesta REST di tipo POST con url "/studenti"
	 * 
	 * i dati dello studente sono all'interno del body della richiesta,
	 * e questi devono essere formattati in JSON nella forma:
	 * 		{
	 * 			"id": <id>,
	 * 			"nome": "Pippo",
	 * 			"cognome": "Pluto",
	 * 			"esame": "Paperino"
	 * 		}
	 * se esiste già uno studente con quell'id, verrà fatto un aggiornamento,
	 * altrimenti un inserimento
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void insertStudente(@RequestBody Studente studente) {
		this.studenteService.insertStudente(studente);
	}
}
