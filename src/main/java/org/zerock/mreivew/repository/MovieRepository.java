package org.zerock.mreivew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreivew.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
            "left join MovieImage mi on mi.movie = m and mi.inum = (" +
            "select max(mi2) from MovieImage mi2 where mi2.movie = m" +
            ") " +
            "left join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);
}
