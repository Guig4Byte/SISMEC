package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import model.Autorizacao;
import model.Funcionario;
import model.Usuario;
import modelDAO.AutorizacaoDAO;
import modelDAO.FuncionarioDAO;
import modelDAO.GenericDAO;

@SuppressWarnings("serial")
@ManagedBean(name = "funcionarioMB")
@SessionScoped
public class FuncionarioMB implements Serializable {

	@Autowired
	private List<Funcionario> funcionariosSelecionados;
	
	
	private List<Autorizacao> autorizacoes = new ArrayList<>();
	
	private AutorizacaoDAO autorizacaoDAO = new AutorizacaoDAO();
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	private Autorizacao auxiliarParaSalvarEmLista = new Autorizacao();
	
	private List<Autorizacao> list_auxiliarParaSalvarEmLista = new ArrayList<>();
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	public FuncionarioMB() {
		funcionario = new Funcionario();
		funcionarios = new GenericDAO<Funcionario>(Funcionario.class).listarTodos();
		funcionariosSelecionados = new ArrayList<>();
		autorizacoes = new ArrayList<>();
		usuario = new Usuario();
		usuarios = new GenericDAO<Usuario>(Usuario.class).listarTodos();
	}

	public String salvar() {
		new GenericDAO<Funcionario>(Funcionario.class).salvar(funcionario);
		funcionarios = new GenericDAO<Funcionario>(Funcionario.class).listarTodos();
		funcionario = new Funcionario();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atendente cadastrado com sucesso"));
		System.out.println("Objeto " + funcionario.getNome() + " cadastrado com sucesso!");
		return "listarFuncionario.xhtml?faces-redirect=true";
	}
	
	public String salvarUsuario() {
		
		list_auxiliarParaSalvarEmLista.add(auxiliarParaSalvarEmLista);
		
		usuario.setAutorizacoes(list_auxiliarParaSalvarEmLista);
		
		usuarios.get(0);
		
		new GenericDAO<Usuario>(Usuario.class).salvar(usuario);
		usuarios = new GenericDAO<Usuario>(Usuario.class).listarTodos();
		usuario = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usu�rio cadastrado com sucesso"));
		System.out.println("Objeto " + usuario.getNomeUsuario() + " cadastrado com sucesso!");
		return "cadastrarUsuarios.xhtml?faces-redirect=true";
	}
	

	public String editar(Funcionario funcionario) {
		this.funcionario = funcionario;
		return "cadastrarFuncionario.xhtml?faces-redirect=true";
	}

	public void prepararExclusao(Funcionario funcionario) {
		this.funcionario = funcionario;
		System.out.println(" preparar para excluir funcionario: " + funcionario.getNome());
	}

	public void excluir() {
		System.out.println("excluir funcionario: " + funcionario.getNome());
		new GenericDAO<Funcionario>(Funcionario.class).excluir(funcionario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionario excluido com sucesso!"));
		funcionarios = new GenericDAO<Funcionario>(Funcionario.class).listarTodos();
	}

	public List<Funcionario> listarPorNome(String nomeFuncionario) {
		try {
			funcionariosSelecionados = funcionarioDAO.buscarFuncionarioByNome(nomeFuncionario);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		return funcionariosSelecionados;
	}

	public String limparFuncionario() {
		this.funcionario = new Funcionario();
		return "cadastrarFuncionario.xhtml?faces-redirect=true";
	}
	
	public String limparUsuario() {
		this.usuario = new Usuario();
		return "cadastrarUsuario.xhtml?faces-redirect=true";
	}

	public void detalhesFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Autorizacao> listaDePapeis(String nomePapel) {
		System.out.println("entrou no m�todo listaDePapeis");
		try {
			System.out.println("entrou no try listaDePapeis");
			autorizacoes = autorizacaoDAO.buscarPapeis(nomePapel);
		} catch (Exception e) {
			System.out.println("ERROR Exception, " + e);
		}
		return autorizacoes;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Autorizacao getAuxiliarParaSalvarEmLista() {
		return auxiliarParaSalvarEmLista;
	}

	public void setAuxiliarParaSalvarEmLista(Autorizacao auxiliarParaSalvarEmLista) {
		this.auxiliarParaSalvarEmLista = auxiliarParaSalvarEmLista;
	}

	public List<Autorizacao> getList_auxiliarParaSalvarEmLista() {
		return list_auxiliarParaSalvarEmLista;
	}

	public void setList_auxiliarParaSalvarEmLista(List<Autorizacao> list_auxiliarParaSalvarEmLista) {
		this.list_auxiliarParaSalvarEmLista = list_auxiliarParaSalvarEmLista;
	}
	

}