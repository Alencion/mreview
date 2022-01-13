package org.zerock.mreivew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreivew.dto.MovieDTO;
import org.zerock.mreivew.entity.Movie;
import org.zerock.mreivew.entity.MovieImage;
import org.zerock.mreivew.repository.MovieImageRepository;
import org.zerock.mreivew.repository.MovieRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        this.movieRepository.save(movie);

        movieImageList.forEach(this.movieImageRepository::save);

        return movie.getMno();
    }
}
