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
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private UserValidator userValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
    	logger.debug("addUser");
    	model.addAttribute("user", new User());
        return "userForm.html";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("user", this.userService.getUser(id));
    	return "user.html";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getUsers(Model model) {
    		model.addAttribute("users", this.userService.getAllUsers());
    		return "users.html";
    }
    
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("user") User user, 
    									Model model, BindingResult bindingResult) {
    	this.userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.userService.saveUser(user);
            model.addAttribute("users", this.userService.getAllUsers());
            return "users.html";
        }
        return "userForm.html";
    }
}
