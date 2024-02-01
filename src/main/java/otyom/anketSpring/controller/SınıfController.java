package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.service.SınıfService;

@RestController
@RequestMapping("/Sınıf")
public class SınıfController {
    @Autowired
    private SınıfService sınıfService;




}
