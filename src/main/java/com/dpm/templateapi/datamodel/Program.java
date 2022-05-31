package com.dpm.templateapi.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Program", schema = "DPMDatabase")
public class Program {
    private String Name;

    @Id
    @Column(name = "Name", nullable = false)
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    @ManyToMany(mappedBy = "programs")
    List<Template> templates;

}
