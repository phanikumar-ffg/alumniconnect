package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class AdminHelpDaoImpl implements AdminHelpDao {
    private static final Logger logger = LoggerFactory.getLogger(AdminHelpDaoImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

        @Override
        public List<Map<String, Object>> getAllRequests() throws AdminHelpRequestDaoException{
        try{
            List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

            String query = "Select aars.ASPIRANT_ID ,up.FIRST_NAME,aars.REASON,aars.DETAILS,aars.DESCRIPTION,up.PHONE,aars.STATUS from tbl_admin_help_request_status aars" +
                    " INNER JOIN tbl_profile_data up ON aars.ASPIRANT_ID=up.ASPIRANT_ID WHERE aars.STATUS='New' OR aars.STATUS='InProgress' ORDER BY aars.ASPIRANT_ID";

            List<AdminHelpRequests> result = jdbcTemplate.query(query, (rs, arg1) -> {
                UserProfile up = new UserProfile();
                up.setAspirantId(rs.getLong("ASPIRANT_ID"));
                up.setFirstName(rs.getString("FIRST_NAME"));
                up.setPhone(rs.getLong("PHONE"));
                AdminHelpRequestStatus ahrs=new AdminHelpRequestStatus();
                ahrs.setAspirantId(rs.getLong("ASPIRANT_ID"));
                ahrs.setReason(rs.getString("REASON"));
                ahrs.setDetails(rs.getString("DETAILS"));
                ahrs.setDescription(rs.getString("DESCRIPTION"));
                ahrs.setStatus(rs.getString("STATUS"));
                return new AdminHelpRequests(up,ahrs);
            });
            result.forEach(r -> {
                Map<String, Object> map = new HashMap<String, Object>();
                UserProfile userProfile = r.getUserProfile();
                AdminHelpRequestStatus adminHelpRequestStatus=r.getAdminHelpRequestStatus();
                map.put("Student_Id", adminHelpRequestStatus.getAspirantId());
                map.put("Name", userProfile.getFirstName());
                map.put("Problem_Type", adminHelpRequestStatus.getReason());
                map.put("Problem_description", adminHelpRequestStatus.getDetails());
                map.put("Other_Details",adminHelpRequestStatus.getDescription());
                map.put("Phone_No", userProfile.getPhone());
                map.put("Request_Status",adminHelpRequestStatus.getStatus());
                listmap.add(map);
            });
//        JdbcMapper<AdminHelpRequests> jdbcMapper =
//                JdbcMapperFactory
//                        .newInstance()
//                        .addKeys("hh.STUDENT_ID","up.STUDENT_ID" )
//                        .newMapper(AdminHelpRequests.class);
            logger.info("Got new Admin Help Requests successfully ");
            return listmap;

        }
    catch(Exception e){
        logger.error("Error in getting request details",e.getLocalizedMessage());
        throw e;
    }
    }


    @Override
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) {
    try {
        long id = adminHelpRequestStatus.getAspirantId();
        String Status = adminHelpRequestStatus.getStatus();
        Date date = new Date();
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
        if (Status.equals("New")) {
            String st = "UPDATE  TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE ASPIRANT_ID='" + id + "'";
            jdbcTemplate.update(st, "InProgress",ts);
            System.out.print("STATUS FROM NEW TO IN PROGRESS");
        }
        if (Status.equals("InProgress")) {
            String st = "UPDATE TBL_ADMIN_HELP_REQUEST_STATUS SET STATUS=?,CREATE_TIMESTAMP=? WHERE ASPIRANT_ID='" + id + "'";
            jdbcTemplate.update(st,"Attended",ts);
            System.out.print("STATUS FROM INPROGRESS TO ATTENDED");

        }
        logger.info("Admin HelpRequest successfully updated");
        return "Status Updated";
    }
    catch(Exception e){
        logger.error("Error in updating record",e.getLocalizedMessage());
        throw e;
    }
    }
}
