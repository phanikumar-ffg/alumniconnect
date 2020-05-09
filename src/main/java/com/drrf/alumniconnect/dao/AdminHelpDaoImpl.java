package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.jdbcmapper.HelpHistoryRowMapper;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminHelpDaoImpl implements AdminHelpDao{
    private static final Logger logger = LoggerFactory.getLogger(AdminHelpDaoImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<AdminHelpRequests> getAllRequests(){
        String query="Select hh.STUDENT_ID as hid ,up.FIRST_NAME,hh.REASON,hh.DETAILS,up.MOBILE_INT from tbl_help_history hh"+
                " INNER JOIN tbl_profile_data up ON hh.STUDENT_ID=up.STUDENT_ID ORDER BY hh.STUDENT_ID";
//          String query="select * from drf.tbl_help_history";

//        List<AdminHelpRequests> result= jdbcTemplate.query(query,new BeanPropertyRowMapper(AdminHelpRequests.class));

        JdbcMapper<AdminHelpRequests> jdbcMapper =
                JdbcMapperFactory
                        .newInstance()
                        .addKeys("hh.STUDENT_ID","up.STUDENT_ID" )
                        .newMapper(AdminHelpRequests.class);

//        for(AdminHelpRequests r :jdbcMapper){
//            HelpHistory hh=r.getHelpHistory();
//            UserProfile up=r.getUserProfile();
//            System.out.println("HELP HISTORY"+hh);
//            System.out.println("User Profile"+up);
//
//        }
        return jdbcMapper;

    }
}
