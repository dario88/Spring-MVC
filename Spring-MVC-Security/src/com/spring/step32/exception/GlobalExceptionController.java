package com.spring.step32.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * GLOBAL EXCEPTION HANDLING
 * 
 * Questa classe gestisce le eccezioni a livello globale,
 * cioè vale per tutti i Controller dell'applicazione 
 */

@ControllerAdvice
public class GlobalExceptionController {
	
	private Log logger = LogFactory.getLog(GlobalExceptionController.class);

	
    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " Raised an exception: ", exception);
        return "error";
    }

}
