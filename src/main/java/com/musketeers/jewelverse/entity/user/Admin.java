package com.musketeers.jewelverse.entity.user;

import com.musketeers.jewelverse.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "department")
    private String department;

    // Custom constructor to handle the specific fields and set the role
    public Admin(String email, String password, String firstName, String lastName, String employeeId, String department) {
        super(email, password, firstName, lastName, UserRole.ADMIN);
        this.employeeId = employeeId;
        this.department = department;
    }

    // A simpler custom constructor if department is optional during initial creation
    public Admin(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName, UserRole.ADMIN);
        this.employeeId = employeeId;
    }
}