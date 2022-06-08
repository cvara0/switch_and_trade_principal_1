package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilControlador {

    private final PerfilServicio perfilServicio;

    @GetMapping("/traer-todo")
    public ModelAndView traerTodo() {
        ModelAndView mav = new ModelAndView("html que muestra todos los perfiles");
        mav.addObject("todoPerfil", perfilServicio.traerTodo());
        return mav;
    }

    @GetMapping("/formulario")
    public ModelAndView traerFormularioInsertar() {
        ModelAndView mav = new ModelAndView("html que muestra formulario para insertar perfil nuevo y llama a /insertar");
        mav.addObject("objetoPerfilVacio", new Perfil());
        return mav;
    }

    @GetMapping("/formulario/{id}")
    public ModelAndView traerFormularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("html que muestra formulario para actualizar perfil  y llama a /actualizar");
        mav.addObject("perfilParaActualizar", perfilServicio.traerPorId(id));
        return mav;
    }

    @PostMapping("/insertar")
    public RedirectView insertar(Perfil dto) {
        RedirectView redirect = new RedirectView("/perfiles/traer-todo");

        try {
            perfilServicio.insertar(dto);

        } catch (IllegalArgumentException e) {

            redirect.setUrl("/perfiles/formulario");
        }
        return redirect;
    }


    @PostMapping("/actualizar")
    public RedirectView actualizar(Perfil dto) {
        RedirectView redirect = new RedirectView("/perfiles/traer-todo");
        perfilServicio.actualizar(dto);

        return redirect;
    }

    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/enable/{id}")
    public RedirectView enable(@PathVariable Integer id) {
        authorService.enableById(id);
        return new RedirectView("/authors");
    }*/

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/perfiles/traer-todo");
        perfilServicio.eliminarPorId(id);
        return redirect;
    }

}
