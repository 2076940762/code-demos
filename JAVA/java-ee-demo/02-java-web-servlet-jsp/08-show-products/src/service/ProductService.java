package service;

import java.sql.SQLException;
import java.util.List;

import dao.productDao;
import domain.product;

public class ProductService
    {

        public List<product> findAll() throws SQLException {
            return new productDao().findAll();
        }

    }
