package jpabook.start;

import jpabook.model.entity.Member;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class JpaMain implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        logic(em);
    }

    public void logic(EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("fmoths");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(3);

        //한건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findmember = " + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("SELECT m FROM Member m",Member.class).getResultList();
        System.out.println("member.size = " + members.size());

        //삭제
        em.remove(member);
    }
}
