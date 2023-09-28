package nikki.practice.tmpTool.controller;

import nikki.practice.tmpTool.dto.Project;
import nikki.practice.tmpTool.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import nikki.practice.tmpTool.services.ProjectService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping(value = "")
    public ResponseEntity<Project> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);

        if(errorMap != null){
            return (ResponseEntity<Project>) errorMap;
        }

        Project newProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{ProjectId}")
    public ResponseEntity<Project> getProjectByProjectId(@PathVariable String projectId){
        Project project = projectService.findProjectById(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        Map<String, Iterable<Project>> map = new HashMap<>();
        map.put("data", ProjectService.findAllProjects());
        return new ResponseEntity<Map<String, Iterable<Project>>>(map, HttpStatus.OK);
    }


    @PutMapping(value = "/{ProjectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Map<String, String> updatedData) {
        Project existingProject = projectService.findProjectById(projectId);
        if(existingProject == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingProject.setValue(updatedData.get(projectId));
        Project updatedProject = projectService.saveOrUpdateProject(existingProject);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}

