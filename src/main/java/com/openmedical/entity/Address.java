package com.openmedical.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Address  extends BaseEntity {

    private static final long serialVersionUID = 6466581233410620476L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false,updatable = false)
    private long id;
    @Column(name = "FLAT_NUMBER",length = 10)
    private String flatNumber;
    @Column(name = "HOUSE_NUMBER",nullable = false,length = 10)
    private String houseNumber;
    @Column(name = "STREET_NAME",nullable = false,length = 60)
    private String streetName;
    @Column(name = "POST_CODE",nullable = false,length = 10)
    private String postCode;
    @Column(name = "CITY",nullable = false,length = 50)
    private String city;
    @Column(name = "COUNTRY",nullable = false,length = 60)
    private String country;


}
