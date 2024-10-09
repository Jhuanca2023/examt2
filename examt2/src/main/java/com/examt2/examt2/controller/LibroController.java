package com.examt2.examt2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.examt2.examt2.model.Libro;
import com.examt2.examt2.repository.LibroRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Listar libros
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "libros/index";  
    }

    // Formulario para crear un nuevo libro
    @GetMapping("/crear")
    public String crearLibroForm(Model model) {
        model.addAttribute("libro", new Libro()); 
        return "libros/form";  
    }

    // Guardar un nuevo libro
    @PostMapping("/crear")
    public String guardarLibro(@ModelAttribute @Valid Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("libro", libro); 
            return "libros/form"; 
        }
        libroRepository.save(libro);
        return "redirect:/libros"; 
    }

    // Formulario para editar un libro existente
    @GetMapping("/editar/{id}")
    public String editarLibroForm(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid libro Id:" + id)));
        return "libros/form";
    }

    // Actualizar un libro existente
    @PostMapping("/editar/{id}")
    public String actualizarLibro(@PathVariable Long id, @ModelAttribute @Valid Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("libro", libro); 
            return "libros/form"; 
        }
        libro.setId(id); 
        libroRepository.save(libro);
        return "redirect:/libros"; 
    }

    // Eliminar un libro
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }
}
