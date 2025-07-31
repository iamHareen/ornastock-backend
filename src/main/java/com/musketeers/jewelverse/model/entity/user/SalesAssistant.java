package com.musketeers.jewelverse.model.entity.user;

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
@DiscriminatorValue("SALES_ASSISTANT")
public class SalesAssistant extends User {

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "commission_rate")
    private Double commissionRate;

    // Constructor
    public SalesAssistant(String email, String password, String firstName, String lastName, String employeeId) {
        super(email, password, firstName, lastName);
        this.employeeId = employeeId;
    }

}

