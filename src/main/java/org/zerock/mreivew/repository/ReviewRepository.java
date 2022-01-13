package org.zerock.mreivew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreivew.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
