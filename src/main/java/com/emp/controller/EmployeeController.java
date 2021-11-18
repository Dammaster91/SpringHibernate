package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emp.bean.EmployeeDto;
import com.emp.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/empform", method = RequestMethod.GET)
	public String showform(Model m) {
		m.addAttribute("command", new EmployeeDto());
		return "empform";
	}

	/*
	 * It saves object into database. The @ModelAttribute puts request data into
	 * model object. You need to mention RequestMethod.POST method because
	 * default request is GET
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") EmployeeDto emp) {
		employeeService.save(emp);
		return "redirect:/viewemp";// will redirect to viewemp request mapping
	}

	// /* It provides list of employees in model object */
	@RequestMapping(value = "/viewemp", method = RequestMethod.GET)
	public String viewemp(Model m) {
		List<EmployeeDto> list = employeeService.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	/*
	 * It displays object data into form for the given id. The @PathVariable
	 * puts URL data into variable.
	 */
	@RequestMapping(value = "/editemp/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model m) {
		EmployeeDto emp = employeeService.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	/* It updates model object. */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") EmployeeDto emp) {
		employeeService.update(emp);
		return "redirect:/viewemp";
	}

	/* It deletes record for the given id in URL and redirects to /viewemp */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		employeeService.delete(id);
		return "redirect:/viewemp";
	}
}