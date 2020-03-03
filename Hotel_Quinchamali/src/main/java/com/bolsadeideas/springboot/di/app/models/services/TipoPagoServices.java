package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.dao.ITipoPagoDao;
import com.bolsadeideas.springboot.di.app.models.entity.TipoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoPagoServices implements ITipoPagoServices {

    @Autowired
    private ITipoPagoDao tipoPagoDao;

    @Transactional(readOnly = true)
    public List<TipoPago> findAll() {
        return ((List<TipoPago>) tipoPagoDao.findAll());
    }

    @Transactional
    public void save(TipoPago tipoPago) {
        tipoPagoDao.save(tipoPago);
    }

    @Transactional(readOnly = true)
    public TipoPago finOne(Long id) {
        return tipoPagoDao.findById(id).get();
    }

    @Transactional
    public void delete(Long id) {
        tipoPagoDao.deleteById(id);
    }
}
