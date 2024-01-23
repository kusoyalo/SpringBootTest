package com.kuso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuso.entity.Member;


public interface MemberRepository extends JpaRepository<Member,String>{

}
