package synopsarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import synopsarapi.entity.Summary;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {

    @Query("SELECT s FROM Summary s WHERE s.userId = :userId ORDER BY s.date DESC")
    List<Summary> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT s FROM Summary s WHERE (s.id = :id AND s.userId = :userId)")
    Optional<Summary> findByIdAndUserId(@Param("id") Long id, @Param("userId") String userId);
}
