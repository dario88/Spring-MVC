package com.spring.step32.login;

import org.springframework.stereotype.Service;

/*
 *  Con l'annotation @Service sto creandno un bean che sarà gestito da Spring.
 *  Spring creerà quindi una istanza di LoginService.
 *  
 *  A questo punto, se un'altra classe deve usare tale servizio,
 *  non deve crearne uno nuovo ma potra usarlo tramite l'annotation @AutoWired
 *  
 *  NOTA: Sprin cerca i bean @Service e i @Controller solo all'interno del path indicato
 *  nel componente scan nel file step07-context.xml:
 *  	<context:component-scan base-package="com.in28minuti" />
 *  Quindi cercherà solo all'interno del package com.in28minuti e in tutti i suoi sottopackage.
 *  Perciò se definiamo un @Service dentro il package com.in30minuti, questo non verrà istanziato.
 */  
@Service
public class LoginService {
	
	
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("admin") && password.equals("admin");
    }

}
