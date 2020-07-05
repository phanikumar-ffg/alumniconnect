package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.CityDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDetailsRowMapper implements RowMapper<CityDetails> {
    @Override
    public CityDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        CityDetails cityDetails = new CityDetails();
        cityDetails.setCityId(resultSet.getLong("city_id"));
        cityDetails.setCityName(resultSet.getString("city_name"));
        cityDetails.setStateId(resultSet.getLong("state_id"));
        return cityDetails;
    }
}
