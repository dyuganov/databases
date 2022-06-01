package ru.nsu.kot_i_kit.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceType serviceType;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "adding_date", nullable = false)
    private OffsetDateTime addingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public OffsetDateTime getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(OffsetDateTime addingDate) {
        this.addingDate = addingDate;
    }

}