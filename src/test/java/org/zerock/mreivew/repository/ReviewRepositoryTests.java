package org.zerock.mreivew.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreivew.entity.Member;
import org.zerock.mreivew.entity.Movie;
import org.zerock.mreivew.entity.Review;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void insertReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long) (Math.random() * 100) + 1;
            Long mid = (long) (Math.random() * 100) + 1;

            Review review = Review.builder()
                    .member(Member.builder().mid(mid).build())
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 영화의 대한 느낌..." + i)
                    .build();

            this.reviewRepository.save(review);
        });
    }
}
