package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ProductController {
@Autowired
private ProductRepository productRepository;
@GetMapping("/")
public String viewHomePage(Model model) {
model.addAttribute("listProducts", productRepository.findAll());
return "index";
}
@GetMapping("/showNewProductForm")
public String showNewProductForm(Model model) {
model.addAttribute("product", new Product());
return "new_product";
}
@PostMapping("/saveProduct")
public String saveProduct(@ModelAttribute("product") Product product) {
productRepository.save(product);
return "redirect:/";
}
@GetMapping("/updateProduct/{id}")
public String showFormForUpdate(@PathVariable(value = "id") int id, Model model)
{
Product product = productRepository.findById(id).orElseThrow(() -> new
IllegalArgumentException("Invalid product Id:" + id));
model.addAttribute("product", product);
return "update_product";
}
@GetMapping("/deleteProduct/{id}")
public String deleteProduct(@PathVariable(value = "id") int id) {
Product product = productRepository.findById(id).orElseThrow(() -> new
IllegalArgumentException("Invalid product Id:" + id));
productRepository.delete(product);
return "redirect:/";
}
}
