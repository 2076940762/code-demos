package services;

import java.sql.SQLException;
import java.util.List;

import doa.CityDoa;
import domain.City;

public class CityService
    {

        public List<City> findCityiesBypid(int pid) throws SQLException {
            return new CityDoa().findCityiesBypid(pid);
        }

    }
