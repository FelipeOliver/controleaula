package br.com.controleaula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controleaula.framework.BaseController;
import br.com.controleaula.model.Pessoa;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends BaseController<Pessoa> {

	public PessoaController(){
		super.setClazz(Pessoa.class);
	}
	
}
