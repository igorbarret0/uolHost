package com.uolHost.uolHost_backend_challenge.entity;

import com.uolHost.uolHost_backend_challenge.entity.enums.HeroGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;

    private String phone;

    private String codename;

    @Enumerated(EnumType.STRING)
    private HeroGroup heroGroup;

    public Player() {}

    public Player(String name, String email, String phone, String codename, HeroGroup heroGroup) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.codename = codename;
        this.heroGroup = heroGroup;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public HeroGroup getHeroGroup() {
        return heroGroup;
    }

    public void setHeroGroup(HeroGroup heroGroup) {
        this.heroGroup = heroGroup;
    }
}
