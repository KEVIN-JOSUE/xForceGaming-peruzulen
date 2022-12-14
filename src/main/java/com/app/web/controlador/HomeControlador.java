package com.app.web.controlador;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.entidad.articulos;
import com.app.web.repositorios.articulosRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class HomeControlador {

    @Autowired
    private  articulosRepositorio articlerepositorio;

    @Autowired
    private MessageSource mensaje;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(Model model, HttpServletRequest request) {
            Locale currentLocale = request.getLocale();
            String countryCode = currentLocale.getCountry();
            String countryName = currentLocale.getDisplayCountry();

            String langCode = currentLocale.getLanguage();
            String langName = currentLocale.getDisplayLanguage();

            System.out.println(countryCode + ":" + countryName);
            System.out.println(langCode + ":" + langName);

            model.addAttribute("titulo" , mensaje.getMessage("saludo.title", null, currentLocale));

            List<articulos> ultimosArticulos=articlerepositorio.findAll(PageRequest.of(0,3,Sort.by("fechaEstreno").descending())).toList();
            return new ModelAndView("index").addObject("ultimosArticulos",ultimosArticulos);
    }
    
    @GetMapping("/en")
    public ModelAndView verPaginaDeInicioIngles(Model model, HttpServletRequest request) {
            Locale currentLocale = request.getLocale();
            String countryCode = currentLocale.getCountry();
            String countryName = currentLocale.getDisplayCountry();

            String langCode = currentLocale.getLanguage();
            String langName = currentLocale.getDisplayLanguage();

            System.out.println(countryCode + ":" + countryName);
            System.out.println(langCode + ":" + langName);

            model.addAttribute("titulo" , mensaje.getMessage("saludo.title", null, Locale.US));
            //model.addAttribute("titulo" , mensaje.getMessage("saludo.title", null, Locale.FRANCE));

            List<articulos> ultimosArticulos=articlerepositorio.findAll(PageRequest.of(0,3,Sort.by("fechaEstreno").descending())).toList();
            return new ModelAndView("index").addObject("ultimosArticulos",ultimosArticulos);
    }
    
    @GetMapping("/es")
    public ModelAndView verPaginaDeInicioEspanol(Model model, HttpServletRequest request) {
            Locale currentLocale = request.getLocale();
            String countryCode = currentLocale.getCountry();
            String countryName = currentLocale.getDisplayCountry();

            String langCode = currentLocale.getLanguage();
            String langName = currentLocale.getDisplayLanguage();

            System.out.println(countryCode + ":" + countryName);
            System.out.println(langCode + ":" + langName);

            model.addAttribute("titulo" , mensaje.getMessage("saludo.title", null, currentLocale));

            List<articulos> ultimosArticulos=articlerepositorio.findAll(PageRequest.of(0,3,Sort.by("fechaEstreno").descending())).toList();
            return new ModelAndView("index").addObject("ultimosArticulos",ultimosArticulos);
    }
    
    @GetMapping("/it")
    public ModelAndView verPaginaDeInicioFrances(Model model, HttpServletRequest request) {
            Locale currentLocale = request.getLocale();
            String countryCode = currentLocale.getCountry();
            String countryName = currentLocale.getDisplayCountry();

            String langCode = currentLocale.getLanguage();
            String langName = currentLocale.getDisplayLanguage();

            System.out.println(countryCode + ":" + countryName);
            System.out.println(langCode + ":" + langName);

            model.addAttribute("titulo" , mensaje.getMessage("saludo.title", null, Locale.ITALIAN));

            List<articulos> ultimosArticulos=articlerepositorio.findAll(PageRequest.of(0,3,Sort.by("fechaEstreno").descending())).toList();
            return new ModelAndView("index").addObject("ultimosArticulos",ultimosArticulos);
    }

    @GetMapping("/articulos")
    public ModelAndView listarArticulos(@PageableDefault(sort="fechaEstreno",direction = Sort.Direction.DESC)Pageable pageable) {
         Page<articulos>articulo=articlerepositorio.findAll(pageable);
         return new ModelAndView("articulos").addObject("articulos",articulo);
    }

    @GetMapping("/articulos/{id}")
    public ModelAndView mostrarDetallesDeArticulos(@PathVariable Integer id) {
            articulos articulo=articlerepositorio.getOne(id);
            return new ModelAndView("articulo").addObject("articulo",articulo);
    }
	
}
