package com.sarakhman.Servise;

import com.sarakhman.Entity.Product;
import com.sarakhman.JDBS.JDBSProductDao;

import java.util.List;

public class ProductService {
    JDBSProductDao jdbsProductDao = new JDBSProductDao();

    public ProductService(JDBSProductDao jdbsProductDao) {
        this.jdbsProductDao = jdbsProductDao;
    }

    public void addProduct(Product product){
        jdbsProductDao.addProduct(product);
    }

    public void updateProduct(Product product){
        jdbsProductDao.updateProduct(product, product.getId());
    }

    public List<Product> getAllProducts(){
        List<Product> allProducts = jdbsProductDao.getAllProducts();
        return allProducts;
    }
}
