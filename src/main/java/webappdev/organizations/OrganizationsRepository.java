package webappdev.organizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Long> {
    Optional<Organizations> findOrganizationByName(String name);
    Optional<Organizations> findOrganizationById(Long id);

    List<Organizations> findOrganizationsByUserId(Long userId);

    void deleteOrganizationsByUserId(Long userId);

    boolean existsByName(String name);
}