package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Select m from Member m where m.name =?
    List<Member> findByName(String name);
}
