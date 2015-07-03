package demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.mapper.ConcurrencyDao;
import demo.service.ConcurrencyService;

@Service("concurrencyService")
public class ConcurrencyServiceImpl implements ConcurrencyService {
	
	@Resource
	private ConcurrencyDao concurrencyDao;
	
	@Override
	public Long updateBatchThenReturn() {
		Long l = 0L ;
		for(int i=0; i<10 ; i++){
			this.concurrencyDao.updateIncrementBatchNumber();
			l = this.concurrencyDao.getBatchNumber();
		}
		return l;
	}

}
