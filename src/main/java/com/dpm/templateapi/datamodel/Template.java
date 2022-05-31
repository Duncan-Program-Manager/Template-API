package com.dpm.templateapi.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Template", schema = "DPMDatabase")
public class Template {
    private int Id;
    private String Name;
    private List<Program> programs;
    private String Username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() { return Id; }
    public void setId(int id) {
        Id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false)
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Templates_Programs",
            joinColumns = { @JoinColumn(name = "Id")},
            inverseJoinColumns = { @JoinColumn(name = "Name")}
    )
    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @Basic
    @Column
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
}
