package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.model.entity.Customer;
import ra.model.service.CustomerService;


import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //  show list
    @GetMapping("/")
    public String list_customer(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("list", customers);
        return "customer";
    }

    @GetMapping("add")
    public String add(Model model){
        Customer customer = new Customer();
        model.addAttribute("newCus",customer);
        return "add-customer";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("newCus") Customer customer){
        if (customerService.saveOrUpdate(customer)){
        return "redirect:/";
        }
        return "redirect:/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        customerService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("editCus",customer);
        return "edit_customer";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("editCus") Customer customer){
        if (customerService.saveOrUpdate(customer)){
            return "redirect:/";
        }
        return "edit_customer";
    }
}
