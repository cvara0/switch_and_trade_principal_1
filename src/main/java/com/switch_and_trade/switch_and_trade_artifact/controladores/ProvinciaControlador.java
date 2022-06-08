package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.dtos.NuevoUsuarioDto;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciaControlador {

    private final ProvinciaServicio provinciaServicio;

    @GetMapping("/tabla")//muestra todo con botones
    public ModelAndView tabla(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla-provincia");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("todoProvincia", provinciaServicio.traerTodo());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-insertar")
    public ModelAndView formularioInsertar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("formulario-insertar-provincia");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("author", inputFlashMap.get("author"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("objetoProvinciaParaRellenar", new Provincia());
        }

        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario-actualizar-provincia");
        mav.addObject("objetoProvinciaParaActualizar", provinciaServicio.traerPorId(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/insertar")
    public RedirectView insertar(Author dto, @RequestParam(required = false) MultipartFile photo, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/authors");

        try {
            authorService.create(dto, photo);
            attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("author", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/authors/form");
        }

        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Author dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/authors");
        authorService.update(dto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/restablecer/{id}")
    public RedirectView enable(@PathVariable Integer id) {
        authorService.enableById(id);
        return new RedirectView("/authors");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/authors");
        authorService.deleteById(id);
        return redirect;
    }
/*
* /restablecer/{id}
* /eliminar/{id}
* */
}
