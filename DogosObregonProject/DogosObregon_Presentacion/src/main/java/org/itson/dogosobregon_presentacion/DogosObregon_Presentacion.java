package org.itson.dogosobregon_presentacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.itson.dogosobregon_dominio.Cliente;
import org.itson.dogosobregon_dominio.HotDog;
import org.itson.dogosobregon_dominio.MetodoPago;
import org.itson.dogosobregon_dominio.Pedido;
import org.itson.dogosobregon_dominio.PedidoDetalle;
import org.itson.dogosobregon_servivios.ClienteService;
import org.itson.dogosobregon_servivios.HotDogService;
import org.itson.dogosobregon_servivios.IClienteService;
import org.itson.dogosobregon_servivios.IHotDogService;
import org.itson.dogosobregon_servivios.IPedidoDetalleService;
import org.itson.dogosobregon_servivios.IPedidoService;
import org.itson.dogosobregon_servivios.PedidoDetalleService;
import org.itson.dogosobregon_servivios.PedidoService;

public class DogosObregon_Presentacion {

    public static void main(String[] args) {
        IClienteService clienteService = new ClienteService();
        IHotDogService hotDogService = new HotDogService();
        IPedidoService pedidoService = new PedidoService();
        IPedidoDetalleService pedidoDetalleService = new PedidoDetalleService();

        System.out.println("--- Iniciando Pruebas de Servicios Dogos Obregón ---");

        try {
            // 1. Guardar Cliente
            Cliente cliente = new Cliente();
            cliente.setNombre("Juan");
            cliente.setApPaterno("Pérez");
            cliente.setFchNac(LocalDate.of(1990, 5, 20));
            clienteService.guardar(cliente);
            System.out.println("Cliente guardado exitosamente.");

            // 2. Guardar HotDog
            HotDog hotdog = new HotDog();
            hotdog.setNombre("Dogo Sencillo");
            hotdog.setPrecio(new BigDecimal("35.00"));
            hotdog.setIva(new BigDecimal("0.16"));
            hotDogService.guardar(hotdog);
            System.out.println("HotDog guardado exitosamente.");

            // 3. Crear Pedido
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setFecha(LocalDateTime.now());
            pedido.setMetodoPago(MetodoPago.EFECTIVO);
            pedidoService.guardar(pedido);
            System.out.println("Pedido guardado exitosamente.");

            // 4. Crear Detalles de Pedido
            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(pedido);
            detalle.setHotdog(hotdog);
            detalle.setCantidad(2);
            detalle.setPrecioVenta(hotdog.getPrecio());
            detalle.setSubtotal(hotdog.getPrecio().multiply(new BigDecimal(2)));
            pedidoDetalleService.guardar(detalle);
            System.out.println("Detalle guardado exitosamente.");

            // 5. Prueba de Validaciones (Debe lanzar excepción)
            System.out.println("\nProbando validación de HotDog sin nombre...");
            HotDog hotDogInvalido = new HotDog();
            // Esto lanzará IllegalArgumentException porque el nombre es nulo
            hotDogService.guardar(hotDogInvalido);

        } catch (IllegalArgumentException e) {
            System.out.println("Excepción de validación capturada exitosamente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}