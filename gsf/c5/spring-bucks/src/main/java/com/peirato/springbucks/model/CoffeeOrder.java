package com.peirato.springbucks.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Yang zeqi
 * @date 2019/11/8
 * @description:
 */
@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
