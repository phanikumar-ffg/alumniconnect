package com.drrf.alumniconnect.jdbcmapper;


import com.drrf.alumniconnect.model.StateDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDetailsRowMapper implements RowMapper<StateDetails> {
    @Override
    public StateDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        StateDetails stateDetails = new StateDetails();
        stateDetails.setStateId(resultSet.getLong("state_id"));
        stateDetails.setStateName(resultSet.getString("state_name"));
        return stateDetails;
    }
}
