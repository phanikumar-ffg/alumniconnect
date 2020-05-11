package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentManagementRowMapper implements RowMapper<ContentManagement> {


    @Override
    public ContentManagement mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        ContentManagement contentManagement = new ContentManagement();

        contentManagement.setContentId(resultSet.getLong("content_id"));
        contentManagement.setContentURL(resultSet.getString("content_url"));
        contentManagement.setContentTitle(resultSet.getString("content_title"));
        contentManagement.setContentDesc(resultSet.getString("content_desc"));
        contentManagement.setCreateDate(resultSet.getTimestamp("create_timestamp"));


        return contentManagement;
    }
}
