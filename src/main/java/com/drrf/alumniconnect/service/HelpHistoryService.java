package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.HelpHistoryDaoException;
import com.drrf.alumniconnect.model.HelpDetails;
import com.drrf.alumniconnect.model.HelpHistory;

import java.util.List;

public interface HelpHistoryService {
    public String saveHelpDetails(HelpHistory helpHistory) throws HelpHistoryDaoException;
    public List<HelpDetails> getHelpDetails(String helpType) throws HelpHistoryDaoException;
}
