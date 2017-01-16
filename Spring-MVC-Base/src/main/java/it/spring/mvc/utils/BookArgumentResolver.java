package it.spring.mvc.utils;

import java.util.Date;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import it.spring.mvc.Book;

public class BookArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		// TODO Auto-generated method stub
		
		Book book = new Book();	     
		book.setId(Long.parseLong(arg2.getParameter("book_id")));
		book.setTitolo("Fake Titolo");
		book.setAutore("Fake Autore");
		book.setDataUscita(new Date());
	    return book;
	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		// TODO Auto-generated method stub
		
		return Book.class == arg0.getParameterType();
	}

}
