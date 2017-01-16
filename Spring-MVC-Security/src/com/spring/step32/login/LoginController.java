package com.spring.step32.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
/*
 * L'annotation SessionAttributes serve per dire quale attributo deve rimanere memorizzato in
 * sessione. Qui memorizziamo il 'name' altrimenti poi quando andiamo a vedere la lista degli
 * esami avremo questo valore nullo e quindi una lista vuota anzichè inizializzata.
 * 
 * Il metodo model.put("qualcosa", qualcosa) memorizza quel qualcosa solo per la richiesta, quindi
 * una volta esaudita la richiesta quel valore viene distrutto (e quindi se viene fatta una nuova
 * richiesta quel valore non è più presente nel Model).
 * Per far si che il valore che salviamo nel Model sia memorizzato per sempre anche nelle richieste
 * successive, occorre salvare quel valore nella sessione.
 * Per farlo si usa l'annotation @SessionAttribute, che prende il corrispondente attributo aggiunto
 * al Model (nel metodo handleLogin) e lo memorizza in Sessione.
 */
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	/*
	 * Se LoginService non avesse l'annotation @Service, non avremo potuto usare @Autowired,
	 * ma dovevamo instanziare la classe alla vecchia maniera. 
	 */
	//private LoginService loginService = new LoginService();
	
	
	/*
	 * QUESTI DUE METODI /login NON SERVONO PIU,
	 * perchè la login verrà gestita da Spring Security.
	 * 
	 * Quindi anche la classe LoginService può essere eliminata
	 */	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handleLogin(ModelMap model, @RequestParam String name, @RequestParam String password) {

		if (!loginService.validateUser(name, password)) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        model.put("name", name);
        return "welcome";
	}
	
	
	
	/*
	 * Nei sistemi di sicurezza, tipicamente l'oggetto principal l'utente loggato
	 * con username e password.
	 * Quello che noi vogliamo da questo oggetto è la sua username (cioè 'admin').
	 * 
	 * Questo ci permette di non avere lo username scolpito dentro il codice
	 */
	private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
	
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUserName());
		return "welcome";
	}	

	


}
