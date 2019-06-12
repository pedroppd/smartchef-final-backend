package com.pedrodantas.smartchef.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.pedrodantas.smartchef.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComDinheiro() {
		
	}
	
	

	public PagamentoComDinheiro(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		
	}



	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
