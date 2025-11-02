package proyectoSpringBoot.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpringBoot.domain.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    private List<Project> projects = new ArrayList<>(Arrays.asList(
            new Project(1, "petsitter", "", List.of("Python", "ROS2", "TensorFlow", "Keras", "OpenCV")),
            new Project(2, "Nevera Inteligente", "", List.of("C++", "Java", "Android", "Arduino", "Firebase", "MQTT")),
            new Project(3, "planetarium", "", List.of("C#", "RV/RA", "Unity", "ARCore", "Vuforia", "SteamVR")),
            new Project(4, "Pumpkin", "", List.of("HTML", "CSS", "JavaScript", "PHP", "SQL"))
    ));

    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projects);
    }

    @GetMapping("{technology}")
    public ResponseEntity<?> getProjectsByTechnology(@PathVariable String technology) {
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : projects) {
            for (String projectTechnology : project.getTechnologies()) {
                if (projectTechnology.equalsIgnoreCase(technology)) {
                    filteredProjects.add(project);
                }
            }
        }

        if (filteredProjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos desarrollados con " + technology);
        }
        return ResponseEntity.ok(filteredProjects);
    }

    @PostMapping
    public ResponseEntity<?> postProject(@RequestBody Project project) {
        projects.add(project);
        return ResponseEntity.status(HttpStatus.CREATED).body("Proyecto " + project.getName() + " creado exitosamente");
    }

    @PutMapping
    public ResponseEntity<?> putProjects(@RequestBody Project project) {
        for (Project targetProject : projects) {
            if (targetProject.getId().equals(project.getId())) {
                targetProject.setName(project.getName());
                targetProject.setDescription(project.getDescription());
                targetProject.setTechnologies(project.getTechnologies());

                return ResponseEntity.ok("Proyecto modificado exitosamente: " + targetProject.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado " + project.getName());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                projects.remove(project);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Proyecto borrado exitosamente: " + project.getId());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el proyecto con id" + id);
    }
}
