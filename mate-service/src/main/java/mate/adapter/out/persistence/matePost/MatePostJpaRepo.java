package mate.adapter.out.persistence.matePost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatePostJpaRepo extends JpaRepository<MatePostJpaEntity, Long>, MatePostJpaRepoCustom {

    @Modifying(clearAutomatically = true)
    @Query(value = "update mate_post mp set mp.view = mp.view + 1 where mp.id = :id", nativeQuery = true)
    void viewCountUp(@Param("id") Long matePostId);


}
