package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.HelpHistory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminHelpStatusRowMapper implements RowMapper<AdminHelpRequestStatus>{
    @Override
    public AdminHelpRequestStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AdminHelpRequestStatus adminHelpRequestStatus=new AdminHelpRequestStatus();
        adminHelpRequestStatus.setStudentId(resultSet.getLong("STUDENT_ID"));
        adminHelpRequestStatus.setStatus(resultSet.getString("STATUS"));
        adminHelpRequestStatus.setCreatedTime(resultSet.getTimestamp("CREATE_TIMESTAMP"));
        return adminHelpRequestStatus;
}

}
