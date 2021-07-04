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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.upload.FileUploadUtil;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	

    @Autowired
    private CollezioneValidator collezioneValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/collezione", method = RequestMethod.GET)
    public String addCollezione(Model model) {
    	logger.debug("addCollezione");
    	model.addAttribute("collezione", new Collezione());
    	model.addAttribute("curatori",this.collezioneService.getCuratoreService().tutti());
    	//model.addAttribute("curatori", this.collezioneService.getCuratoreService().tutti());
    //	model.addAttribute("opere", this.collezioneService.getOperaService().opereSenzaCollezione());
        return "collezioneForm.html";
    }

    @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	return "collezione.html";
    }

    @RequestMapping(value = "/collezione", method = RequestMethod.GET)
    public String getCollezioni(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.tutti());
    		return "collezioni.html";
    }
    
  
    @PostMapping("/admin/collezione")
    public RedirectView newCollezione(@ModelAttribute("collezione") Collezione collezione,
    		@RequestParam("image") MultipartFile multipartFile,Model model, BindingResult bindingResult) throws IOException {
    	
    this.collezioneValidator.validate(collezione, bindingResult);
      if (!bindingResult.hasErrors()) {
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	collezione.setPhotos(fileName);
    	Collezione savedCollezione =this.collezioneService.inserisci(collezione);
    	
    	String uploadDir = "collezione-photos/" + savedCollezione.getId();
    	model.addAttribute("collezioni", this.collezioneService.tutti());
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	
    	return new RedirectView("collezioni");
    	}
      return new RedirectView("collezioneForm");
    }

        
    @RequestMapping(value = "/admin/collezioni", method = RequestMethod.GET)
    public String getCollezioni2(Model model) {
		
		return "uploadSuccessful.html";
    }
    
    @RequestMapping(value="/admin/collezioneForm", method = RequestMethod.GET)
    public String addCollezioni2(Model model) {
    	logger.debug("addCollezioneFailed");
    	model.addAttribute("collezione", new Collezione());
    	model.addAttribute("curatori",this.collezioneService.getCuratoreService().tutti());
    	return "collezioneForm.html";
    }
    @RequestMapping(value="/admin/collezione/{id}", method= RequestMethod.GET)
    public String removeCollezione(@PathVariable("id")Long id, Model model) {
    	logger.debug("inizio eliminazione");
    		this.collezioneService.deletedCollezione(id);
    		logger.debug("collezione cancellata");
    		model.addAttribute("collezioni",this.collezioneService.tutti());
    		return "collezioni.html";
		
    		
    }
    @RequestMapping(value="/admin/modCollezione/{id}",method= RequestMethod.GET)
    public String updateOpera(@PathVariable("id")Long id, Model model) {
    	logger.debug("UpdateCollezione");
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	model.addAttribute("curatori",this.collezioneService.getCuratoreService().tutti());
    	
        return "collezioneForm.html";
    }

}
    
 /*   
    @RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
    public String newCollezione(@ModelAttribute("collezione") Collezione collezione, 
    									Model model, BindingResult bindingResult) {
    	this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.collezioneService.inserisci(collezione);
            model.addAttribute("collezioni", this.collezioneService.tutti());
            return "collezioni.html";
        }
        model.addAttribute("curatori",this.collezioneService.getCuratoreService().tutti());
        return "collezioneForm.html";
    }

*/
    
    
    