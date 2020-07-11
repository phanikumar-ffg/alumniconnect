package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.jdbcmapper.AdminHelpStatusRowMapper;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Component
public class AdminHelpDaoImpl implements AdminHelpDao {
    private static final Logger logger = LoggerFactory.getLogger(AdminHelpDaoImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public List<Map<String, Object>> getAllRequests() {

        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

       /* String query = "Select hh.STUDENT_ID ,up.FIRST_NAME,hh.REASON,hh.DETAILS,up.MOBILE_INT,aars.STATUS from drf.tbl_help_history hh" +
                " INNER JOIN drf.tbl_profile_data up ON hh.STUDENT_ID=up.STUDENT_ID" +
                " INNER JOIN drf.tbl_admin_help_request_status aars ON up.STUDENT_ID=aars.STUDENT_ID ORDER BY hh.STUDENT_ID";
//          String query="select * from drf.tbl_help_history";

        List<AdminHelpRequests> result = jdbcTemplate.query(query, (rs, arg1) -> {
            HelpHistory hh = new HelpHistory();
          *//*  hh.setStudentId(rs.getLong("STUDENT_ID"));
            hh.setReason(rs.getString("REASON"));
            hh.setDetails(rs.getString("DETAILS"));
            UserProfile up = new UserProfile();
            up.setStudentId(rs.getLong("STUDENT_ID"));
            up.setFirstName(rs.getString("FIRST_NAME"));
            up.setMobile(rs.getLong("MOBILE_INT"));*//*
            AdminHelpRequestStatus ahrs=new AdminHelpRequestStatus();
            ahrs.setStudentId(rs.getLong("STUDENT_ID"));
            ahrs.setStatus(rs.getString("STATUS"));
            return new AdminHelpRequests(hh,ahrs);
        });
        result.forEach(r -> {
            Map<String, Object> map = new HashMap<String, Object>();
            HelpHistory helpHistory = r.getHelpHistory();
            UserProfile userProfile = r.getUserProfile();
            AdminHelpRequestStatus adminHelpRequestStatus=r.getAdminHelpRequestStatus();
            map.put("Student_Id", helpHistory.getStudentId());
            map.put("Name", userProfile.getFirstName());
            map.put("Problem_Type", helpHistory.getReason());
            map.put("Problem_description", helpHistory.getDetails());
            map.put("Phone_No", userProfile.getMobile());
            map.put("Request_Status",adminHelpRequestStatus.getStatus());
            listmap.add(map);
        });
//        JdbcMapper<AdminHelpRequests> jdbcMapper =
//                JdbcMapperFactory
//                        .newInstance()
//                        .addKeys("hh.STUDENT_ID","up.STUDENT_ID" )
//                        .newMapper(AdminHelpRequests.class);*/
        return listmap;
    }


    @Override
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) {

        long id=adminHelpRequestStatus.getStudentId();
        String Status = adminHelpRequestStatus.getStatus();
        Date date=new Date();
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
        if (Status.equals("New")) {
            String st = "Update DRF.TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE STUDENT_ID=?";
            jdbcTemplate.update(st,"InProgress",ts,id);
            System.out.print("STATUS FROM NEW TO IN PROGRESS");
        }
        if(Status.equals("InProgress")){
            String st = "Update DRF.TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE STUDENT_ID=?";
            jdbcTemplate.update(st,"Attended",ts,id);
            System.out.print("STATUS FROM INPROGRESS TO ATTENDED");

        }

        return "Status Updated";
    }
}
