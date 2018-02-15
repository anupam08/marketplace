package marketplace.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import marketplace.dto.ProjectDTO;
import marketplace.services.ProjectService;

@RestController
@Api(basePath = "/marketplace", value = "/project", description = "Project API")
public class ProjectController {

    private final ProjectService projectService;

    @Inject
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/marketplace/project", method = RequestMethod.POST, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Create new project")
    public Long createProject(
                    @ApiParam(name = "Project", value = "Project", required = true) @Valid @RequestBody
                    ProjectDTO projectDTO) {
        return projectService.save(projectDTO).getId();
    }

    @RequestMapping(value = "/marketplace/project/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get project by Id")
    public ProjectDTO getProject(@ApiParam(value = "Project ID", required = true) @PathVariable long id) {
        return projectService.findOne(id);
    }

    @RequestMapping(value = "/marketplace/project", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all projects")
    public Iterable<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @RequestMapping(value = "/marketplace/project/{id}", method = RequestMethod.PUT, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Update project by Id")
    public ProjectDTO updateProject(
                    @ApiParam(value = "Project ID", required = true) @PathVariable Long id,
                    @ApiParam(name = "Project", value = "Project", required = true) @Valid @RequestBody
                    ProjectDTO projectDTO) {
        projectDTO.setId(id);
        return projectService.update(projectDTO);
    }

    @RequestMapping(value = "/marketplace/project/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete project by Id")
    public void deleteProject(@ApiParam(value = "Project ID", required = true) @PathVariable long id) {
        projectService.delete(id);
    }
    
    @RequestMapping(value = "/marketplace/project/open", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all open projects")
    public Iterable<ProjectDTO> getOpenProjects() {
        return projectService.findAllOpen();
    }

}

