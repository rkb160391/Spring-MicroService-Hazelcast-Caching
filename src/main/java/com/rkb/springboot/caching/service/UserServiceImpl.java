package com.rkb.springboot.caching.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rkb.springboot.caching.entity.User;
import com.rkb.springboot.caching.repo.UserRepo;

@Service
public class UserServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepo userRepo;

	public String save() {
		long start = System.currentTimeMillis();
		List<User> users = new ArrayList<User>();
		for (int count = 0; count < 50000; count++) {
			User user = new User();
			user.setFirstName("FirstName" + count + 1);
			user.setLastName("LastName" + count + 1);
			user.setAddress("Address" + count + 1);
			users.add(user);
		}
		userRepo.saveAll(users);
		long end = System.currentTimeMillis();
		return "10K Records inserted successfully: " + "StartTime :" + start + "  EndTime: " + end;
	}

	@Cacheable(cacheNames = { "userCache" })
	public List<User> findAll() {
		LOGGER.info("1st time hitting the db");
		return userRepo.findAll();
	}

	@Cacheable(cacheNames = { "userCache" }, key = "#id", unless = "#result==null")
	public User findOneById(int id) {
		return userRepo.findOneById(id);
	}

	@CacheEvict(cacheNames = { "userCache" })
	public String delete(int id) {
		userRepo.deleteById(id);
		return "Deleted successfully";
	}

	@CacheEvict(cacheNames = { "userCache" })
	public void deleteAll() {
		userRepo.deleteAll();
	}

}
