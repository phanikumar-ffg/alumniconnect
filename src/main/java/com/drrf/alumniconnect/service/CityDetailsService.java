package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.model.CityDetails;
import com.drrf.alumniconnect.model.StateDetails;



import java.util.List;


public interface CityDetailsService {
   public List<StateDetails> getStateDetails() throws CityDetailsDaoException;
   public List<CityDetails> getCityDetails(Long stateId) throws CityDetailsDaoException;
}
