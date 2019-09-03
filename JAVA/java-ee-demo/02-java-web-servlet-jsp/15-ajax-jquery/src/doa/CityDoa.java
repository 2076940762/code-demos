package doa;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.City;

public class CityDoa
    {

        public List<City> findCityiesBypid(int pid) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
            
            String sql = "SELECT * FROM city WHERE ProvinceID=?;";
            
            return qr.query(sql, new BeanListHandler<City>(City.class), pid);
        }

    }
