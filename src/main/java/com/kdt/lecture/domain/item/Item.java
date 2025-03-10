package com.kdt.lecture.domain.item;


import com.kdt.lecture.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    public void addStock(int count) {
        this.stockQuantity += count;
    }

    public void removeStock(int count) {
        if ((this.stockQuantity - count) < 0) {
            throw new RuntimeException("재고 부족");
        }
        this.stockQuantity -= count;
    }

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
