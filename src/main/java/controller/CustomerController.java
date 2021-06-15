package controller;


import model.Customer;
import model.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.ICustomerService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
//    private final ICustomerService customerService = new CustomerService();
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private Environment environment;

    @GetMapping()
    public ModelAndView getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    //hien thi form tao moi
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;

    }
    //xu ly khi nhan nut tao moi
    @PostMapping("/create")
    public String createNewCustomer(CustomerForm customerForm){
        //sao chep anh vao thu muc
        MultipartFile multipartFile = customerForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        String localFile = environment.getProperty("file_upload");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(localFile+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Customer customer = new Customer();
        customer.setId((int) (Math.random() * 10000));
        customer.setImg(fileName);
        customer.setAddress(customerForm.getAddress());
        customer.setEmail(customerForm.getEmail());
        customer.setName(customerForm.getName());
        customerService.save(customer);
        return "redirect:/customers";
    }
}
