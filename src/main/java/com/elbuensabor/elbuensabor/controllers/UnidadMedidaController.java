package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.UnidadMedida;
import com.elbuensabor.elbuensabor.services.UnidadMedidaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping (path = "/ebs/unidades")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida, UnidadMedidaServiceImpl>{
}
