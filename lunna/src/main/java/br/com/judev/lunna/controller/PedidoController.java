package br.com.judev.lunna.controller;

import br.com.judev.lunna.dto.ItemPedidoDtoResponse;
import br.com.judev.lunna.dto.PedidoDtoRequest;
import br.com.judev.lunna.dto.PedidoDtoResponse;
import br.com.judev.lunna.service.PedidoService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;


    @PostMapping
    public PedidoDtoResponse criarPedido(@RequestBody PedidoDtoRequest request) {
        return pedidoService.criarPedido(request);
    }


    @PutMapping("/itens/{itemId}/status")
    public ItemPedidoDtoResponse atualizarStatusItem(
            @PathVariable Long itemId,
            @RequestParam String status) {

        return pedidoService.atualizarStatusItem(itemId, status);
    }


    @GetMapping
    public Page<PedidoDtoResponse> listarPedidos(Pageable pageable) {
        return pedidoService.filtrarPedidos(pageable);
    }
}
