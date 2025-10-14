package com.poly.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String image;
    Double price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Createdate")
    Date createDate = new Date();
    Boolean available;
    @ManyToOne @JoinColumn(name = "Categoryid")
    Category category;
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
}
