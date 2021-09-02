package com.rislah.tpilet.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "Companies")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Company.buses", attributeNodes = {@NamedAttributeNode("buses")})})
public class Company {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bus> buses;

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Company company = (Company) obj;
        return Objects.equals(this.name, company.getName());
    }
}
