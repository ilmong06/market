package com.cod.market.product.entity;

import com.cod.market.base.BaseEntity;
import com.cod.market.market.entity.Market;
import com.cod.market.question.entity.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
