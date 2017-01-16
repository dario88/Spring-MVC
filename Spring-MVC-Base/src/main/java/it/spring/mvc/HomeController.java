package it.spring.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.spring.mvc.utils.MyException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Value("${remoteServer.URL}")
	private String remoteServerURL;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		model.addAttribute("remoteServerUrl", remoteServerURL);
		
		return "home";
	}
	
	
	/*
	 * URL dinamica con placeholder,
	 * da utilizzare al posto dei classici parametri GET
	 * 
	 * Quindi, invocando la url "/10" verrà richiamato questo metodo,
	 * avendo come parametro "id = 10"
	 * 
	 *
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void homeGetId(@PathVariable int id) {
		
		System.out.println("Requested id " + id);
	}
	*/
	
	
	/*
	 * Altro metodo equivalente al precedente:
	 *
	 *
	@RequestMapping(value = "/{idHome}", method = RequestMethod.GET)
	public void home(@PathVariable("idHome") int idHome) {
		
		System.out.println("Requested code " + idHome);
	}
	*/
	
	
	/*
	 * Per recuperare i valori dei parametri inviati alla pagina (querystring)
	 * possiamo utilizzare l'annotation @RequestParam
	 * 
	 * In questo caso invocando l'URL /?id=10 otterremo lo stesso effetto
	 * 
	 * 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(@RequestParam("id") int id) {
		
		System.out.println("Requested id from querystring" + id);
	}
	*/
	
	
	// visualizzazione del form
	@RequestMapping(value="/persona", method=RequestMethod.GET)
	public String savePersona(@ModelAttribute Persona personaForm, Model model) {
	    model.addAttribute("personaForm", personaForm);
	    return "persona";
	}
	
	// raccolta dati dal form
	@RequestMapping(value="/persona", method=RequestMethod.POST)
	public String savePersonaPost(@Valid @ModelAttribute("personaForm") Persona personaForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("personaForm", personaForm);
			return "persona";
		}
		logger.info("Dati persona inseriti: " + personaForm.getNome() + " " + personaForm.getCognome());
	    return "redirect:/persona";
	}
	
	@RequestMapping(value="/updatePersona", method=RequestMethod.GET)
	public String updatePersona(@ModelAttribute Persona personaForm, Model model) {
	    personaForm.setNome("Nome di default");
	    personaForm.setCognome("Cognome di default");
	    model.addAttribute("personaForm", personaForm);
	    return "persona";
	}
	
	
	
	
	// Gestione eccezioni
	@ExceptionHandler(MyException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Messaggio di errore personalizzato!") 
    public String gestoreEccezioni(MyException ex) {
		return "error";
	}
     
    @RequestMapping(value = "/error")
    public String handler() throws MyException {
    	throw new MyException("An error occured!");
    	//return "";
    }
    
    
    
    // Estensione Controller
    @RequestMapping(value="/get-book", method=RequestMethod.GET)
    public String getBook(Book book, Model model) {
        model.addAttribute("book", book);
        return "book";
    }

	

	
}
