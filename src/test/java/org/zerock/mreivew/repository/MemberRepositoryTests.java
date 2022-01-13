package org.zerock.mreivew.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreivew.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r" + i + "@zerock.org")
                    .password("1111")
                    .nickname("reviewer" + i)
                    .build();
            this.memberRepository.save(member);
        });
    }

    @Test
    @Transactional
    @Commit
    void testDeleteMember() {
        // given
        Long mid = 2L;
        Member member = Member.builder().mid(mid).build();
        // when
        this.reviewRepository.deleteByMember(member);
        this.memberRepository.delete(member);
        // then
        Assertions.assertThrows(RuntimeException.class, () -> this.memberRepository.findById(mid).orElseThrow(RuntimeException::new));
    }
}
