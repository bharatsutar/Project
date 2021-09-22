package com.dao;

import java.util.List;

import com.model.Batch;


public interface BatchDao {
	
	List<Batch> getAllBatchs();
	int addBatch(Batch batch);

}
