package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.CityDetailsDao;
import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.model.CityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityDetailsServiceImpl implements CityDetailsService{
    @Autowired
    CityDetailsDao cityDetailsDao;
    @Override
    public List<CityDetails> getCityDetails() throws CityDetailsDaoException {
        return cityDetailsDao.getCityDetails();
    }
}
