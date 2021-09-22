package com.rislah.tpilet.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "Buses")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Bus {
    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(nullable = false, unique = true, name = "number")
    private int number;

    @Column(name = "wc")
    private boolean wc;
    @Column(name = "ac")
    private boolean ac;
    @Column(name = "wifi")
    private boolean wifi;

    @Column(name = "company_id")
    private int companyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus")
    private Set<Route> routes;

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Bus bus = (Bus) obj;
        return Objects.equals(bus.getNumber(), getNumber());
    }
}
