package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    @Transactional
    @Commit
    public void insertMovie() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie....." + i).build();

            System.out.println("---------------------------------------");

            this.movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1; // 1~4

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                this.movieImageRepository.save(movieImage);
            }

            System.out.println("=======================================");
        });
    }

    @Test
    void testListPage() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());
        // when
        Page<Object[]> result = this.movieRepository.getListPage(pageRequest);
        // then
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    void testGetMovieWithAll() {
        // given
        Long mno = 92L;
        // when
        List<Object[]> result = this.movieRepository.getMovieWithAll(mno);
        // then
        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
