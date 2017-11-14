package com.hadoopService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bigdata")
public class HadoopController {
	
	@Autowired
	private HadoopService hadoopService;

	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String setUser(@RequestBody UserPOJO user) throws IOException {
		this.hadoopService.setUser(user.getId(), user.getFirstName(),user.getLastName(), user.getEmail(), user.getGender(), user.getIpAddress());
		return "data added for"+user.getId();
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public UserPOJO getDataById(@PathVariable("id") String id) throws IOException {
		return this.hadoopService.getUser(id);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id) throws IOException {
		return this.hadoopService.delete(id);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public String updateUser(@RequestBody UserPOJO user) throws IOException {
		this.hadoopService.updateUser(user.getId(), user.getFirstName(),user.getLastName(), user.getEmail(), user.getGender(), user.getIpAddress());
		return "data updated for"+user.getId();
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public List<UserPOJO> getUsers() throws IOException {
		return this.hadoopService.getUsers();
	}
}
