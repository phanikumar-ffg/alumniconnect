package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.model.CityDetails;
import com.drrf.alumniconnect.model.StateDetails;

import java.util.List;

public interface CityDetailsDao {
    public List<CityDetails> getCityDetails(Long stateId) throws CityDetailsDaoException;
    public List<StateDetails> getStateDetails() throws CityDetailsDaoException;
}
