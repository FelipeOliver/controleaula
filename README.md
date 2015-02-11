# Sistema para Controle de Aulas

Para facilitar o desenvolvimento de **CRUD**, foi criado dentro do próprio sistema um *micro Framework*,   
tudo referente ao *micro Framework* está dentro do *Package*: **br.com.controleaula.framework**:

* BaseModel
* BaseDAO
* BaseService
* BaseController

O fluxo de desenvolvimento para um CRUD segue assim:
----------------------------------------------------

1. Criar uma classe Model herança de BaseModel, exemplo **Usuario**
2. Criar uma classe DAO herança de **BaseDAO**, exemplo **UsuarioDAO** e apenas anotar como: **@Repository("UsuarioDAO")**
2. Criar uma classe Service herança de **BaseService**, exemplo **UsuarioService** e apens anotar como: **@Service("UsuarioService")**
3. Criar uma classe Controller herança de **BaseController**, exemplo **UsuarioController** e anotar como: **@Controller e @RequestMapping("/usuario")**, criar um construtor passando por set() a Classe Model: **super.setClazz(Usuario.class);**
4. Criar uma View com o mesm nome da Model como lower case exemplo **usuario.jsp**

> Por convenção o Controller ira sempre redirecionar para uma View com o mesmo nome da Model com a escrita toda em Lower Case


Arquivos de Exemplo
-------------------

**Exemplo da classe UsuarioDAO:** [UsuarioDAO.java](https://gist.github.com/57a0da2a4c13eeca2866.git)

**Exemplo da classe Service:** [UsuarioService.java](https://gist.github.com/03f4860ad4e44066890c.git)

**Exemplo da Classe Controller:** [UsuarioController.java](https://gist.github.com/8a1896224f879914e725.git)

> Perceba que as classes DAO e Service não possui nenhuma implementação, apenas com as heranças a implementação do CRUD baseado na classe Model já será automaticamente criado pelo "micro framework", apenas a classe Controller deve receber uma pequena implementação, criando um Construtor que passe por set() qual a classe Model.

**Exemplo da View Usuario:** [usuario.jsp](https://gist.github.com/546cca80763b8404484d.git)

> Na view deve existir uma regra que quando no contexto a variavel "list" estiver nula significa que deve ser exibido apenas o Formulário de cadastro, e quando a varivael "list" não estiver nula significa que deve ser exibido a Table/GRID.

Como Contribuir
---------------

* Faça um fork desse repositório, clicando no botão [![Fork][14]][15], na parte superior direita da pagina do Github
* Clone seu fork:

    ``git clone --recursive https://github.com/SEU_USUARIO_DO_GITHUB/pythonclub.github.io.git``

* Após criar ou editar seu artigo faça um pull-request para que sua implementação entre em produção.

Veja o video que explica o processo de fork, clone, push e pull-request : http://pythonclub.com.br/como-fazer-fork-clone-push-pull-request-no-github.html
 
Sincronizando seu fork
----------------------

Caso você já tenha feito fork a algum tempo você tem duas opções para garantir que
estará trabalhando com as ultimas alterações, que pode ser simplesmente deletar
seu fork e fazer um novo ou sincronizar seu fork com o repositório de origem
usando as [instruções contidas na wiki](https://gist.github.com/55ed9eed0664d2f90f9c.git)




