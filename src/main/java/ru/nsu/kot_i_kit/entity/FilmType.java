package ru.nsu.kot_i_kit.entity;

import javax.persistence.*;

@Entity
@Table(name = "film_types")
public class FilmType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "dev_process_id", nullable = false)
    private DevProcess devProcess;

    @Column(name = "iso", nullable = false)
    private Integer iso;

    @Column(name = "is_monochrome", nullable = false)
    private Boolean isMonochrome = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DevProcess getDevProcess() {
        return devProcess;
    }

    public void setDevProcess(DevProcess devProcess) {
        this.devProcess = devProcess;
    }

    public Integer getIso() {
        return iso;
    }

    public void setIso(Integer iso) {
        this.iso = iso;
    }

    public Boolean getIsMonochrome() {
        return isMonochrome;
    }

    public void setIsMonochrome(Boolean isMonochrome) {
        this.isMonochrome = isMonochrome;
    }

}