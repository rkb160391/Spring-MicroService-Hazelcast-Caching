package com.rkb.springboot.caching.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rkb.springboot.caching.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findOneById(int id);
}
