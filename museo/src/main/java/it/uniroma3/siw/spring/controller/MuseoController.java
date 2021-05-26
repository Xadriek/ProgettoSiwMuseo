package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.MuseoValidator;
import it.uniroma3.siw.spring.model.Museo;
import it.uniroma3.siw.spring.service.MuseoService;

@Controller
public class MuseoController {
	
	@Autowired
	private MuseoService museoService;
	
    @Autowired
    private MuseoValidator MuseoValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

  

   
    
   
    
}
