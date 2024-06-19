package com.adso.practica2.Controller;
import com.adso.practica2.model.Registro;
import com.adso.practica2.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller //Para definir la clase como controlador
public class UserController {
    @Autowired //Instance una clase dentro de una clase
    private ServiceRegistro serviceRegistro;

    @GetMapping({"/","/index"})
    public String home() {
        return "pages/index"; // Asegúrate de tener una plantilla llamada "index"
    }


    @GetMapping("/register/new") //
    public String FormRegister(Model model){
        model.addAttribute("registro", new Registro());
        return "pages/registro";
    }

    @PostMapping("/registro")
    public String CreateUser (@ModelAttribute Registro registro){
        serviceRegistro.saveRegister(registro);
        return "pages/index";
    }
    @GetMapping("/registro")
    public String ListRegister(Model model){
        model.addAttribute("result", serviceRegistro.getAllRegistro());
        return "pages/dataSave";
    }

    @RequestMapping("/Lista")
    public String lista(Model model){
        model.addAttribute("result", serviceRegistro.getAllRegistro());
        return "pages/Lista";
    }

    // Método para editar el registro
    @GetMapping("/registro/edit/{id}")
    public String editRegistro(@PathVariable Long id, Model model) {
        Registro registro = serviceRegistro.getRegistro(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de registro inválido: " + id));
        model.addAttribute("registro", registro);
        return "fragments/editRegistro"; // Asegúrate de tener una plantilla llamada "editRegistro"
    }

    @PostMapping("/registro/update/{id}")
    public String updateRegister(@PathVariable Long id, @ModelAttribute Registro registro) {
        registro.setId(id);
        serviceRegistro.saveOrUpdate(registro);
        return "redirect:/Lista"; // Redirige a la lista de registros después de actualizar
    }

    //Eliminar
    @GetMapping("/registros/delete/{registroId}")
    public String deleteRegistro(@PathVariable("registroId") Long registroId) {
        serviceRegistro.deleteRegistro(registroId);
        return "redirect:/Lista";

    }

    //Buscar
    @GetMapping("/registro/search")
    public String searchRegistroById(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            Optional<Registro> registro = serviceRegistro.getRegistro(id);
            if (registro.isPresent()) {
                model.addAttribute("result", Collections.singletonList(registro.get()));
            } else {
                model.addAttribute("result", serviceRegistro.getAllRegistro());
                model.addAttribute("mensaje", "No se encontró ningún registro con el ID proporcionado.");
            }
        } else {
            model.addAttribute("result", serviceRegistro.getAllRegistro());
        }
        return "pages/Lista";
    }


}