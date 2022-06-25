package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private int id;

    @NotBlank(message = "{product.name.notblank}")
    private String name;

    @NotBlank(message = "{product.detail.notblank}")
    private String detail;

    private long price;
    private MultipartFile logo;

    private Category category;

}
