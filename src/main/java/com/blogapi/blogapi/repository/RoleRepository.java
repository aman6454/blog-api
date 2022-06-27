package com.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
