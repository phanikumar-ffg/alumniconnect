package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.model.CityDetails;
import org.springframework.stereotype.Service;


import java.util.List;


public interface CityDetailsService {
   public List<CityDetails> getCityDetails() throws CityDetailsDaoException;
}
