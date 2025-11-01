package webappdev.organizations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class OrganizationsController {

    private final OrganizationsService organizationsService;

    @Autowired
    public OrganizationsController(OrganizationsService organizationsService) {
        this.organizationsService = organizationsService;
    }

    @GetMapping("/organizations") //GET
    public ResponseEntity<List<Organizations>> getAllOrganizations() {
        List<Organizations> organizations = organizationsService.getOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @PostMapping("/organizations/post") //GET
    @ResponseBody
    public List<Organizations> getUserOrganizations(@RequestBody OrganizationsRequest request) {
        return organizationsService.getOrganizationsByUserId(request.getUserId());
    }

    @PostMapping("/organization/save") //POST
    @ResponseBody
    public ResponseEntity<String> saveOrganization(@RequestBody OrganizationsRequest request) {

        if (organizationsService.organizationExistsByName(request.getName())) {
            return ResponseEntity.badRequest().body("Organization already exists");
        }

        System.out.println(request.getUserId());

        Organizations o = new Organizations();
        o.setImage(request.getImage());
        o.setName(request.getName());
        o.setSlug(request.getSlug());
        o.setUrl(request.getUrl());
        o.setUid(request.getUserId());

        boolean saved = organizationsService.saveOrganization(o, request.getUserId());
        if (saved) {
            return ResponseEntity.ok("Organization saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save organization");
        }
    }

    @DeleteMapping("/organization/delete") //DELETE
    @ResponseBody
    public ResponseEntity<?> deleteOrganization(@RequestBody OrganizationsRequest request) {
        boolean deleted = organizationsService.deleteOrganizationByUserId(request.getUserId());
        OrganizationsResponse organizationsResponse = new OrganizationsResponse();
        if (deleted) {
            organizationsResponse.setData(organizationsService.getOrganizationsByUserId(request.getUserId()));
            return ResponseEntity.ok().body(organizationsResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete organization");
        }
    }
}