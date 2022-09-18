package com.vikash.kafkaconsumer.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student",catalog = "demo")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
       if(o == this)
           return true;
       if(o == null || o.getClass() != this.getClass())
           return false;
       Student student = (Student)o;
       return Objects.equals(student.phoneNumber, this.phoneNumber) && Objects.equals(student.id, this.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
