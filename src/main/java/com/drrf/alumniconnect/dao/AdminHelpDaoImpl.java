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

        String query = "Select hh.ASPIRANT_ID ,up.FIRST_NAME,hh.REASON,hh.DETAILS,up.PHONE,aars.STATUS from alumniconnect.tbl_help_history hh" +
                " INNER JOIN alumniconnect.tbl_profile_data up ON hh.ASPIRANT_ID=up.ASPIRANT_ID" +
                " INNER JOIN alumniconnect.tbl_admin_help_request_status aars ON up.ASPIRANT_ID=aars.ASPIRANT_ID ORDER BY hh.ASPIRANT_ID";
//          String query="select * from drf.tbl_help_history";

        List<AdminHelpRequests> result = jdbcTemplate.query(query, (rs, arg1) -> {
            HelpHistory hh = new HelpHistory();
            hh.setAspirantId(rs.getLong("ASPIRANT_ID"));
            hh.setReason(rs.getString("REASON"));
            hh.setDetails(rs.getString("DETAILS"));
            UserProfile up = new UserProfile();
            up.setAspirantId(rs.getLong("ASPIRANT_ID"));
            up.setFirstName(rs.getString("FIRST_NAME"));
            up.setPhone(rs.getLong("PHONE"));
            AdminHelpRequestStatus ahrs=new AdminHelpRequestStatus();
            ahrs.setStudentId(rs.getLong("ASPIRANT_ID"));
            ahrs.setStatus(rs.getString("STATUS"));
            return new AdminHelpRequests(hh, up,ahrs);
        });
        result.forEach(r -> {
            Map<String, Object> map = new HashMap<String, Object>();
            HelpHistory helpHistory = r.getHelpHistory();
            UserProfile userProfile = r.getUserProfile();
            AdminHelpRequestStatus adminHelpRequestStatus=r.getAdminHelpRequestStatus();
            map.put("Student_Id", helpHistory.getAspirantId());
            map.put("Name", userProfile.getFirstName());
            map.put("Problem_Type", helpHistory.getReason());
            map.put("Problem_description", helpHistory.getDetails());
            map.put("Phone_No", userProfile.getPhone());
            map.put("Request_Status",adminHelpRequestStatus.getStatus());
            listmap.add(map);
        });
//        JdbcMapper<AdminHelpRequests> jdbcMapper =
//                JdbcMapperFactory
//                        .newInstance()
//                        .addKeys("hh.STUDENT_ID","up.STUDENT_ID" )
//                        .newMapper(AdminHelpRequests.class);
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
            String st = "Update DRF.TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE ASPIRANT_ID=?";
            jdbcTemplate.update(st,"InProgress",ts,id);
            System.out.print("STATUS FROM NEW TO IN PROGRESS");
        }
        if(Status.equals("InProgress")){
            String st = "Update DRF.TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE ASPIRANT_ID=?";
            jdbcTemplate.update(st,"Attended",ts,id);
            System.out.print("STATUS FROM INPROGRESS TO ATTENDED");

        }

        return "Status Updated";
    }
}
