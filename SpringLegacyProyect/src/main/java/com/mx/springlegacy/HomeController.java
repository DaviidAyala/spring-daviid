package com.mx.springlegacy;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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

import com.mx.springlegacy.beans.Empresa;
import com.mx.springlegacy.beans.Persona;
import com.mx.springlegacy.beans.PersonaTarea;
import com.mx.springlegacy.utils.Utileria;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ServletContextAware {

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

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletCont = servletContext;
	}

	@ResponseBody
	@RequestMapping(value = "/ronnie", method = RequestMethod.GET)
	public String getRonnieSeLaCome(Model model) {
		// model.addAttribute("ronnie", servletCont);
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.servletCont);
		ac.refresh();
		Persona person = (Persona) ac.getBean("ronnie");
		return person.getNombre().concat(" ").concat("se la come...");
	}

	@ResponseBody
	@RequestMapping(value = "/hector", method = RequestMethod.GET)
	public String getHectorSeLaCome(Model model) {
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.servletCont);
		ac.refresh();
		// Persona person = (Persona) ac.getBean("hector");
		Persona person = ac.getBean("hector", Persona.class);
		return person.getNombre().concat(" ").concat("se la come...");
	}

	@ResponseBody
	@RequestMapping(value = "/emmanuel", method = RequestMethod.GET)
	public String getSeLaCome(Model model) {
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.servletCont);
		ac.refresh();
		// Persona person = (Persona) ac.getBean("hector");
		Empresa empresa = ac.getBean("empresa", Empresa.class);
		return "El nombre del CEO ".concat(empresa.getCeo().getNombre()).concat(" ").concat("su edad es: ")
				.concat(empresa.getCeo().getEdad().toString()).concat(" ").concat("Nombre de la empresa: ")
				.concat(empresa.getNombre());
	}

	@ResponseBody
	@RequestMapping(value = "/empresa", method = RequestMethod.GET)
	public String getSeLaComeLosEmpleados(Model model) {
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.servletCont);
		ac.refresh();
		// Persona person = (Persona) ac.getBean("hector");
		Empresa empresa = ac.getBean("empresa", Empresa.class);
		return "El nombre del CEO ".concat(empresa.getCeo().getNombre()).concat(" ")
				.concat(empresa.getEmpleados().get(0).toString()).concat(" ")
				.concat(empresa.getEmpleados().get(1).toString()).concat(" ")
				.concat(empresa.getEmpleados().get(2).toString()).concat(" ")
				.concat(empresa.getEmpleados().get(3).toString()).concat(" ")
				.concat(empresa.getEmpleados().get(4).toString());
	}
	
	@ResponseBody
	@RequestMapping(value = "/tareaBeans", method = RequestMethod.GET)
	public String getPersonaTarea(Model model) {
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		context.setServletContext(this.servletCont);
		context.refresh();
		
		PersonaTarea tarea = context.getBean("emma", PersonaTarea.class);
		// usar listSort para ordenar
		
		List<PersonaTarea> persons = new ArrayList<PersonaTarea>();		
		persons.add(tarea);
		persons.add(context.getBean("jorge", PersonaTarea.class));
		persons.add(context.getBean("perla", PersonaTarea.class));
		persons.add(context.getBean("rosario", PersonaTarea.class));
		persons.add(context.getBean("angel", PersonaTarea.class));
		persons.add(context.getBean("alma", PersonaTarea.class));
		
		Collections.sort(persons, new Comparator<PersonaTarea>(){
			@Override
			public int compare(PersonaTarea per1, PersonaTarea per2) {
				return per1.getEdad() - per2.getEdad();
			}
		});
		
		StringBuilder stb1 = new StringBuilder();
		stb1.append("<body>");
		stb1.append("<div style='backgrond-color:grey; background-image:linear-gradient(90deg, red, orange, yellow, green, blue, purple); width:100%; height:150px; color:#fff;'>");
		stb1.append(tarea.getNombre());
		stb1.append(" ");
		stb1.append(tarea.getSexo());
		stb1.append(" ");
		stb1.append(tarea.getSueldoDiario());
		stb1.append(" ");
		stb1.append(Utileria.printEmpleadosInfo(tarea.getSubordinados()));
		stb1.append("</div>");
		
		stb1.append(Utileria.printEmpleadosInfo(persons));
		
		return stb1.toString();
	}

}
