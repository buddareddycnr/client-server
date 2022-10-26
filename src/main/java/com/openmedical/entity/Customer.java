package com.openmedical.entity;

import com.openmedical.server.constants.Gender;
import com.openmedical.server.constants.Status;
import com.openmedical.server.constants.Title;
import com.openmedical.server.converters.GenderConverter;
import com.openmedical.server.converters.StatusConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@DynamicUpdate
public class Customer extends BaseEntity {
    private static final long serialVersionUID = 5625659246522118952L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false)
    private Long id;
    @Column(name = "TITLE",length = 20,nullable = false)
    private Title title;
    @Column(name = "first_name",nullable = false,length = 60)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 60)
    private String lastName;
    @Column(name = "GENDER",nullable = false,length = 20)
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    @Column(name = "EMAIL_ID",length = 100,nullable = false)
    private String emailId;
    @Column(name = "MOBILE_NUMBER",nullable = false,length = 13)
    private String mobileNumber;
    @Convert(converter = StatusConverter.class)
    @Column(name = "STATUS",nullable = false,length = 20)
    private Status status = Status.CREATED;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Address.class)
    @JoinColumn(name = "CUSTOMER_ID",referencedColumnName = "id")
    private Set<Address> address;

}
