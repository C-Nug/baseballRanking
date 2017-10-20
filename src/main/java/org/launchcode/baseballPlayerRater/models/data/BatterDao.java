package org.launchcode.baseballPlayerRater.models.data;

import org.launchcode.baseballPlayerRater.models.Batter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by CNUG on 8/1/17.
 */
@Repository
@Transactional
public interface BatterDao extends CrudRepository<Batter, Integer>{


}
