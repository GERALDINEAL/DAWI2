package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.entity.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Film f WHERE f.id = :filmId")
    void deleteByFilmId(Integer filmId);
}
