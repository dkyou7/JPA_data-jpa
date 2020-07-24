package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired MemberRepository repository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = repository.save(member);

        Optional<Member> byId = repository.findById(savedMember.getId());
        Member findMember = byId.get();

        Assertions.assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    public void findByUsernameAnd(){
        Member m1 = new Member("user",10);
        Member m2 = new Member("user",20);
        repository.save(m1);
        repository.save(m2);

        List<Member> result = repository.findByNameAndAgeGreaterThan("user",15);
        assertThat(result.get(0).getName()).isEqualTo("user");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.get(0)).isEqualTo(m2);
    }
    @Test
    public void find_대문자_어쩌구저쩌구By_하면_전체조회가된다(){
        List<Member> result = repository.findAsdasdoasdasdBy();
    }

    @Test
    public void find_탑10뽑기(){
        List<Member> result = repository.findTop10By();
    }
}