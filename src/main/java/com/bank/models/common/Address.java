package com.bank.models.common;

import com.bank.models.AuditableObject;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Address extends AuditableObject {
    @Column(name = "alias", length = 50)
    private String alias;
    @Column(name = "first_name", length = 40)
    private String firstName;
    @Column(name = "last_name", length = 80)
    private String lastName;
    @Column(name = "company", length = 80)
    private String company;
    @Column(name = "address1", length = 200)
    private String address1;
    @Column(name = "address2", length = 200)
    private String address2;
    @Column(name = "zip_postal_code", length = 20)
    private String zipPostalCode;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @Column(name = "fax_number", length = 20)
    private String faxNumber;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
}
