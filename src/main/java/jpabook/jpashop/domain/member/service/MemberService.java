package jpabook.jpashop.domain.member.service;

import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO:: pagination 구현
@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) throws Exception {
        return memberRepository.findById(id)
                .orElseThrow(() -> new Exception("[MemberService.findById] 엔티티가 없습니다."));
    }

    public List<Member> findByName(String memberName){
        return memberRepository.findByName(memberName);
    }
}
