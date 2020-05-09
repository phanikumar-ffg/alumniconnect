package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.HelpHistoryDao;
import com.drrf.alumniconnect.exceptions.HelpHistoryDaoException;
import com.drrf.alumniconnect.model.HelpDetails;
import com.drrf.alumniconnect.model.HelpHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpHistoryServiceImpl implements HelpHistoryService{
    @Autowired
    HelpHistoryDao helpHistoryDao;

    @Override
    public String saveHelpDetails(HelpHistory helpHistory) throws HelpHistoryDaoException {
        helpHistoryDao.saveHelpDetails(helpHistory);
        return "success";
    }

    @Override
    public List<HelpDetails> getHelpDetails(String helpType) throws HelpHistoryDaoException {
        return helpHistoryDao.getHelpDetails(helpType);
    }
}
