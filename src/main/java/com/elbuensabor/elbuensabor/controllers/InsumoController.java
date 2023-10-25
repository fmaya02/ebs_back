package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Insumo;
import com.elbuensabor.elbuensabor.services.InsumoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ebs/insumo")
public class InsumoController extends BaseControllerImpl<Insumo, InsumoServiceImpl> {
}
