package com.example.upload_multi_file.controller;

import com.example.upload_multi_file.model.Image;
import com.example.upload_multi_file.model.Product;
import com.example.upload_multi_file.service.IImageService;
import com.example.upload_multi_file.service.IProductService;
import com.example.upload_multi_file.service.ImageServiceIMPL;
import com.example.upload_multi_file.service.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping(value = {"/","/product"})
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;
    IProductService productService = new ProductServiceIMPL();
    IImageService imageService = new ImageServiceIMPL();

    @GetMapping
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/save")
    public String create(@RequestParam ("files") MultipartFile[] files, @ModelAttribute ("product") Product newProduct) {
        int pId;
        if (productService.findAll().isEmpty()) {
            pId = 1;
        } else {
            pId = productService.findAll().get(productService.findAll().size() - 1).getId() + 1;
        }
        productService.save(newProduct);
        try {
            for (MultipartFile file : files) {
                Image newImage = new Image();
                String fileName = file.getOriginalFilename();
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
                newImage.setUrl(fileName);
                newImage.setProductId(pId);
                imageService.save(newImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/create";
    }
}
