package org.zerock.mreivew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreivew.dto.MovieDTO;
import org.zerock.mreivew.dto.PageRequestDTO;
import org.zerock.mreivew.dto.PageResultDTO;
import org.zerock.mreivew.entity.Movie;
import org.zerock.mreivew.entity.MovieImage;
import org.zerock.mreivew.repository.MovieImageRepository;
import org.zerock.mreivew.repository.MovieRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = this.movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie) arr[0],
                List.of((MovieImage) arr[1]),
                (Double) arr[2],
                (Long) arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }
}