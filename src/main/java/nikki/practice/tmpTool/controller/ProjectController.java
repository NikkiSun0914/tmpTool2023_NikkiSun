package nikki.practice.tmpTool.controller;

import nikki.practice.tmpTool.dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.MapValidationErrorService;
import services.ProjectService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = MapValidationErrorService.mapValidation(result);

        if (errorMap != null) {
            return errorMap;
        }


        Project newProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }

}

