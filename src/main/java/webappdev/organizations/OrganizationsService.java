package webappdev.organizations;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import org.springframework.stereotype.Service;
import webappdev.login.User;
import webappdev.login.UserRepository;


@Service
public class OrganizationsService {

    private final OrganizationsRepository organizationsRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrganizationsService(OrganizationsRepository organizationsRepository, UserRepository userRepository) {
        this.organizationsRepository = organizationsRepository;
        this.userRepository = userRepository;
    }

    public List<Organizations> getOrganizations() {
        return organizationsRepository.findAll();
    }

    public Organizations getOrganizationById(Long id) {
        Optional<Organizations> organization = organizationsRepository.findById(id);
        return organization.orElse(null);
    }

    public Organizations getOrganizationByName(String name) {
        Optional<Organizations> organization = organizationsRepository.findOrganizationByName(name);
        return organization.orElse(null);
    }

    public List<Organizations> getOrganizationsByUserId(Long userId) {
        return organizationsRepository.findOrganizationsByUserId(userId);
    }

    public boolean saveOrganization(Organizations organization) {
        if (organization == null) {
            return false;
        }
        organizationsRepository.save(organization);
        return true;
    }

    public boolean saveOrganization(Organizations organization, Long userId) {
        if (organization == null) {
            return false;
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        organization.setUser(user);
        organizationsRepository.save(organization);
        return true;
    }

    public boolean deleteOrganizationById(Long id) {
        if (id == null) {
            return false;
        }
        organizationsRepository.deleteById(id);
        return true;
    }

    public boolean organizationExistsByName(String name) {
        return organizationsRepository.existsByName(name);
    }

    public boolean deleteOrganizationByUserId(Long userId) {
        if (userId == null) {
            return  false;
        }
        organizationsRepository.deleteOrganizationsByUserId(userId);
        return true;
    }
}