package edu.miu.cs545.store.service;

import edu.miu.cs545.store.domain.Product;
import edu.miu.cs545.store.dto.ProductDTO;
import edu.miu.cs545.store.exception.ProductNotFoundException;
import edu.miu.cs545.store.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<ProductDTO> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProduct(String productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(() -> new ProductNotFoundException(productNumber));
        return modelMapper.map(product, ProductDTO.class);
    }

    public void saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
    }

    public void deleteProduct(String productNumber) {
        productRepository.deleteById(productNumber);
    }
}
