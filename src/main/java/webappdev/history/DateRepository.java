package webappdev.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    // You can define custom query methods here if needed
    List<Date> findDateByUserId(Long userId);
}