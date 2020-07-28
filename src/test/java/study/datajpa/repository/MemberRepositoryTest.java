package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.MemberDto;
import study.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository repository;
    @Autowired TeamRepository teamRepository;

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
    public void find_탑2뽑기(){
        Member m1 = new Member("user",10);
        Member m2 = new Member("user",20);
        Member m3 = new Member("user",30);
        Member m4 = new Member("user",40);
        Member m5 = new Member("user",50);
        repository.save(m1);
        repository.save(m2);
        repository.save(m3);
        repository.save(m4);
        repository.save(m5);

        List<Member> result = repository.findTop2By();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void jpql로_테스트(){
        Member m1 = new Member("user",10);
        Member m2 = new Member("user",20);
        repository.save(m1);
        repository.save(m2);
        List<Member> result = repository.findUser("user",10);

        assertThat(m1).isEqualTo(result.get(0));
    }

    @Test
    public void 이름만_출력하도록(){
        Member m1 = new Member("user1",10);
        Member m2 = new Member("user2",20);
        repository.save(m1);
        repository.save(m2);
        List<String> result = repository.findUsernameList();

        for(String s : result){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void DTO로_조회가능하도록(){
        Member m1 = new Member("user1",10);
        Member m2 = new Member("user2",20);

        Team team = new Team("TeamA");
        teamRepository.save(team);
        m1.setTeam(team);
        m2.setTeam(team);

        repository.save(m1);
        repository.save(m2);

        List<MemberDto> memberDto = repository.findMemberDto();
        for(MemberDto m: memberDto){
            System.out.println("m.toString() = " + m.toString());
        }

    }
}