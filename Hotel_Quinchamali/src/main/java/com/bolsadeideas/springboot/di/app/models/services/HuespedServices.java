package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.dao.IHuespedDao;
import com.bolsadeideas.springboot.di.app.models.entity.Huesped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuespedServices implements IHuespedServices{

    @Autowired
    private IHuespedDao huespedDao;

    @Override
    public List<Huesped> findAll() {
        return (List<Huesped>) huespedDao.findAll();
    }

    @Override
    public void save(Huesped huesped) {
        huespedDao.save(huesped);
    }

    @Override
    public Huesped findOne(Long id) {
        return huespedDao.findById(id).get();
    }

    @Override
    public void deleted(Long id) {
        huespedDao.deleteById(id);
    }
}
