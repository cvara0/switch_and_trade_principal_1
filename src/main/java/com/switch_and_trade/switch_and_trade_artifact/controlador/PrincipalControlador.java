package com.switch_and_trade.switch_and_trade_artifact.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalControlador {
    @GetMapping
    public ModelAndView traerIndex() {
        return new ModelAndView("index.html");
    }
}
