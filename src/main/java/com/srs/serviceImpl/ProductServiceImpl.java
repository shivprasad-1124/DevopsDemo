package com.srs.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.srs.dto.ProductDto;
import com.srs.entity.Product;
import com.srs.exception.AppException;
import com.srs.repo.ProductRepository;
import com.srs.request.AddProduct;
import com.srs.request.UpdateProduct;
import com.srs.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDto addProduct(AddProduct request) {
/*		Product p = new Product();
		p.setProductName(request.getProductName());
		p.setPrice(request.getPrice());
		p.setBrand(request.getBrand()); */
		Product p = mapper.map(request, Product.class);
		p=prepo.save(p);
		
/*      ProductDto dto = new ProductDto();
		dto.setProductId(p.getProductId());
		dto.setProductName(p.getProductName());
		dto.setPrice(p.getPrice());
		dto.setBrand(request.getBrand());
		return dto; */
		
		return mapper.map(p, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		 Product p = prepo.findById(productId).orElse(null);
		 if(p==null) {
			 throw new AppException("Product not found",HttpStatus.NOT_FOUND);
		 }
	/*	 ProductDto dto = new ProductDto();
		 dto.setProductId(p.getProductId());
		 dto.setProductName(p.getProductName());
		 dto.setPrice(p.getPrice());
		 dto.setBrand(p.getBrand());
		 return dto; */
		 
		 return mapper.map(p, ProductDto.class);
		 
	}

	@Override
	public List<ProductDto> getAllProducts() {
		
		List<Product> pList = prepo.findAll();
		
		if(pList==null&&pList.isEmpty()) {
			throw new AppException("No Products Found", HttpStatus.NOT_FOUND);
		}

		List<ProductDto> dtoList = pList.stream()
		        .map(product -> mapper.map(product, ProductDto.class))
		        .toList();
		return dtoList;
		
	/*	List<Product> pList = prepo.findAll();
		
		Function<Product, ProductDto> function = (p)->{
			ProductDto dto = new ProductDto();
			dto.setProductId(p.getProductId());
			dto.setProductName(p.getProductName());
			dto.setPrice(p.getPrice());
			dto.setBrand(p.getBrand());
			return dto;
		};
		
		List<ProductDto> dtoList = pList.stream().map(function).collect(Collectors.toList());
		return dtoList;   */
	}

	@Override
	public void deleteProductById(Integer productId) {
		Product p = prepo.findById(productId).orElseThrow(()->new AppException("Product Not Found!",HttpStatus.NOT_FOUND));
		prepo.deleteById(productId);	
	}

	@Override
	public ProductDto updateProduct(Integer productId, UpdateProduct request) {
		Product existingProduct = prepo.findById(productId).orElseThrow(()->new AppException("Product Updation not happened!",HttpStatus.NOT_FOUND));
	/*	existingProduct.setProductName(request.getProductName());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setBrand(request.getBrand());  
		Product afterUpdate = prepo.save(existingProduct); */
		
		mapper.map(request, existingProduct);
		Product afterUpdate = prepo.save(existingProduct);
		
	/*	ProductDto dto = new ProductDto();
		dto.setProductName(existingProduct.getProductName());
		dto.setPrice(existingProduct.getPrice());
		dto.setBrand(existingProduct.getBrand());
		return dto;   */
		
		return mapper.map(afterUpdate, ProductDto.class);
	}
	
	

}
