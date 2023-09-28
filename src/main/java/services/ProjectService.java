package services;

import nikki.practice.tmpTool.dto.Project;
import nikki.practice.tmpTool.exceptions.ProjectIdException;
import nikki.practice.tmpTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(@Valid Project project) {
        try {
            project.setProjectId(project.getProjectId().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectId().toUpperCase() + "' is already existed");
        }

    }

}