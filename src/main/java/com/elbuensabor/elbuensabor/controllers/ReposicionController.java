package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Reposicion;
import com.elbuensabor.elbuensabor.services.ReposicionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ebs/reposicion")
public class ReposicionController extends BaseControllerImpl<Reposicion, ReposicionServiceImpl> {
}
