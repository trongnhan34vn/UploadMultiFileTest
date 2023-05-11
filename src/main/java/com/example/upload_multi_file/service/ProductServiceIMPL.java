package com.example.upload_multi_file.service;

import com.example.upload_multi_file.config.ConnectionDB;
import com.example.upload_multi_file.model.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService{
    IImageService imageService = new ImageServiceIMPL();
    private static final String QUERY_FIND_ALL = "SELECT * FROM Product";
    private static final String QUERY_INSERT = "{CALL createProduct()}";
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        List<Product> productList = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_FIND_ALL);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setDescription(resultSet.getString("Description"));
                product.setListImgs(imageService.findImagesByProductId(product.getId()));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return productList;
    }

    @Override
    public void save(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callableStatement = conn.prepareCall(QUERY_INSERT);
            callableStatement.setString(1, product.getName());
            callableStatement.setString(2, product.getDescription());
            callableStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
