package org.zerock.mreivew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreivew.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
