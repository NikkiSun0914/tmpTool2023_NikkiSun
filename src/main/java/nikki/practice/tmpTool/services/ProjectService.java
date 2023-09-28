package nikki.practice.tmpTool.services;

import nikki.practice.tmpTool.dto.Project;
import nikki.practice.tmpTool.exceptions.ProjectIdException;
import nikki.practice.tmpTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProjectService {

    @Autowired
    private static ProjectRepository projectRepository;

    public Project saveOrUpdateProject(@Valid Project project) {
        try {
            project.setProjectId(project.getProjectId().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectId().toUpperCase() + "' is already existed");
        }

    }

    public Project findProjectById(String projectId){
        Project project = projectRepository.findByProjectId(projectId);

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");
        }
        return projectRepository.findByProjectId((projectId));
    }

    public static Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByProjectId(String projectId){}


}

