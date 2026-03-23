/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dogosobregon_persistencia;

import jakarta.persistence.EntityManager;
import org.itson.dogosobregon_dominio.Cliente;

/**
 *
 * @author martinbl
 */
public interface IClienteDAO extends IGenericoDAO<Cliente, Long>{
    Cliente buscarPorNombre(String nombre, EntityManager em);
}
