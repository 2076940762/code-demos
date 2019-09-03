package doa;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.Province;

public class ProvincesDoa
    {

        public List<Province> findAllProvinces() throws SQLException {

            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
            String sql = "SELECT * FROM province;";
            return qr.query(sql, new BeanListHandler<Province>(Province.class));
        }

    }
