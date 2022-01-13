package org.zerock.mreivew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreivew.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
