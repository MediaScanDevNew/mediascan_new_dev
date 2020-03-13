/**
 * 
 */
package com.pradeep.pj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.pradeep.pj.model.Infringing_source;
import com.pradeep.pj.repo.InfringingSourceRepository;

/**
 * @author pradeep
 *
 */
@Service
@Configurable
public class InfringingSourceRepositoryIMPL {

	/**
	 * 
	 */
	public InfringingSourceRepositoryIMPL() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private InfringingSourceRepository infringingSourceRepository;

	public void addData(Infringing_source infringing_source) {
		//infringingSourceRepository.save(infringing_source);
		new IWLDataProcess().addDataInfringing(infringing_source);
	}
	
//	public List<Object[]> findALLCustom(int uid,String date__) {
//		List<Object[]> allCustomRecords = new ArrayList<>();
//		
//		allCustomRecords = infringingSourceRepository.findAllCustomQuery(uid, date__);
//		
//		return allCustomRecords;
//	}
}
