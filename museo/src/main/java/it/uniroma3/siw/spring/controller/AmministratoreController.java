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

import it.uniroma3.siw.spring.controller.validator.AmministratoreValidator;
import it.uniroma3.siw.spring.model.Amministratore;
import it.uniroma3.siw.spring.service.AmministratoreService;

@Controller
public class AmministratoreController {
	
	@Autowired
	private AmministratoreService amministratoreService;
	
    @Autowired
    private AmministratoreValidator amministratoreValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/addAmministratore", method = RequestMethod.GET)
    public String addAmministratore(Model model) {
    	logger.debug("addAmministratore");
    	model.addAttribute("amministratore", new Amministratore());
        return "amministratoreForm.html";
    }

    @RequestMapping(value = "/amministratore/{id}", method = RequestMethod.GET)
    public String getAmministratore(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("amministratore", this.amministratoreService.amministratorePerId(id));
    	return "amministratore.html";
    }

    @RequestMapping(value = "/amministratore", method = RequestMethod.GET)
    public String getAmministratori(Model model) {
    		model.addAttribute("amministratori", this.amministratoreService.tutti());
    		return "amministratori.html";
    }
    
    @RequestMapping(value = "/amministratore", method = RequestMethod.POST)
    public String newAmministratore(@ModelAttribute("amministratore") Amministratore amministratore, 
    									Model model, BindingResult bindingResult) {
    	this.amministratoreValidator.validate(amministratore, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.amministratoreService.inserisci(amministratore);
            model.addAttribute("amministratori", this.amministratoreService.tutti());
            return "amministratori.html";
        }
        return "amministratoreForm.html";
    }
}
