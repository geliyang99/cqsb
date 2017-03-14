package cqsb.sycj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqsb.sycj.dao.CustomerDao;
import cqsb.sycj.domain.Customer;
import cqsb.sycj.service.CustomerService;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	public Customer findCustomerById(String id){
		customerDao.find(id);
		return null;
	}
	
}
