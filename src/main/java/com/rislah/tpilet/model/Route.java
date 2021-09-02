package com.rislah.tpilet.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Routes")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Route.findAll", attributeNodes = {
                @NamedAttributeNode("bus"),
                @NamedAttributeNode("from"),
                @NamedAttributeNode("to")
        })})
public class Route {
    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price", columnDefinition = "DECIMAL(9, 2)")
    private BigDecimal price;

    @Column(name = "bus_id")
    private int busId;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "location_from_id", referencedColumnName = "id")
    private Location from;
    @ManyToOne
    @JoinColumn(name = "location_to_id", referencedColumnName = "id")
    private Location to;
}
