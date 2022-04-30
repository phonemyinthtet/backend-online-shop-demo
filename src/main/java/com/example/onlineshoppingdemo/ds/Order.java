package com.example.onlineshoppingdemo.ds;



import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;
    @ManyToOne
    private ApplicationUser applicationUser;
    @OneToMany
    @JoinColumn(name = "order_detail_id")
    private List<OrderProductDetail> orderProductDetails = new ArrayList<>();

}
