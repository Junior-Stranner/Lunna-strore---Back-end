package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.*;
import br.com.judev.lunna.entity.*;
import br.com.judev.lunna.enums.StatusPedido;
import br.com.judev.lunna.mapper.ItemPedidoMapper;
import br.com.judev.lunna.mapper.PedidoMapper;
import br.com.judev.lunna.repositories.ItemPedidoRepository;
import br.com.judev.lunna.repositories.PedidoRepository;
import br.com.judev.lunna.repositories.ProdutoRepository;
import br.com.judev.lunna.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ItemPedidoRepository itemPedidoRepository,
                         ProdutoRepository produtoRepository,
                         UsuarioRepository usuarioRepository) {

        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public PedidoDtoResponse criarPedido(PedidoDtoRequest request) {

        Usuario usuario = usuarioRepository.findById(Math.toIntExact(request.getUsuarioId()))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<ItemPedido> itens = request.getItens().stream().map(dtoItem -> {

            Produto produto = produtoRepository.findById(Math.toIntExact(dtoItem.getProdutoId()))
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(dtoItem.getQuantidade());
            item.setPreco(produto.getPreco().multiply(BigDecimal.valueOf(dtoItem.getQuantidade())));
            item.setStatus(StatusPedido.PENDENTE);
            item.setUsuario(usuario);

            return item;

        }).collect(Collectors.toList());

        BigDecimal totalCalculado = itens.stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(totalCalculado);
        pedido.setStatus(String.valueOf(StatusPedido.PENDENTE));
        pedido.setItens(itens);

        itens.forEach(item -> item.setPedido(pedido));

        pedidoRepository.save(pedido);

        return PedidoMapper.toDto(pedido);
    }


    public ItemPedidoDtoResponse atualizarStatusItem(Long itemId, String status) {

        ItemPedido item = itemPedidoRepository.findById(Math.toIntExact(itemId))
                .orElseThrow(() -> new EntityNotFoundException("Item do pedido não encontrado"));

        StatusPedido novoStatus = StatusPedido.valueOf(status.toUpperCase());
        item.setStatus(novoStatus);

        ItemPedido atualizado = itemPedidoRepository.save(item);

        return ItemPedidoMapper.toDto(atualizado);
    }


    public Page<PedidoDtoResponse> filtrarPedidos(Pageable pageable) {

        Page<Pedido> page = pedidoRepository.findAll(pageable);

        return page.map(PedidoMapper::toDto);
    }
}
