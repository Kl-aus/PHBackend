package com.ph.phbackend.services;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.repository.DiagnoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiagnoseService {
    private DiagnoseRepository diagnoseRepository;

    @Autowired
    public DiagnoseService(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @Transactional
    public List<Diagnose> listDiagnoses() {
        return diagnoseRepository.findAll();
    }

}

