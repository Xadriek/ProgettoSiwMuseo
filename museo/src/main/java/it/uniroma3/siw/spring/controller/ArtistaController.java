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

import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;

import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.upload.FileUploadUtil;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
    @Autowired
    private ArtistaValidator artistaValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/artista", method = RequestMethod.GET)
    public String addArtista(Model model) {
    	logger.debug("addArtista");
    	model.addAttribute("artista", new Artista());
        return "artistaForm.html";
    }

    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String getArtista(@PathVariable("id") Long id, Model model) {
    	Artista artista=this.artistaService.artistaPerId(id);
    	model.addAttribute("artista",artista );
    	model.addAttribute("opere",artista.getOpere() );
    	model.addAttribute("role", this.artistaService.getCredentialsService().getRoleAuthenticated());
    	return "artista.html";
    }

    @RequestMapping(value = "/artista", method = RequestMethod.GET)
    public String getArtisti(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		model.addAttribute("role", this.artistaService.getCredentialsService().getRoleAuthenticated());
    		return "artisti.html";
    }
    
   /* @RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
    
    
    // ma perche persona??? � artista no? me sa te sei sbagliato
    
    public String newArtista(@ModelAttribute("artista") Artista artista, 
    									Model model, BindingResult bindingResult) {
    	this.artistaValidator.validate(artista, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.artistaService.inserisci(artista);
            model.addAttribute("artisti", this.artistaService.tutti());
            return "artisti.html";
        }
        return "artistaForm.html";
    }*/
    @PostMapping("/admin/artista")
    public RedirectView newArtista(@ModelAttribute("artista") Artista artista,
    		@RequestParam("image") MultipartFile multipartFile,Model model, BindingResult bindingResult) throws IOException {
    	
    this.artistaValidator.validate(artista, bindingResult);
      if (!bindingResult.hasErrors()) {
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	artista.setPhotos(fileName);
    	
    	Artista savedArtista =this.artistaService.inserisci(artista);
    	
    	String uploadDir = "artista-photos/" + savedArtista.getId();
    	
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	
    	return new RedirectView("artisti");
    	}
      return new RedirectView("artistaForm");
      
     
}
    
    @RequestMapping(value ="/admin/artistaUpdate")
    public RedirectView updateArtista(@ModelAttribute("artista") Artista artista,
    		@RequestParam("image") MultipartFile multipartFile,
    		Model model, BindingResult bindingResult) throws IOException {
    	
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	artista.setPhotos(fileName);
    	
    	Artista savedArtista =this.artistaService.inserisci(artista);
    	
    	String uploadDir = "artista-photos/" + savedArtista.getId();
    	
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	
    	return new RedirectView("artisti");
    }
    
    @RequestMapping(value = "/admin/artisti", method = RequestMethod.GET)
    public String getArtisti2(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		return "uploadSuccessful.html";
    }
    @RequestMapping(value="/admin/artistaForm", method = RequestMethod.GET)
    public String addArtista2(Model model) {
    	logger.debug("addArtista2");
    	model.addAttribute("artista", new Artista());
        return "artistaForm.html";
    }
    @RequestMapping(value="/admin/modArtista/{id}",method= RequestMethod.GET)
    public String updateArtista(@PathVariable("id")Long id, Model model) {
    	logger.debug("UpdateArtista");
    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
    	model.addAttribute("role", this.artistaService.getCredentialsService().getRoleAuthenticated());
        return "artistaFormMod.html";
    }
    @RequestMapping(value="/admin/artista/{id}", method= RequestMethod.GET)
    public String removeArtista(@PathVariable("id")Long id, Model model) {
    	logger.debug("inizio eliminazione");
    		this.artistaService.deletedArtista(id);
    		logger.debug("artista cancellato");
    		model.addAttribute("artisti",this.artistaService.tutti());
        	model.addAttribute("role", this.artistaService.getCredentialsService().getRoleAuthenticated());

    		return "artisti.html";
		
    		
    }
}
