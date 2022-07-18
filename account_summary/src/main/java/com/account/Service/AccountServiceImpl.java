package com.account.Service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.Model.Response.AccountDetails;
import com.account.Model.Response.AccountSummary;
import com.account.Model.Response.Customer;
import com.account.Model.Reuest.Account;
import com.account.Repository.AccountRepository;

@Service
public class AccountServiceImpl {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	AccountRepository customerRepository;

	@Value("${customer_details_byid}")
	private String customer_by_id_url;

	public Account saveCustomer(Account customer) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		String requiredDate = df.format(new Date());
		customer.setDate(requiredDate);
		customerRepository.save(customer);
		return customer;
	}

	public List<Account> allCustomer() {
		List<Account> customerList = customerRepository.findAll();
		return customerList;

	}
	public Account updateCustomer(String id, Account customer) {
	       Optional<Account> customerupdate=customerRepository.findById(id);
	        Account cust=customerupdate.get();
	        cust.setAccType(customer.getAccType());
	        cust.setBalance(customer.getBalance());
	        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
			String requiredDate = df.format(new Date());
	        cust.setDate(requiredDate);
	        customerRepository.save(cust);
	        return cust;
		}

	public String deleteById(String id) {
		customerRepository.deleteById(id);
		return "customer deleted successFully";
	}

	public AccountSummary findAccount(String account_id) {
		Optional<Account> acc=customerRepository.findById(account_id);
		Account acc1=acc.get();
		HttpHeaders header=new HttpHeaders();
	   HttpEntity<Customer> entity=new HttpEntity<Customer>(header);
	   Map<String, String> vars = new HashMap<>();
	   vars.put("account",account_id);
	   ResponseEntity<Customer> cus=restTemplate.exchange(customer_by_id_url,HttpMethod.GET,entity,Customer.class,vars);
	   Customer customer=cus.getBody();
	   System.out.println("customer body  "+customer.toString());
	   AccountSummary accSummary=new AccountSummary();
	   AccountDetails accDetails=new AccountDetails();
	   accDetails.setAccount(acc1.getAccount());
	   accDetails.setAccType(acc1.getAccType());
	   accDetails.setBalance(acc1.getBalance());
	   accDetails.setDate(acc1.getDate());
	   accSummary.setAccountDetails(accDetails);
	   accSummary.setCustomer(customer);
	   return accSummary;
	}


}
