package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService = new ProductService();

    @GetMapping()
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Product> products = productService.productList();
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        for (Product product : products) {
            product.setPriceFormatted(decimalFormat.format(product.getPrice()));
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes attributes) {
        product.setId((long) (Math.random() * 1000));
        productService.save(product);
        attributes.addFlashAttribute("products", "Add new products successfully");
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        product.setPriceFormatted(decimalFormat.format(product.getPrice()));
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("products", product);
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product, RedirectAttributes attributes) {
        productService.save(product);
        attributes.addFlashAttribute("products", "Successful product update");
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        Product product = productService.findById(id);
        productService.delete(product.getId());
        attributes.addAttribute("success", "Product deletion successful");
        return "redirect:/products";
    }


    @GetMapping("/views/{id}")
    public ModelAndView views(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/views");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", productService.findByName(name));
        return modelAndView;
    }
}
