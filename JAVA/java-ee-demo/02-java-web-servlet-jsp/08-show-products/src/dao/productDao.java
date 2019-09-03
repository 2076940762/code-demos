package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.product;

public class productDao
    {

        public List<product> findAll() throws SQLException {
            //创建语句执行者
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            //sql
            String sql = "SELECT * FROM  products.product;";

            //执行
            List<product> list = qr.query(sql, new BeanListHandler<product>(product.class));

            return list;
        }

    }
