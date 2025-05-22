package org.ossrep.customer;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Customer")
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public Long customerId;

    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    public CustomerType customerType;

    @Column(name = "legal_name")
    public String legalName;

    @Column(name = "prefix")
    public String prefix;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "suffix")
    public String suffix;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(customerId, that.customerId) && customerType == that.customerType && Objects.equals(legalName, that.legalName) && Objects.equals(prefix, that.prefix) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(suffix, that.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerType, legalName, prefix, firstName, middleName, lastName, suffix);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId=" + customerId +
                ", customerType=" + customerType +
                ", legalName='" + legalName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }

}
