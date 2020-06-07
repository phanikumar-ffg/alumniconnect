package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.dao.CityDetailsDao;
import com.drrf.alumniconnect.jdbcmapper.CityDetailsRowMapper;
import com.drrf.alumniconnect.model.CityDetails;
import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CityDetailsDaoImpl implements CityDetailsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CityDetails> getCityDetails() throws CityDetailsDaoException {
        List<CityDetails> list=null;
        String message="";
        try {
            final String sql = "SELECT * FROM tbl_city_details";

           list = jdbcTemplate.query(sql,  new CityDetailsRowMapper());
            if(list.size()==0){
                throw  new CityDetailsDaoException(String.format("City Details not found"));
            }
            else {
                return list;
            }
        }catch (CityDetailsDaoException e)
        { throw e;}
    }
}
