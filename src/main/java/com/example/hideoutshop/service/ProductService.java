package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.Option.OptionRepository;
import com.example.hideoutshop.repository.Option.Options;
import com.example.hideoutshop.repository.Product.Product;
import com.example.hideoutshop.repository.Product.ProductRepository;
import com.example.hideoutshop.service.exceptions.NotFoundException;
import com.example.hideoutshop.web.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    public boolean save(ProductDTO product, Options option, MultipartFile productImg , String userid) throws Exception{

        if(userid == ""){
            return false;
        }
        String path = System.getProperty("user.dir")+"/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();
        String filename = uuid+"_"+productImg.getOriginalFilename();
        File saveFile = new File(path,filename);
        productImg.transferTo(saveFile);

        //파일이 없으면 입력 실패
        if(productImg.isEmpty()){
            return false;
        }

        Product productInfo = Product.builder()
                .productName(product.getProductName())
                .productContent(product.getProductContent())
                .productPrice(product.getProductPrice())
                .productOptionShow(product.getProductOptionShow())
                .productImg(filename)
                .productWriter(userid)
                .productPath("/files/"+filename)
                .productWriter("writer")
                .build();

       Product productDTO =  productRepository.save(productInfo);

        Options optionInfo = Options.builder()
                .OptionPrice(10000)
                .OptionName("노랑")
                .ProductNo(productDTO.getProductNo())
                .build();
        optionRepository.save(optionInfo);

        return true;
    }

    public List<Product> Boardlist(){

        return productRepository.findAll();
    }

    public String deleteList(Integer id) {
        String status = "삭제되었습니다.";
        //받은 ListNo여부 체크
        Product product =  productRepository.findById(id).orElseThrow(()-> new NotFoundException("상품이 없습니다."));
        Integer ProductNo= product.getProductNo();

        try {
            // 이미지 파일의 경로를 문자열로 받아 Path 객체로 변환
            File file = new File(System.getProperty("user.dir")+"/src/main/resources/static/files",product.getProductImg());

            // 파일 삭제
            file.delete();
        } catch (Exception e) {
            // 파일 삭제 중 오류가 발생한 경우 처리
            e.printStackTrace();
            // 예외 처리를 할 수도 있습니다.
        }
        productRepository.deleteById(ProductNo);
        return status;
    }

    public List<Product> searchProducts(String keyword) {

        List<Product> searchResult = productRepository.findByProductNameContaining(keyword);

        return searchResult;

    }
}
