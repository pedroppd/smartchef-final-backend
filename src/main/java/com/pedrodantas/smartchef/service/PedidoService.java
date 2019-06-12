package com.pedrodantas.smartchef.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.ItemPedido;
import com.pedrodantas.smartchef.domain.PagamentoComDinheiro;
import com.pedrodantas.smartchef.domain.Pedido;
import com.pedrodantas.smartchef.domain.enums.EstadoPagamento;
import com.pedrodantas.smartchef.repository.ItemPedidoRepository;
import com.pedrodantas.smartchef.repository.PagamentoRepository;
import com.pedrodantas.smartchef.repository.PedidoRepository;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	//return all category
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	//return category by id
	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	//inserting one request of one client in database 
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findByEmail(obj.getCliente().getEmail()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.setNumeroMesa(obj.getNumeroMesa());
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComDinheiro) {
			PagamentoComDinheiro pagto = (PagamentoComDinheiro) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	

	
	
	
}
