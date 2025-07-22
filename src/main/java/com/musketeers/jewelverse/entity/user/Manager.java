package com.musketeers.jewelverse.entity.user;

import com.musketeers.jewelverse.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Manager extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "department")
    private String department;

    // Constructors
    public Manager() {
        super();
        setRole(UserRole.MANAGER);
    }

    public Manager(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName, UserRole.MANAGER);
        this.employeeId = employeeId;
    }

}
