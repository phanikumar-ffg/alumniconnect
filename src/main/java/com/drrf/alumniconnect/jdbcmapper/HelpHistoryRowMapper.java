package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelpHistoryRowMapper implements RowMapper<HelpHistory> {

    @Override
    public HelpHistory mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        HelpHistory helpHistory=new HelpHistory();
        helpHistory.setStudentId(resultSet.getLong("STUDENT_ID"));
        helpHistory.setReason(resultSet.getString("REASON"));
        helpHistory.setDetails(resultSet.getString("DETAILS"));
        helpHistory.setCenterId(resultSet.getString("CENTER_ID"));
        helpHistory.setCreateDate(resultSet.getTimestamp("CREATE_TIMESTAMP"));
        return helpHistory;
    }

}
