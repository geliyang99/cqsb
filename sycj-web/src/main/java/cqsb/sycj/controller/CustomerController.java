package cqsb.sycj.controller;

import org.springframework.beans.factory.annotation.Autowired;

import cqsb.sycj.domain.Customer;
import cqsb.sycj.service.CustomerService;

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	public String getCustomerListPage(String customerId){
		Customer customer = customerService.findCustomerById(customerId);
		System.out.println(customer.getName());
		return "welcome";
	}

}
