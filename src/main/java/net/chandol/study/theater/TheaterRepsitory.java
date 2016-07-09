package net.chandol.study.theater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepsitory extends JpaRepository<Theater, Long>{

}
