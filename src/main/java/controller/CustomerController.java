package controller;


import exception.NotfoundException;
import model.Category;
import model.Customer;
import model.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ICustomerService;
import service.category.ICategoryService;

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

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping()
    public ModelAndView getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("search")
    public ModelAndView getCustomerByAddress(String address){
        List<Customer> customers = customerService.findByAddress(address);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    //hien thi form tao moi
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        //ghi log
        return modelAndView;

    }
    //hien thi chi tiet khach hang
    @GetMapping("/{id}")
    public ModelAndView detailCustomer(@PathVariable Long id) throws NotfoundException {
        ModelAndView modelAndView = new ModelAndView("/customer/detail");
        modelAndView.addObject("customer", customerService.findById(id));
        //ghi log
        return modelAndView;

    }

    @ExceptionHandler(NotfoundException.class)
    public ModelAndView notfound(){
        return new ModelAndView("notfound");
    }


    //xu ly khi nhan nut tao moi
//    @PostMapping("/create")
//    public String createNewCustomer(@Validated CustomerForm customerForm, BindingResult bindingResult, Model modelMap){
//        //sao chep anh vao thu muc
//        if (!bindingResult.hasFieldErrors()){
//
//            MultipartFile multipartFile = customerForm.getImg();
//            String fileName = multipartFile.getOriginalFilename();
//            String localFile = environment.getProperty("file_upload");
//            try {
//                FileCopyUtils.copy(multipartFile.getBytes(), new File(localFile+fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Customer customer = new Customer();
//            customer.setImg(fileName);
//            customer.setAddress(customerForm.getAddress());
//            customer.setEmail(customerForm.getEmail());
//            customer.setName(customerForm.getName());
//            customer.setCategory(customerForm.getCategory());
//            customerService.save(customer);
//            return "redirect: /customers";
//        }
//        else {
//            modelMap.addAttribute("customer", customerForm);
//            return "/customer/create";
//        }
//    }
    //xu ly khi nhan nut tao moi
    @PostMapping("/create")
    public ModelAndView createNewCustomer(@Validated @ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult, Model modelMap){
        //sao chep anh vao thu muc
        if (!bindingResult.hasErrors()){

            MultipartFile multipartFile = customerForm.getImg();
            String fileName = multipartFile.getOriginalFilename();
            String localFile = environment.getProperty("file_upload");
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(localFile+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Customer customer = new Customer();
            customer.setImg(fileName);
            customer.setAddress(customerForm.getAddress());
            customer.setEmail(customerForm.getEmail());
            customer.setName(customerForm.getName());
            customer.setCategory(customerForm.getCategory());
            customerService.save(customer);
            //ghi log
            return getAllCustomer();
        }
        else {
            return new ModelAndView("/customer/create");
        }
    }
}
