package com.fpmislata.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import com.fpmislata.excepciones.BussinessException;

public interface GenericDAO<T,ID extends Serializable> {
     T create() throws BussinessException;
     void saveOrUpdate(T entity) throws BussinessException;
     T get(ID id) throws BussinessException;
     void delete(ID id) throws BussinessException;
     List<T> findAll() throws BussinessException;
 }
