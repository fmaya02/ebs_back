package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.services.DomicilioServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.elbuensabor.elbuensabor.entities.Domicilio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ebs/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioServiceImpl> {

}
