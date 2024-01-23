package com.kuso.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuso.entity.Member;
import com.kuso.repository.MemberRepository;

@Service
public class KusoServiceImpl{
	@Autowired
	private MemberRepository memberRepository;
	
	public Member query(String account){
		Optional<Member> memberOptional = memberRepository.findById(account);
		
		if(!memberOptional.isPresent()){
			return null;
		}
		
		Member member = memberOptional.get();
		
		return member;
	}

}
