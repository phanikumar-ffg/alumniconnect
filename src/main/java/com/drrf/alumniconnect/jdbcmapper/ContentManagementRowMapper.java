package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentManagementRowMapper implements RowMapper<ContentManagement> {


    @Override
    public ContentManagement mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        ContentManagement contentManagement = new ContentManagement();

        contentManagement.setContentId(resultSet.getLong("CONTENT_ID"));
        contentManagement.setContentURL(resultSet.getString("CONTENT_URL"));
        contentManagement.setContentType(resultSet.getString("CONTENT_TYPE"));
        contentManagement.setContentDesc(resultSet.getString("CONTENT_DESC"));
        contentManagement.setAssessmentURL(resultSet.getString("ASSESSMENT_URL"));
        contentManagement.setCreateDate(resultSet.getTimestamp("CREATE_TIMESTAMP"));



        return contentManagement;
    }
}
