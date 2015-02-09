package br.com.controleaula.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.controleaula.service.BaseService;

@Controller
public class BaseController<T> {
	
	private final String BASE_VIEW = "base-view";
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	private Class<T> clazz;
	public void setClazz(Class<T> clazz){
		this.clazz = clazz;
	}
	
	
	@Autowired
	private BaseService<T> service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		logger.info("["+clazz.getName()+"]list()...");
		ModelAndView mv = new ModelAndView(BASE_VIEW);
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");
		mv.addObject("content_title", clazz.getSimpleName());
		mv.addObject("request_mapping_add", "/"+clazz.getSimpleName().toLowerCase()+"/add");
		mv.addObject("model_name", clazz.getSimpleName());
		
		mv.addObject("list", this.service.listAll(clazz));
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		logger.info("["+clazz.getName()+"]form()...");
		ModelAndView mv = new ModelAndView(BASE_VIEW);
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");
		mv.addObject("content_title", clazz.getSimpleName());
		mv.addObject("request_mapping_add", "/"+clazz.getSimpleName().toLowerCase()+"/add");
		mv.addObject("model_name", clazz.getSimpleName());
		
		@SuppressWarnings("unchecked")
		T model = (T)Class.forName( clazz.getName() ).newInstance();
		mv.addObject("model", model);
		
		return mv;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id) {
		logger.info("["+clazz.getName()+"]edit()...");
		ModelAndView mv = new ModelAndView(BASE_VIEW);
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");
		mv.addObject("content_title", clazz.getSimpleName());
		mv.addObject("request_mapping_add", "/"+clazz.getSimpleName().toLowerCase()+"/add");
		mv.addObject("model_name", clazz.getSimpleName());
		
		T model = this.service.findById(clazz, id);
		mv.addObject("model", model);
		
		return mv;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView save(T entity) {
		logger.info("["+clazz.getName()+"]list()...");
		ModelAndView mv = new ModelAndView(clazz.getSimpleName().toLowerCase());
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");		
		
		this.service.save(entity);
		mv.addObject("list", this.service.listAll(clazz));
		return mv;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(Long id) {
		logger.info("["+clazz.getName()+"]list()...");

		ModelAndView mv = new ModelAndView(clazz.getSimpleName().toLowerCase());
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");
		this.service.delete(clazz, id);
		mv.addObject("list", this.service.listAll(clazz));
		return mv;
	}
	
}
