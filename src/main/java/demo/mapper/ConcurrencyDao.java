package demo.mapper;

import org.springframework.stereotype.Repository;


@Repository("concurrencyDao")
public interface ConcurrencyDao {
	
	public Long getBatchNumber();
	
	public void updateIncrementBatchNumber();

}
