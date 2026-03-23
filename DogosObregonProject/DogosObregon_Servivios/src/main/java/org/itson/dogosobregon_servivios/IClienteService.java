package org.itson.dogosobregon_servivios;

import org.itson.dogosobregon_dominio.Cliente;

public interface IClienteService extends IGenericoService<Cliente, Long> {
    Cliente buscarPorNombre(String nombre);
    void registrarClienteNuevo(Cliente c);
}
