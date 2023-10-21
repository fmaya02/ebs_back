package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.services.FacturaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "ebs/Factura")
public class FacturaControllerImpl extends BaseControllerImpl<Factura, FacturaServiceImpl> {


}
