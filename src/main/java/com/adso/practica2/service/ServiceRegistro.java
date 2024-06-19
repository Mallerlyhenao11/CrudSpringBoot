package com.adso.practica2.service;

import com.adso.practica2.model.Registro;
import com.adso.practica2.repository.RepositoryRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRegistro {
    @Autowired
    private RepositoryRegistro repositoryRegister;

    public List<Registro> getAllRegistro() {
        return repositoryRegister.findAll();
    }

    public Optional<Registro> getRegistro(Long id) {
        return repositoryRegister.findById(id);
    }

    public void saveRegister(Registro registro) {
        repositoryRegister.save(registro);
    }

    public void updateRegister(Registro registro) {
        repositoryRegister.save(registro);
    }

    public void deleteRegistro(Long id) {
        repositoryRegister.deleteById(id);
    }


    // Guardar o actualizar
    public void saveOrUpdate(Registro registro) {
        repositoryRegister.save(registro);

    }

}



