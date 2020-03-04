package com.mx.springlegacy;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.mx.springlegacy.beans.Persona;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ServletContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private ServletContext servletCont;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletCont = servletContext;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ronnie", method = RequestMethod.GET)
	public String setRonnieSeLaCome(Model model) {
		//model.addAttribute("ronnie", servletCont);
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.servletCont);
		ac.refresh();
		Persona person = ac.getBean(Persona.class);
		return person.getNombre().concat(" ").concat("se la come...");
	}
	
	
}
