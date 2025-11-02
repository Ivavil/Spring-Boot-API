package proyectoSpringBoot.controllers;

import org.springframework.web.bind.annotation.*;
import proyectoSpringBoot.domain.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProjectController {
    private List<Project> projects = new ArrayList<>(Arrays.asList(
            new Project(1, "petsitter", "", List.of("Python", "ROS2", "TensorFlow", "Keras", "OpenCV")),
            new Project(2, "Nevera Inteligente", "", List.of("C++", "Java", "Android", "Arduino", "Firebase", "MQTT")),
            new Project(4, "planetarium", "", List.of("C#", "RV/RA", "Unity", "ARCore", "Vuforia", "SteamVR")),
            new Project(3, "planetarium", "", List.of("HTML", "CSS", "JavaScript", "PHP", "SQL"))
            ));

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return projects;
    }

    @GetMapping("/projects/{technology}")
    public List<Project> getProjectsByTechnology(@PathVariable String technology){
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project: projects){
            for (String projectTechnology: project.getTechnologies()){
                if (projectTechnology.equalsIgnoreCase(technology)){
                    filteredProjects.add(project);
                }
            }
        }
        return filteredProjects;
    }

    @PostMapping("/projects")
    public Project postProject(@RequestBody Project project){
        projects.add(project);
        return project;
    }
}
