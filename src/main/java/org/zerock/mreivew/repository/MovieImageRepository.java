package org.zerock.mreivew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreivew.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
