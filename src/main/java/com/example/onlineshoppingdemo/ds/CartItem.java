package com.example.onlineshoppingdemo.ds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    @ManyToOne
    private ApplicationUser user;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "cartItemDetaisl")
    private List<CartItemDetail> cartItemDetails = new ArrayList<>();
}
