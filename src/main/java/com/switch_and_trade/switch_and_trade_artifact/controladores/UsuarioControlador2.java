package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.dtos.NuevoUsuario;
import com.switch_and_trade.switch_and_trade_artifact.dtos.NuevoUsuarioDto;
import com.switch_and_trade.switch_and_trade_artifact.entidades.*;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador2 {
    private final UsuarioServicio usuarioServicio;
    private final ProvinciaServicio provinciaServicio;
    private final NuevoUsuarioDto nuevoUsuarioDto;
    @GetMapping("/formulario-registrarse")
    public ModelAndView formularioRegistrarse(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("formulario-registrarse");
        mav.addObject("objeto_perfil_vacio",new Perfil());
        mav.addObject("objeto_usuario_vacio",new Usuario());

        return mav;
    }
    @GetMapping("/formulario-iniciar-sesion")
    public ModelAndView formularioIniciarSesion(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("formulario-iniciar-sesion");
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
    @PostMapping("/post-registrarse")
    public RedirectView signup(NuevoUsuarioDto dto, HttpServletRequest request, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");
        Localidad localidadSeleccionada=new Localidad();
        Provincia provinciaSeleccionada=new Provincia();
        Usuario nuevoUsuario=new Usuario();
        Perfil nuevoPerfil=new Perfil();

        provinciaSeleccionada.setNombre(dto.getNombreProvincia());
        provinciaSeleccionada.setEliminado(false);

        localidadSeleccionada.setNombre(dto.getNombreLocalidad());
        localidadSeleccionada.setEliminado(false);
        localidadSeleccionada.setProvincia(provinciaSeleccionada);

        nuevoPerfil.setNombre(this.nuevoUsuarioDto.getNombre());
        nuevoPerfil.setApellido(this.nuevoUsuarioDto.getApellido());
        nuevoPerfil.se

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