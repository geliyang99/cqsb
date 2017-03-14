package cqsb.sycj.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import cqsb.sycj.dao.CustomerDao;
import cqsb.sycj.domain.Customer;
import cqsb.tech.common.basedao.AbstractDAO;
import cqsb.tech.common.page.Page;

@Repository("CustomerDao")
public class CustomerDaoImpl extends AbstractDAO<Customer> implements CustomerDao{


	@Override
	public Page<Customer> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getScrollData(int firstindex, int maxresult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getPageData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getPageData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getPageData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> getPageData(int firstindex, int maxresult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllData(String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllData(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
