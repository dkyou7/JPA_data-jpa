package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;
import study.datajpa.entity.MemberDto;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>  {
    List<Member> findByNameAndAgeGreaterThan(String name, int age);

    List<Member> findAsdasdoasdasdBy();

    List<Member> findTop2By();

    @Query("select m from Member m where m.name = :name and m.age = :age")
    List<Member> findUser(@Param("name") String username, @Param("age")int age);

    @Query("select m.name from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.entity.MemberDto(m.id, m.name, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
