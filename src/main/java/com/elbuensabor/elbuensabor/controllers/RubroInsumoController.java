package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.RubroInsumo;
import com.elbuensabor.elbuensabor.services.RubroInsumoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/rubroinsumo")
public class RubroInsumoController extends BaseControllerImpl<RubroInsumo, RubroInsumoServiceImpl> {
}
