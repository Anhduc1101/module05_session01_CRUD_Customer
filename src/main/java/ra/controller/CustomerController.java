package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Customer;
import ra.model.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
//  show bang du lieu
    @GetMapping("/")
    public String getList(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/customer";
    }

//  them
    @GetMapping("/add-customer")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add-customer";
    }

    @PostMapping("/add-customer")
    public String add_customer(@ModelAttribute("customers") Customer customer) {
        if (customerService.saveOrUpdate(customer)) {
            return "redirect:/";
        }
        return "redirect:/add-customer";
    }

//  sua
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("cus", customer);
        return "edit_customer";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cus") Customer customer ){
        if (customerService.saveOrUpdate(customer)){
        return "redirect:/";
        }
        return "redirect:/edit/"+customer.getId();
    }

//  xoa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        customerService.deleteById(id);
        return "redirect:/";
    }
}
