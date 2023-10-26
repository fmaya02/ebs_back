package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.RubroArticulo;
import com.elbuensabor.elbuensabor.services.RubroArticuloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping (path = "/ebs/rubros")
public class RubroArticuloController extends BaseControllerImpl<RubroArticulo, RubroArticuloServiceImpl> {
}
