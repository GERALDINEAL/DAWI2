package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.FilmCreateDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.FilmDetailDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.FilmDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/start")
    public String start(Model model) {
        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("filmCreateDto", new FilmCreateDto(
                "",
                "",
                0,
                1,
                0,
                0.0,
                0,
                0.0,
                "",
                "",
                0
        ));
        return "maintenance_create";  // Vista donde se mostrará el formulario
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute FilmCreateDto filmCreateDto, Model model) {
        boolean isCreated = maintenanceService.createFilm(filmCreateDto);

        if (isCreated) {
            return "redirect:/maintenance/start";
        } else {
            model.addAttribute("errorMessage", "Error.");
            return "maintenance_create";
        }
    }

    @PostMapping("/remove/{id}")
    public String removeFilm(@PathVariable Integer id, Model model) {
        boolean isDeleted = maintenanceService.deleteFilm(id);

        if (isDeleted) {
            return "redirect:/maintenance/start";
        } else {
            model.addAttribute("errorMessage", "Error al eliminar la película.");
            return "maintenance";
        }
    }







}
