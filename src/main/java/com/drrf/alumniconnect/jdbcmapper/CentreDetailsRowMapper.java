package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.CentreDetails;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CentreDetailsRowMapper implements RowMapper<CentreDetails>{
    @Override
    public CentreDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        CentreDetails centres = new CentreDetails();

        centres.setCentreId(resultSet.getLong("CENTRE_ID"));
        centres.setCentreName(resultSet.getString("CENTRE_NAME"));

        return centres;
    }

}
