package com.drrf.alumniconnect.dao;


import com.drrf.alumniconnect.jdbcmapper.CityDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.StateDetailsRowMapper;
import com.drrf.alumniconnect.model.CityDetails;
import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.model.StateDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CityDetailsDaoImpl implements CityDetailsDao {
    private static final Logger logger = LoggerFactory.getLogger(CityDetailsDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CityDetails> getCityDetails(Long stateId) throws CityDetailsDaoException {
        List<CityDetails> list=null;
        try {
            final String sql = "SELECT * FROM alumniconnect.tbl_city_details where STATE_ID="+stateId;

           list = jdbcTemplate.query(sql,  new CityDetailsRowMapper());
            if(list.isEmpty()){
                throw  new CityDetailsDaoException("City Details not found");
            }
            else {
                logger.info("Details fetched successfully for City details dropdowns");
                return list;
            }
        }catch (CityDetailsDaoException e)
        {
            logger.error("Error occurred while fetching the information from city details table: ",e.getMessage());
            throw e;}
    }

    @Override
    public List<StateDetails> getStateDetails() throws CityDetailsDaoException {
        List<StateDetails> list = null;
        String message="";
        try {
            final String sql = "SELECT * FROM alumniconnect.tbl_state_details";
            list = jdbcTemplate.query(sql,  new StateDetailsRowMapper());
            if(list.isEmpty()){
                throw  new CityDetailsDaoException("State Details not found");
            }
            else {
                logger.info("Details fetched successfully for State Details dropdowns");
                return list;
            }
        }catch (CityDetailsDaoException e)
        { logger.error("Error occurred while fetching the information from State details table: ",e.getMessage());
            throw e;}
    }
}
