package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.HelpDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelpDetailsRowMapper implements RowMapper<HelpDetails> {
    @Override
    public HelpDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        HelpDetails helpDetails=new HelpDetails();
        helpDetails.setHelpId(resultSet.getLong("help_id"));
        helpDetails.setHelpType(resultSet.getString("help_type"));
        helpDetails.setHelpValue(resultSet.getString("help_value"));
        helpDetails.setCreateDate(resultSet.getTimestamp("create_timestamp"));

        return helpDetails;
    }

}
