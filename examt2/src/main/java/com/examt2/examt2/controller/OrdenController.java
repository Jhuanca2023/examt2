package com.examt2.examt2.controller;

import com.examt2.examt2.model.Orden;
import com.examt2.examt2.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    // Mostrar el formulario de creación de una nueva orden
    @GetMapping("/crear")
    public String crearOrdenForm(Model model) {
        model.addAttribute("orden", new Orden()); 
        return "ordenes/form";  
    }

    // Guardar una nueva orden en la base de datos
    @PostMapping("/crear")
    public String guardarOrden(@RequestParam String fechaOrdenStr, @ModelAttribute @Valid Orden orden, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ordenes/form"; 
        }

        // Intentar convertir la fecha de String a Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaOrden = dateFormat.parse(fechaOrdenStr);
            orden.setFechaOrden(fechaOrden);
        } catch (ParseException e) {
            model.addAttribute("error", "Fecha inválida");
            return "ordenes/form"; 
        }

        // Guarda la orden en la base de datos
        ordenRepository.save(orden);
        return "redirect:/ordenes"; 
    }



    // Listar todas las órdenes
    @GetMapping
    public String listarOrdenes(Model model) {
        model.addAttribute("ordenes", ordenRepository.findAll());
        return "ordenes/index"; 
    }


    // Editar una orden existente
    @GetMapping("/editar/{id}")
    public String editarOrdenForm(@PathVariable Long id, Model model) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de orden no válida: " + id)); 
        model.addAttribute("orden", orden);
        return "ordenes/form"; 
    }

    // Actualizar una orden existente
    @PostMapping("/editar/{id}")
    public String actualizarOrden(@PathVariable Long id, @ModelAttribute @Valid Orden orden, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ordenes/form"; 
        }
        orden.setId(id); // Establece el ID de la orden
        ordenRepository.save(orden); 
        return "redirect:/ordenes"; 
    }

    // Eliminar una orden
    @GetMapping("/eliminar/{id}")
    public String eliminarOrden(@PathVariable Long id) {
        ordenRepository.deleteById(id); // Elimina la orden por ID
        return "redirect:/ordenes"; 
    }
}
