package com.srs.service;

import java.util.List;

import com.srs.dto.ProductDto;
import com.srs.request.AddProduct;
import com.srs.request.UpdateProduct;

public interface ProductService {
	
	public ProductDto addProduct(AddProduct request);
	
	ProductDto getProductById(Integer productId);
	
	List<ProductDto> getAllProducts();
	
	ProductDto updateProduct(Integer productId, UpdateProduct request);
	
	void deleteProductById(Integer productId);

}
