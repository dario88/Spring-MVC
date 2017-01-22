package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * L'annotation "@SpringBootApplication" fa si che Spring:
 *  - inizializzi tutti i Bean
 *  - riconosca le annotation
 *  - riconosca le configurazioni
 *  - ecc...
 *
 *  Praticamente equivale a scrivere:
 *  - @Configuration
 *  - @EnableAutoConfiguration
 *  - @ComponentScan
 */
@SpringBootApplication
public class SpringBootMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);
	}

}
// https://www.youtube.com/watch?v=Ke7Tr4RgRTs



/*
 *  									### STRUTTURA DELL'APPLICATIVO ###
 *  
 *  							   |						   |						|
 *  	Controller Layer		---|-->		Service Layer	---|-->		DAO Layer	 ---|-->	DB
 *  							   |						   |					    |
 *	 							   |						   |					    |
 *  	Handles HTTP methods	<--|---		Business Logic	<--|---		Data Access	 <--|---	DB
 *  							   |						   |					    |
 */
