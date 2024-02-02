package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.service.ClasService;

@RestController
@RequestMapping("/Sınıf")
public class ClasController {
    @Autowired
    private ClasService clasService;




}
