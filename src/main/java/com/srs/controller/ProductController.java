package com.srs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srs.dto.ProductDto;
import com.srs.request.AddProduct;
import com.srs.request.UpdateProduct;
import com.srs.response.ApiResponse;
import com.srs.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody AddProduct request){
		ProductDto dto=pservice.addProduct(request);
		return ResponseEntity.ok(new ApiResponse<>("Product Added Successfully",dto,HttpStatus.OK));
	}
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getProductId(@PathVariable Integer productId){
		ProductDto dto = pservice.getProductById(productId);
		return ResponseEntity.ok(new ApiResponse<>("Product Info:",dto,HttpStatus.OK));
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<?> getAllProducts(){
		List<ProductDto> dtoList = pservice.getAllProducts();
		return ResponseEntity.ok(new ApiResponse<>("All Produts:",dtoList,HttpStatus.OK));
	}
	
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody UpdateProduct request){
		ProductDto dto = pservice.updateProduct(productId, request);
		return ResponseEntity.ok(new ApiResponse<>("Product Updated Successfully",dto,HttpStatus.OK));
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable Integer productId){
		pservice.deleteProductById(productId);
		return ResponseEntity.ok(new ApiResponse<>("Product Deleted sucessfully!",null,HttpStatus.OK));
	}
	

}
