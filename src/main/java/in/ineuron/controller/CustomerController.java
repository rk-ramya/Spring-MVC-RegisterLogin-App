package in.ineuron.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ineuron.model.Customer;
import in.ineuron.model.Login;
import in.ineuron.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;

//	@GetMapping("/list")
//	public String listCustomers(Map<String, Object> model) {
//		System.out.println("Implementation class is :: " + service.getClass().getName());
//		List<Customer> customers = service.getCustomers();
//		customers.forEach(System.out::println);
//		model.put("customers", customers);
//		return "/customers/list-customers";
//	}

	
	@GetMapping("/home")
	public String listCustomers(Map<String, Object> model) {
		System.out.println("Implementation class is :: " + service.getClass().getName());
		return "/customers/home";
	}

	@GetMapping("/registerForm")
	public String showFormForAdd(Map<String, Object> model) {
		Customer customer = new Customer();
		model.put("customer", customer);
		return "/customers/customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		System.out.println(customer);
		service.saveCustomer(customer);
		return "redirect:/customer/home";
	}
	
	
	@GetMapping("/loginForm")
	public String showFormForLogin(Map<String, Object> model) {
		Login login = new Login();
		model.put("login", login);
		return "/customers/login-form";
	}
	
	@GetMapping("/failed-Login")
	public String showFormForFailedLogin(Map<String, Object> model) {
		Login login = new Login();
		model.put("login", login);
		return "/customers/failed-Login";
	}
	@GetMapping("/success-Login")
	public String showFormForSuccessLogin(Map<String, Object> model) {
		Login login = new Login();
		model.put("login", login);
		return "/customers/welcome";
	}
	
	@PostMapping("/validateUser")
	public String validateUser(@ModelAttribute("login") Login login) {
		Customer customer = service.getCustomer(login.getId());
		System.out.println("LOGIN ==>"+login);
		System.out.println("customer details =>" +customer);
		if((customer.getEmail()).equalsIgnoreCase(login.getEmail())
				&& (customer.getPassword()).equalsIgnoreCase(login.getPassword()))
			return "/customers/success-Login";
		
		return "/customers/failed-Login";
	}
	
	
//
//	@PostMapping("/showFormForUpdate")
//	public String showFormForUpdate(@RequestParam Integer customerId, Map<String, Object> model) {
//		Customer customer = service.getCustomer(customerId);
//		System.out.println(customer);
//
//		model.put("customer", customer);
//		return "/customers/customer-form";
//	}
//
//	@PostMapping("/showFormForDelete")
//	public String showFormForDelete(@RequestParam Integer customerId) {
//		service.deleteCustomer(customerId);
//		return "redirect:/customer/list";
//	}

}
