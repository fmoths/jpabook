package jpabook.jpashop.domain.member.service;


import jpabook.JpaBookApplication;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpaBookApplication.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입을 신청하면 유저의 데이터가 저장된다.")
    public void join() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(saveId));
    }

    @Test
    @DisplayName("중복회원이 저장되면 예외를 발생시킨다.")
    public void saveMemberFail() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when, then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member1);
            memberService.join(member2);
        });
    }
}