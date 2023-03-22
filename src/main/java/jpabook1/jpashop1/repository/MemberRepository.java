package jpabook1.jpashop1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook1.jpashop1.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Member member) {
        em.persist(member);
    }

    //단일 조회 : id -> Member
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    //전체 조회 : -> List<Member>
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    //단일 조회 : name -> List<Member> (동일인 포함)
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =:name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
