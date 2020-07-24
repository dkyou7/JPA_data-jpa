package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository repository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = repository.save(member);

        Member findMember = repository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember).isEqualTo(member);
    }
    @Test
    public void findByUsernameAnd(){
        Member m1 = new Member("user",10);
        Member m2 = new Member("user",20);
        repository.save(m1);
        repository.save(m2);

        List<Member> result = repository.findByUsernameAndAgeGreaterThan("user",15);
        assertThat(result.get(0).getName()).isEqualTo("user");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.get(0)).isEqualTo(m2);
    }
}