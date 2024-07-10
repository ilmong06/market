package com.cod.market.member.entity;

import com.cod.market.base.BaseEntity;
import com.cod.market.qustion.entity.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.List;
import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
@Getter
@Setter
public class Member extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String isActive;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questionList;
}