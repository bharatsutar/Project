package com.service;

import java.awt.print.Book;
import java.util.List;

import com.model.Batch;



public interface BatchService {
	List<Batch> getAllBatchs();
	int addBatch(Batch batch);

}
