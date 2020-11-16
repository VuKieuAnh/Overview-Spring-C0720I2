package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/class")
public class HomeController {

    @RequestMapping(value = "/c0720i2", method = RequestMethod.GET)
    public String index(){
        return "home";
    }

    @GetMapping("/c0720i2demo")
    public String home(){
        return "home";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("idcuasanpham", id);
        return modelAndView;
    }
    @GetMapping("/detail")
    public ModelAndView detailProduct(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}
