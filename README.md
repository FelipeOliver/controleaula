# controleaula

### Sistema para Controle de Aulas

Para facilitar o desenvolvimento de **CRUD**, foi criado dentro do próprio sistema um *micro Framework*,   
tudo referente ao *micro Framework* está dentro do *Package*: **br.com.controleaula.framework**:

* BaseModel
* BaseDAO
* BaseService
* BaseController

O fluxo de desenvolvimento para um CRUD segue assim:

1. Criar uma classe Model herança de BaseModel, exemplo **Usuario**
2. Criar uma classe DAO herança de **BaseDAO**, exemplo **UsuarioDAO** e apenas anotar como: **@Repository("UsuarioDAO")**
2. Criar uma classe Service herança de **BaseService**, exemplo **UsuarioService** e apens anotar como: **@Service("UsuarioService")**
3. Criar uma classe Controller herança de **BaseController**, exemplo **UsuarioController** e anotar como: **@Controller e @RequestMapping("/usuario")**, criar um construtor passando por set() a Classe Model: **super.setClazz(Usuario.class);**
4. Criar uma View com o mesm nome da Model como lower case exemplo **usuario.jsp**

> Por convenção o Controller ira sempre redirecionar para uma View com o mesmo nome da Model com a escrita toda em Lower Case

### Exemplo da classe DAO   
```java
package br.com.controleaula.dao;

import org.springframework.stereotype.Repository;
import br.com.controleaula.framework.BaseDAO;
import br.com.controleaula.model.Usuario;

@Repository("UsuarioDAO")
public class UsuarioDAO extends BaseDAO<Usuario> {
}
```   

### Exemplo da classe Service   
```java
package br.com.controleaula.service;

import org.springframework.stereotype.Service;
import br.com.controleaula.framework.BaseService;
import br.com.controleaula.model.Usuario;

@Service("UsuarioService")
public class UsuarioService extends BaseService<Usuario> {

}
```   

### Exemplo da Classe Controller   
```java
package br.com.controleaula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.controleaula.framework.BaseController;
import br.com.controleaula.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends BaseController<Usuario> {

	public UsuarioController(){
		super.setClazz(Usuario.class);
	}
	
}
```

> Perceba que as classes DAO e Service não possui nenhuma implementação, apenas com as heranças a implementação do CRUD baseado na classe Model já será automaticamente criado pelo "micro framework", apenas a classe Controller deve receber uma pequena implementação, criando um Construtor que passe por set() qual a classe Model.

### exemplo da View   
```html
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:if test="${list eq null}">
	<section id="usuario-form" class="well">
		
		<form action="${pageContext.request.contextPath}/usuario/save" method="post" id="id_form_usuario">
			<br>
			<div class="row">
	        	<div class="col-xs-1">
	        		<div class="form-group">
	        			<label for="id_usuario_id" class="text-info">ID</label>
	            		<input type="text" name="id" id="id_usuario_id" value="${model.id}" class="form-control text-center" readonly>
	            	</div>
	            </div>
	        	<div class="col-xs-5">
	        		<div class="form-group">
	        			<label for="id_usuario_login" class="text-info">Login</label>
	            		<input type="text" name="login" id="id_usuario_login" value="${model.login}" class="form-control text-center" required placeholder="Digite o nome do ${model_name} aqui...">
	            	</div>
	            </div>
				<div class="col-xs-6">
					<div class="form-group">
	        			<label for="id_usuario_senha" class="text-info">Senha</label>
						<input type="password" name="senha" id="id_usuario_senha" value="${model.senha}" class="form-control text-center" placeholder="Digite a Senha aqui...">
					</div>
				</div> 		            
	        </div>
			<br/>
			<a href="${pageContext.request.contextPath}/usuario/list" class="btn btn-default" 
				data-toggle="tooltip" data-placement="bottom" title="Cancelar Cadastro...">&nbsp;<span data-icon="&#xf00d;" aria-hidden="true"></span>&nbsp;</a>
			<button type="submit" class="btn btn-info pull-right" 
				data-toggle="tooltip" data-placement="bottom" title="Salvar novo Cadastro...">&nbsp;<span data-icon="&#xf0c7;" aria-hidden="true"></span>&nbsp;</button>
	    </form>
			    
	    
	    
	</section>
</c:if>

<c:if test="${list != null}">
	<section id="usuario-table">
		<table class="table table-striped table-condensed table-hover table-bordered">
		   	<thead>
		       	<tr>
		        	<th class="text-center">ID</th>
		            <th>Login</th>
			        <th>Senha</th>
		            <th class="text-center"> <span data-icon="&#xf085;" aria-hidden="true"></span> </th>
		        </tr>
		    </thead>
		      	<tbody>
		      		<c:if test="${list != null}">
		      			<c:forEach var="model" items="${list}">
				        	<tr>
					        	<td class="text-center">${model.id}</td>
					            <td>${model.login}</td>
						        <td>${model.senha}</td>
					            <td class="text-center">
					            	<a href="${pageContext.request.contextPath}/usuario/edit/${model.id}"
					            		data-toggle="tooltip" data-placement="left" title="Editar Registro...">
					            		<span class="text-info" data-icon="&#xf044;" aria-hidden="true" title="Editar Registro"></span>
					            	</a> 
					            	<a href="${pageContext.request.contextPath}/usuario/delete/${model.id}"
					            		data-toggle="tooltip" data-placement="bottom" title="Deletar Registro...">
					            		<span class="text-danger" data-icon="&#xf014;" aria-hidden="true" title="Deletar Registro"></span>
					            	</a>
					            </td>
				        	</tr>
				        </c:forEach>
			        </c:if>
		      	</tbody>
		</table>
	</section>
</c:if>
```

> Na view deve existir uma regra que quando no contexto a variavel "list" estiver nula significa que deve ser exibido apenas o Formulário de cadastro, e quando a varivael "list" não estiver nula significa que deve ser exibido a Table/GRID.


