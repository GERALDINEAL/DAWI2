package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.entity.FilmActor;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM FilmActor fa WHERE fa.film.filmId = :filmId")
    void deleteByFilmId(@Param("filmId") int filmId);

}
