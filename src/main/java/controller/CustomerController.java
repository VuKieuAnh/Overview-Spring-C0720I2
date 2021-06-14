package controller;


import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
//    private final ICustomerService customerService = new CustomerService();
    @Autowired
    private ICustomerService customerService;

    @GetMapping()
    public ModelAndView getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("list", customers);
        return modelAndView;
    }
    //hien thi form tao moi
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("c", new Customer());
        return modelAndView;

    }
    //xu ly khi nhan nut tao moi
    @PostMapping("/create")
    public ModelAndView createNewCustomer(Customer customer){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("c", new Customer());
        modelAndView.addObject("mess","tao moi thanh cong");
        return modelAndView;
    }
}
