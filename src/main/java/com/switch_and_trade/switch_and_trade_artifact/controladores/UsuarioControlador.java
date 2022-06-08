package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.dtos.NuevoUsuario;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Usuario;
import com.switch_and_trade.switch_and_trade_artifact.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioControlador {
    private final UsuarioServicio usuarioServicio;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("login-form");

        if (error != null) mav.addObject("error", "email o passwords invalidos");
        if (logout != null) mav.addObject("logout", "Se ha deslogueado exitosamente");
        if (principal != null) mav.setViewName("redirect:/");

        return mav;
    }
    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("sign-up-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) mav.setViewName("redirect:/");

        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("usuario", inputFlashMap.get("usuario"));
        } else {
            Usuario usuario = new Usuario();
            usuario.setRol(Rol.USUARIO);
            mav.addObject("usuario", usuario);
        }

        return mav;
    }
    @PostMapping("/register")
    public RedirectView signup(NuevoUsuario dto, HttpServletRequest request, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");

        try {
            usuarioServicio.insertar(dto);//ya incluye los datos del perfil
            request.login(dto.getEmail(), dto.getClave());
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("usuario", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth/sign-up");
        } catch (ServletException e) {
            attributes.addFlashAttribute("error", "Auto login failed");
        }
        return redirect;
    }
}