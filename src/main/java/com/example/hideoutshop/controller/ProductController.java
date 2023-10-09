package com.example.hideoutshop.controller;

import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.Option.Options;
import com.example.hideoutshop.repository.Product.Product;
import com.example.hideoutshop.service.ProductService;
import com.example.hideoutshop.web.dto.ProductDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @ApiOperation("상품등록")
    @PostMapping(value = "/api/product/setting")
    public String setting(@ModelAttribute ProductDTO product, @ModelAttribute Options option, MultipartFile file, @RequestHeader("Authorization") String accessToken) throws Exception {
        String userid = null;
        Boolean isSuccess = false;
        if (accessToken.startsWith("Bearer")) {
            String Token = accessToken.replace("Bearer", " ");
            userid = jwtTokenProvider.getUserId(Token);
            System.out.println("아이디는 !!" + userid);
            System.out.println("상품저보 !!" + product.toString());
            System.out.println("옵션 !!" + option);
            System.out.println("file !!" + file);
             isSuccess = productService.save(product, option, file, userid);
        }else{
             isSuccess = false;
        }
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

    @ApiOperation("상품 구매")
    @PostMapping("/api/product/buy")
    public String buy(){

        return "작성중";
    }

}
