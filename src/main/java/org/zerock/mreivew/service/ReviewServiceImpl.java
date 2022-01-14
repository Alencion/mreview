package org.zerock.mreivew.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreivew.dto.ReviewDTO;
import org.zerock.mreivew.entity.Movie;
import org.zerock.mreivew.entity.Review;
import org.zerock.mreivew.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = this.reviewRepository.findByMovie(movie);

        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {

        Review review = dtoToEntity(reviewDTO);

        this.reviewRepository.save(review);

        return review.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {

        Optional<Review> result = this.reviewRepository.findById(reviewDTO.getReviewnum());

        if (result.isPresent()) {
            Review review = result.get();
            review.changeGrade(reviewDTO.getGrade());
            review.changeText(reviewDTO.getText());

            this.reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long reviewnum) {

        this.reviewRepository.deleteById(reviewnum);
    }
}
