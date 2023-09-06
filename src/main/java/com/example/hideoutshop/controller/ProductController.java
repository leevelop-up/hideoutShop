package com.example.hideoutshop.controller;

import com.example.hideoutshop.repository.list.Product;
import com.example.hideoutshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService listService;

    @PostMapping(value = "/api/product/setting")
    public String setting(@ModelAttribute Product product, MultipartFile file) throws Exception {
        Boolean isSuccess = listService.save(product, file);

        return isSuccess ? "상품이 등록되었습니다." : "등록 실패하였습니다.";
    }

    @GetMapping(value = "/product/board")
    public List<Product> BoardList(Model model){
        List<Product> productList = listService.Boardlist();
        model.addAttribute("list",productList);

        return productList;
    }

}
