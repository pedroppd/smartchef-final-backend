package com.pedrodantas.smartchef.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.Categoria;
import com.pedrodantas.smartchef.domain.Cidade;
import com.pedrodantas.smartchef.domain.Cliente;
import com.pedrodantas.smartchef.domain.Estado;
import com.pedrodantas.smartchef.domain.ItemPedido;
import com.pedrodantas.smartchef.domain.Pagamento;
import com.pedrodantas.smartchef.domain.PagamentoComCartao;
import com.pedrodantas.smartchef.domain.PagamentoComDinheiro;
import com.pedrodantas.smartchef.domain.Pedido;
import com.pedrodantas.smartchef.domain.Produto;
import com.pedrodantas.smartchef.domain.enums.EstadoPagamento;
import com.pedrodantas.smartchef.domain.enums.Perfil;
import com.pedrodantas.smartchef.repository.CategoriaRepository;
import com.pedrodantas.smartchef.repository.CidadeRepository;
import com.pedrodantas.smartchef.repository.ClienteRepository;
import com.pedrodantas.smartchef.repository.EstadoRepository;
import com.pedrodantas.smartchef.repository.ItemPedidoRepository;
import com.pedrodantas.smartchef.repository.PagamentoRepository;
import com.pedrodantas.smartchef.repository.PedidoRepository;
import com.pedrodantas.smartchef.repository.ProdutoRepository;

@Service
public class DBService {
	

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public void instantiateTestDataBase() throws ParseException {
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Categoria cat1 = new Categoria(null, "Massas");
		Categoria cat2 = new Categoria(null, "Bebidas");
		Categoria cat3 = new Categoria(null, "Pizzas");
		Categoria cat4 = new Categoria(null, "Doces");
		Categoria cat5 = new Categoria(null, "Comida Japonesa");
		Categoria cat6 = new Categoria(null, "Churrasco");
		Categoria cat7 = new Categoria(null, "Salgados");
		Categoria cat8 = new Categoria(null, "Bebidas alcoólicas");
		Categoria cat9 = new Categoria(null, "Sorvetes");
	

		
		
		
		Produto p1 = new Produto(null, "Pizza de calabresa", 50.00);
		Produto p2 = new Produto(null, "Pizza de frango com catupiry", 50.00);
		Produto p3 = new Produto(null, "Coca-cola ZERO", 8.00);
		Produto p4 = new Produto(null, "Suco de laranja", 6.00);
		Produto p5 = new Produto(null, "Macarrão com m/bolonhesa", 15.00);
		Produto p6 = new Produto(null, "Macarrão com m/branco", 15.00);
		Produto p7 = new Produto(null, "Sushi", 15.00);
		Produto p8 = new Produto(null, "Temaki", 15.00);
		Produto p9 = new Produto(null, "Picanha", 15.00);
		Produto p10 = new Produto(null, "Sorvete na napolitano", 15.00);
		Produto p11 = new Produto(null, "Sorvete de chocolate", 15.00);
		Produto p12 = new Produto(null, "Brigadeirão", 15.00);
		Produto p13 = new Produto(null, "Coxinha", 5.00);
		Produto p14 = new Produto(null, "Vodka", 5.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p5,p6));
		cat2.getProdutos().addAll(Arrays.asList(p3,p4));
		cat3.getProdutos().addAll(Arrays.asList(p1,p2));
		cat4.getProdutos().addAll(Arrays.asList(p12));
		cat5.getProdutos().addAll(Arrays.asList(p8, p7));
		cat6.getProdutos().addAll(Arrays.asList(p9));
		cat7.getProdutos().addAll(Arrays.asList(p13));
		cat8.getProdutos().addAll(Arrays.asList(p14));
		cat9.getProdutos().addAll(Arrays.asList(p10, p11));
	
		
		p1.getCategorias().addAll(Arrays.asList(cat3, cat1));
		p2.getCategorias().addAll(Arrays.asList(cat3, cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat5));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat9));
		p11.getCategorias().addAll(Arrays.asList(cat9));
		p12.getCategorias().addAll(Arrays.asList(cat4));
		p13.getCategorias().addAll(Arrays.asList(cat7));
		p14.getCategorias().addAll(Arrays.asList(cat8));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6, p7, p8, p9, p10, p11, p12, p13, p14));
		
		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "São paulo");
		Estado est3 = new Estado(null, "Rio de janeiro");
		
		Cidade c1 = new Cidade(null, "uberlandia", est1);
		Cidade c2 = new Cidade(null, "vila madalena", est2);
		Cidade c3 = new Cidade(null, "niterói", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		est3.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Pedro Dantas", "pedro_pdantas@hotmail.com", "12516453728");
		cli1.getTelefones().addAll(Arrays.asList("(21)975111058"));
		cli1.addPerfil(Perfil.ADMIN);
		
		Cliente cli2 = new Cliente(null, "Pedro Gabriel", "pedroppd90@gmail.com", "12536956289");
		cli2.getTelefones().addAll(Arrays.asList("(21)988728190"));
		
		
		
	
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
	
		Pedido ped1 = new Pedido(null, sdf.parse("20/12/2018 20:16"), cli1, 2);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 12:47"), cli2, 20);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 8);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComDinheiro(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/10/2018 20:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 50.00);
		ItemPedido ip2 = new ItemPedido(ped2, p2, 0.00, 2, 100.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p1.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2));	
	}
}
