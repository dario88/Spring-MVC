package com.spring.step32.esame;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.step32.exception.GlobalExceptionController;
import com.spring.step32.model.Esame;


@Controller
/*
 * L'annotation SessionAttributes serve per dire quale attributo deve rimanere memorizzato in
 * sessione. Qui memorizziamo il 'name' (ossia lo studente) altrimenti poi quando andiamo a vedere la
 * lista degli esami avremo questo valore nullo e quindi una lista vuota anzichè inizializzata.
 * 
 */
@SessionAttributes("name")
public class EsameController {
	
	private Log logger = LogFactory.getLog(GlobalExceptionController.class);
	
	@Autowired
	private EsameService service;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	
	private String getLoggedInUserName() {
		/*
		 * Nei sistemi di sicurezza, tipicamente l'oggetto principal l'utente loggato
		 * con username e password.
		 * Quello che noi vogliamo da questo oggetto è la sua username (cioè 'admin').
		 * 
		 * Questo ci permette di non avere lo username scolpito dentro il codice
		 */
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

        return principal.toString();
    }
	
	
	/*
	 * LOCAL EXCEPTION HANDLING
	 * 
	 * Questo metodo gestisce le eccezioni a livello locale,
	 * cioè vale solo per le eccezioni sollevate da EsameController 
	 */
	@ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " Raised an exception: ", exception);
        return "error-esame";
    }
	
	
	@RequestMapping(value="/lista-esami", method=RequestMethod.GET)
	public String getListaEsami(ModelMap model) {
		//String studente = (String)model.get("name");
		String studente = getLoggedInUserName();
        model.addAttribute("esami", service.cercaEsami(studente));
        return "lista-esami";
	}
	
	
	@RequestMapping(value = "/add-esame", method = RequestMethod.GET)
    public String mostraPaginaAddEsame(ModelMap model) {
		/*
		 * Con la GET creiamo un nuovo esame e lo aggiungiamo al model.
		 * Questo serve affinchè poi, con la POST, possa essere usato il binding.
		 * 
		 * Cioè per poter usare il binding, devo prima rendere l'oggetto disponibile,
		 * e questo lo faccio qui creando un nuovo oggetto Esame e aggiungendolo al model.
		 */
		//Nota: il nome 'esame' deve fare scopa con il valore del commandName della jsp esame.jsp
        model.addAttribute("esame", new Esame());
        return "esame";
    }
	
	/*
	 * Qui usiamo anche la validazione tramite l'annotation @Valid prima dell'oggetto da validare.
	 * Inoltre se vogliamo avere anche i risultati della validazione, aggiungiamo alla signature del
	 * metodo il parametro BindingResult: quando avviene il binding, il BindingResult contiene tutti
	 * gli errori della validazione.
	 */
	@RequestMapping(value = "/add-esame", method = RequestMethod.POST)
    public String addEsame(ModelMap model, @Valid Esame esame, BindingResult result) {
        if (result.hasErrors())
            return "esame";

        service.aggiungiEsame(getLoggedInUserName(), esame.getDesc(), esame.getData(), false);
        model.clear();	// to prevent request parameter "studente" to be passed
        return "redirect:/lista-esami";
    }
	
	
	@RequestMapping(value = "/update-esame", method = RequestMethod.GET)
    public String mostraPaginaUpdateEsame(ModelMap model, @RequestParam int id) {
        model.addAttribute("esame", service.cercaEsame(id));
        return "esame";
    }

    @RequestMapping(value = "/update-esame", method = RequestMethod.POST)
    public String updateEsame(ModelMap model, @Valid Esame esame, BindingResult result) {
        if (result.hasErrors())
            return "esame";

        //esame.setStudente("admin");
        esame.setStudente(getLoggedInUserName());
        service.aggiornaEsame(esame);

        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/lista-esami";
    }
	
	
	@RequestMapping(value = "/delete-esame", method = RequestMethod.GET)
    public String deleteEsame(@RequestParam int id) {
        service.rimuoviEsame(id);

        return "redirect:/lista-esami";
    }

}
