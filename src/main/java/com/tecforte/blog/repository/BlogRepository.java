package com.tecforte.blog.repository;
import com.tecforte.blog.domain.Blog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Blog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select blog from Blog blog where blog.user.login = ?#{principal.username}")
    List<Blog> findByUserIsCurrentUser();

    @Query("select distinct blog from Blog blog left join fetch blog.entries")
    List<Blog> findAllWithEagerRelationships();

    @Query("select distinct blog from Blog blog left join fetch blog.entries where blog.id = ?1")
    Optional<Blog> findByIdWithEagerRelationships(Long id);
}
