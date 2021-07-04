package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.upload.FileUploadUtil;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
    @Autowired
    private OperaValidator operaValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/opera", method = RequestMethod.GET)
    public String addOpera(Model model,String role) {
    	logger.debug("addOpera");
    	model.addAttribute("opera", new Opera());
    	model.addAttribute("collezioni",this.operaService.getCollezioneService().tutti());
    	model.addAttribute("artisti",this.operaService.getArtistaService().tutti());
        return "operaForm.html";
    }
    

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getOpera(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "opera.html";
    }
    @RequestMapping(value="/admin/opera/{id}", method= RequestMethod.GET)
    public String removeOpera(@PathVariable("id")Long id, Model model) {
    	logger.debug("inizio eliminazione");
    		this.operaService.deletedOpera(id);
    		logger.debug("opera cancellata");
    		model.addAttribute("opere",this.operaService.tutti());
    		return "opere.html";
		
    		
    }
    @RequestMapping(value="/modOpera/{id}",method= RequestMethod.GET)
    public String updateOpera(@PathVariable("id")Long id, Model model) {
    	logger.debug("UpdateOpera");
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	model.addAttribute("collezioni",this.operaService.getCollezioneService().tutti());
    	model.addAttribute("artisti",this.operaService.getArtistaService().tutti());

        return "operaForm.html";
    }

    @RequestMapping(value = "/opera", method = RequestMethod.GET)
    public String getOpere(Model model) {
    		model.addAttribute("opere", this.operaService.tutti());

    		return "opere.html";
    }
    
   /* @RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
    public String newOpera(@ModelAttribute("opera") Opera opera, 
    									Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.operaService.inserisci(opera);
            model.addAttribute("opere", this.operaService.tutti());
            return "opere.html";
        }
        return "operaForm.html";
    }*/
    @PostMapping("/admin/opera")
    public RedirectView newOpera(@ModelAttribute("opera") Opera opera,
    		@RequestParam("image") MultipartFile multipartFile,Model model, BindingResult bindingResult,String role) throws IOException {
    	
    this.operaValidator.validate(opera, bindingResult);
      if (!bindingResult.hasErrors()) {
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	opera.setPhotos(fileName);
    	
    	
    	Opera savedOpera =this.operaService.inserisci(opera);
    	
    	String uploadDir = "opera-photos/" + savedOpera.getId();
    	
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	model.addAttribute("opere", this.operaService.tutti());
    	model.addAttribute("role", role);
    	return new RedirectView("opere");
    	}
      return new RedirectView("operaForm");
}
    @RequestMapping(value = "/admin/opere", method = RequestMethod.GET)
    public String getOpere2(Model model) {
    		
    		return "uploadSuccessful.html";
    }
    @RequestMapping(value="/admin/operaForm", method = RequestMethod.GET)
    public String addOpera2(Model model) {
    	logger.debug("addOpera");
    	model.addAttribute("opera", new Opera());
    	model.addAttribute("collezioni",this.operaService.getCollezioneService().tutti());
        return "operaForm.html";
    }

}
