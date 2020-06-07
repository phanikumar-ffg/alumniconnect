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
        cityDetails.setCity(resultSet.getString("city"));
        cityDetails.setState(resultSet.getString("state"));
        cityDetails.setCreateDate(resultSet.getTimestamp("create_timestamp"));
        return cityDetails;
    }
}
