package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Reposicion;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.ReposicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReposicionServiceImpl extends BaseServiceImpl<Reposicion, Long> implements ReposicionService {

    @Autowired
    private ReposicionRepository reposicionRepository;

    public ReposicionServiceImpl(BaseRepository<Reposicion, Long> baseRepository, ReposicionRepository reposicionRepository) {
        super(baseRepository);
        this.reposicionRepository=reposicionRepository;
    }
}
