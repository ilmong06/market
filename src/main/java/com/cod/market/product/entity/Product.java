package com.cod.market.product.entity;

import com.cod.market.base.BaseEntity;
import com.cod.market.market.entity.Market;
import com.cod.market.qustion.entity.Question;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    private String name;
    private int price;
    private String description;
    private int hitCount;
    private String isActive;

    @ManyToOne
    private Market market;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Question> questionList;
}
