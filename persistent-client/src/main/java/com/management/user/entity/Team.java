package com.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private int id;
    @Column(name = "TEAM_NAME")
    private String teamName;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> employee = new ArrayList<>();

    public Team(int teamId) {
        this.id = teamId;
    }
}
