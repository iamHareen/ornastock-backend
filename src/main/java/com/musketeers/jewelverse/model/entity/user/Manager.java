package com.musketeers.jewelverse.model.entity.user;

import com.musketeers.jewelverse.model.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "department")
    private String department;

    // Constructor
    public Manager(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName);
        this.employeeId = employeeId;
        this.setUserRole(UserRole.MANAGER); // Set the role explicitly
    }

}
