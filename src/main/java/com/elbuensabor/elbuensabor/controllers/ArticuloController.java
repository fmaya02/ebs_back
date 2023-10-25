package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.services.ArticuloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ebs/articulo")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl> {
}
