package com.pedrodantas.smartchef.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.PagamentoComDinheiro;


@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComDinheiro pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
