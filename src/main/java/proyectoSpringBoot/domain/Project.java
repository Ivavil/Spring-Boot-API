package proyectoSpringBoot.domain;

import java.util.List;

public class Project {
    private Integer id;
    private String name;
    private String description;
    private List<String> technologies;

    public Project(Integer id, String name, String description, List<String> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologies = technologies;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }


}
