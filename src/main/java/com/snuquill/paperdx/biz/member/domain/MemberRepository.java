package com.snuquill.paperdx.biz.member.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findMembersByRetired(boolean retired);
    Member findMemberById(Long id);
}
