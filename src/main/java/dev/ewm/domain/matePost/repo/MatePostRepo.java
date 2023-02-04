package dev.ewm.domain.matePost.repo;

import dev.ewm.domain.matePost.MatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatePostRepo extends JpaRepository<MatePost, Long>,  MatePostRepoCustom {

    Optional<MatePost> findById(Long postId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update mate_post mp set mp.view = mp.view + 1 where mp.id = :id", nativeQuery = true)
    void viewCountUp(@Param("id") Long matePostId);

}
