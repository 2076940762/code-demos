package services;

import java.sql.SQLException;
import java.util.List;

import doa.ProvincesDoa;
import domain.Province;

public class ProvinceService
    {

        public List<Province> findAllProvinces() throws SQLException {
            return new ProvincesDoa().findAllProvinces();
        }

    }
