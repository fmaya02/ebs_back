package com.elbuensabor.elbuensabor.controllers;
import com.elbuensabor.elbuensabor.services.LocalidadServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.elbuensabor.elbuensabor.entities.Localidad;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ebs/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {

}
