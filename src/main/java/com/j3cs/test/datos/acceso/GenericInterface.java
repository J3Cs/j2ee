/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.datos.acceso;

import java.util.List;

/**
 *
 * @author jcastro
 */
public interface GenericInterface<T> {
    void create(T Entity);
    void update(T Entity);
    void delete(T Entity);
    List<T> findAll();
}
