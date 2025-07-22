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
@Table(name = "sales_assistants")
@PrimaryKeyJoinColumn(name = "user_id")
public class SalesAssistant extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "commission_rate")
    private Double commissionRate;

    // Constructors
    public SalesAssistant() {
        super();
        setRole(UserRole.SALES_ASSISTANT);
    }

    public SalesAssistant(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName, UserRole.SALES_ASSISTANT);
        this.employeeId = employeeId;
    }

}

