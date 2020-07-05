package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.HelpHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelpHistoryRowMapper implements RowMapper<HelpHistory> {
    @Override
    public HelpHistory mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        HelpHistory helpHistory=new HelpHistory();
        helpHistory.setAspirantId(resultSet.getLong("ASPIRANT_ID"));
        helpHistory.setReason(resultSet.getString("REASON"));
        helpHistory.setDetails(resultSet.getString("DETAILS"));
        helpHistory.setCenterId(resultSet.getString("CENTRE_ID"));
        helpHistory.setCreateDate(resultSet.getTimestamp("CREATE_TIMESTAMP"));
        helpHistory.setDescription(resultSet.getString("DESCRIPTION"));

        return helpHistory;
    }
}
