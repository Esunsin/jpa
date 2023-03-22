package jpabook1.jpashop1.service;

import jpabook1.jpashop1.domain.Member;
import jpabook1.jpashop1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional //test에 사용시 rollback 함
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //give
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }
    @Test
    public void 중복_회원_예외 () throws Exception{
        //give
        Member member1 = new Member();
        member1.setName("jeong");
        Member member2 = new Member();
        member2.setName("jeong");
        //when
        memberService.join(member1);
        IllegalStateException message = assertThrows(IllegalStateException.class, () ->
                memberService.join(member2));
        //then
        assertEquals("중복 회원 입니다.",message.getMessage());
    }

}