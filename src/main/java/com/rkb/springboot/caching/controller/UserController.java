package com.rkb.springboot.caching.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rkb.springboot.caching.entity.User;
import com.rkb.springboot.caching.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/save")
	public String save() {
		LOGGER.info("Service: /user/save");
		return userServiceImpl.save();
	}

	@GetMapping("/findAll")
	public List<User> findAll() {
		LOGGER.info("Service: /user/findAll");
		return userServiceImpl.findAll();
	}

	@GetMapping("/findOne/{id}")
	public User findOne(@PathVariable int id) {
		LOGGER.info("Service: /user/findOne");
		return userServiceImpl.findOneById(id);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userServiceImpl.delete(id);
		return "Successfully deleted";
	}

	@GetMapping("/deleteAll")
	public String deleteAll() {
		userServiceImpl.deleteAll();
		return "Successfully deleted";
	}

}
