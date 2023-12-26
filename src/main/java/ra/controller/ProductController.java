package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.model.entity.Customer;
import ra.model.entity.Product;
import ra.model.service.CustomerService;
import ra.model.service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;
    @GetMapping("/product")
    public String list_product(Model model){
        List<Product> products =productService.findAll();
        System.out.println(products.size());
        model.addAttribute("products",products);
        return "product";
    }

    @GetMapping("add-product")
    public String add_product(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        List<Customer> customers=customerService.findAll();
        model.addAttribute("customers",customers);
        return "add-product";
    }

    @PostMapping("add-product")
    public String add_product(@ModelAttribute("product") Product product){
        if (productService.saveOrUpdate(product)){
            return "redirect:/product";
        }
        return "redirect:/add-product";
    }

    @GetMapping("edit-product/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Product product=productService.findById(id);
        model.addAttribute("product",product);
        List<Customer> customers=customerService.findAll();
        model.addAttribute("customers",customers);
        return "edit-product";
    }
    @PostMapping("update-product")
    public String update(@ModelAttribute("product") Product product){
        if (productService.saveOrUpdate(product)){
            return "redirect:/product";
        }
        return "edit-product";
    }

    @GetMapping("delete-product/{id}")
    public String deleteProduct(@PathVariable("id")Integer id){
        productService.deleteById(id);
        return "redirect:/product";
    }
}
