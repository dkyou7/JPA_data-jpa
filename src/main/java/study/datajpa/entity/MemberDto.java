package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Data
public class MemberDto {

    private Long id;
    private String name;
    private String teamName;

    // Dto 조회 시, 그에 맞는 생성자가 필요하다.
    public MemberDto(Long id, String name, String teamName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
    }
}
