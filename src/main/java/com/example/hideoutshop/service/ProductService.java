package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.list.Product;
import com.example.hideoutshop.repository.list.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public boolean save(Product product, MultipartFile file) throws Exception{

        String path = System.getProperty("user.dir")+"/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();
        String filename = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(path,filename);
        file.transferTo(saveFile);

        //파일이 없으면 입력 실패
        if(file.isEmpty()){
            return false;
        }

        Product productInfo = Product.builder()
                .productName(product.getProductName())
                .productContent(product.getProductContent())
                .productImg(filename)
                .productPath("/files/"+filename)
                .build();

        productRepository.save(productInfo);
        return true;
    }

    public List<Product> Boardlist(){
        return productRepository.findAll();
    }

}
