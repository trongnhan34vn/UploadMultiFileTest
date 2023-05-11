package com.example.upload_multi_file.service;

import com.example.upload_multi_file.config.ConnectionDB;
import com.example.upload_multi_file.model.Image;
import com.example.upload_multi_file.model.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceIMPL implements IImageService{
    private static final String QUERY_FIND_ALL = "SELECT * FROM Image";
    private static final String QUERY_INSERT = "{CALL createImage(?, ?)}";
    private static final String QUERY_FIND_BY_PRODUCT_ID = "{CALL findImagesByProductId()}";
    @Override
    public List<Image> findAll() {
        Connection conn = null;
        List<Image> imageList = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_FIND_ALL);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getInt("ImageID"));
                image.setUrl(resultSet.getString("Url"));
                image.setProductId(resultSet.getInt("ProductID"));
                imageList.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return imageList;
    }

    @Override
    public void save(Image image) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callableStatement = conn.prepareCall(QUERY_INSERT);
            callableStatement.setString(1, image.getUrl());
            callableStatement.setInt(2, image.getProductId());
            callableStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Image> findImagesByProductId(int pId) {
        List<Image> imageList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callableStatement = conn.prepareCall(QUERY_FIND_BY_PRODUCT_ID);
            callableStatement.setInt(1, pId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getInt("ImageID"));
                image.setUrl(resultSet.getString("Url"));
                image.setProductId(resultSet.getInt("ProductID"));
                imageList.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return imageList;
    }
}
