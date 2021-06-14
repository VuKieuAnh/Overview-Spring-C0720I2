package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/classes")
public class HomeController {

    @RequestMapping(value = "/c0221i1", method = RequestMethod.GET)
    public String index(){
        return "home";
    }

    @GetMapping("/demo")
    public String home(){
        return "home";
    }

    @GetMapping("/demo1")
    public String home1(){
        return "detail";
    }
    @GetMapping("/{id}")
    public String getId(@PathVariable("id") Long id1, Model model) {
        model.addAttribute("idSP", id1);
        return "detail";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView modelAndView(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("idSP", id);
        Map a = new HashMap();
        a.put("a", "a");
        a.put("b", "b");
        modelAndView.addAllObjects(a);
//        ModelAndView modelAndView = new ModelAndView("detail", "idSP", id);
//        modelAndView.addObject("afhs", "ajfhasjf");
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView modelAndView(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("name", name);
        return modelAndView;

    }

    @GetMapping("/create")
    public ModelAndView createNewName(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam String name, Long age){
        ModelAndView modelAndView = new ModelAndView("product/create");
        return  modelAndView;
    }

    //
//    @GetMapping("/detail/{id}")
//    public ModelAndView detail(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("detail");
//        modelAndView.addObject("idcuasanpham", id);
//        return modelAndView;
//    }
//    @GetMapping("/detail")
//    public ModelAndView detailProduct(@RequestParam String name){
//        ModelAndView modelAndView = new ModelAndView("details");
//        modelAndView.addObject("name", name);
//        return modelAndView;
//    }
}
