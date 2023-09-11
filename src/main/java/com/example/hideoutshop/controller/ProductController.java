package com.example.hideoutshop.controller;

import com.example.hideoutshop.repository.list.Product;
import com.example.hideoutshop.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @ApiOperation("상품등록")

    @PostMapping(value = "/api/product/setting")
    public String setting(@ModelAttribute Product product, MultipartFile file) throws Exception {
        Boolean isSuccess = productService.save(product, file);
        return isSuccess ? "상품이 등록되었습니다." : "등록 실패하였습니다.";
    }

    @ApiOperation("상품리스트 노출")
    @GetMapping(value = "/product/board")
    public List<Product> BoardList(Model model){
        List<Product> productList = productService.Boardlist();
        model.addAttribute("list",productList);

        return productList;
    }

    @ApiOperation("상품 삭제")
    @DeleteMapping("/api/product/delete/{id}")
    public String BoardDelete(@PathVariable Integer id){

        String deleteBoard = productService.deleteList(id);

        return deleteBoard;
    }


}
