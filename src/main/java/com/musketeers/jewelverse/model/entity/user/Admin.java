package com.musketeers.jewelverse.model.entity.user;

import com.musketeers.jewelverse.model.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "department")
    private String department;

    // Custom constructor to handle the specific fields and set the role
    public Admin(String email, String password, String firstName, String lastName, String employeeId, String department) {
        super(email, password, firstName, lastName);
        this.employeeId = employeeId;
        this.department = department;
        this.setUserRole(UserRole.ADMIN); // Set the role explicitly
    }

    // A simpler custom constructor if department is optional during initial creation
    public Admin(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName);
        this.employeeId = employeeId;
        this.setUserRole(UserRole.ADMIN); // Set the role explicitly
    }
}