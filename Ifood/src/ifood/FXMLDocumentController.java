package ifood;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable{
    @FXML
    private TextField EnderecoDonoTextField;

    @FXML
    private AnchorPane LoginViews;

    @FXML
    private TextField NomeRestauranteDonoTextField;

    @FXML
    private TextField PreparoTextField;

    @FXML
    private CheckBox alcoolicaCheckBox;

    @FXML
    private RadioButton bebidaRButton;

    @FXML
    private AnchorPane cadastroCliente;

    @FXML
    private AnchorPane cadastroRestaurante;

    @FXML
    private RadioButton comidaRButton;

    @FXML
    private Label custoTotalLabel;

    @FXML
    private TextArea descricaoProdutoTextField;

    @FXML
    private AnchorPane interfaceCliente;

    @FXML
    private AnchorPane interfaceDono;

    @FXML
    private ListView<String> listaCarrinho;

    @FXML
    private ListView<String> listaProdutos;

    @FXML
    private ListView<String> listaProdutosDono;

    @FXML
    private ListView<String> listaRestaurantes;

    @FXML
    private AnchorPane login;

    @FXML
    private TextField loginClienteTextField;

    @FXML
    private TextField loginDonoTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField nomeProdutoTextField;

    @FXML
    private Label nomeRestaurante;

    @FXML
    private Label nomeRestauranteLabel;

    @FXML
    private AnchorPane opcoesBebidaView;

    @FXML
    private AnchorPane opcoesComidaView;

    @FXML
    private Label precoProdutoLabel;

    @FXML
    private TextField precoProdutoTextField;

    @FXML
    private Label produtoSelecionadoLabel;

    @FXML
    private PasswordField senhaClienteTextField;

    @FXML
    private PasswordField senhaDonoTextField;

    @FXML
    private TextField tamanhoTextField;

    @FXML
    private Label tempoEstimadoLabel;

    @FXML
    private Label tempoPreparolabel;

    @FXML
    private TextField tipoCozinhaTextField;

    @FXML
    private CheckBox veganoCheckBox;

    @FXML
    private CheckBox vegetaranoCheckBox;

    @FXML
    private CheckBox vegetarianoCheckBoxCliente;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private Label tamanhoLabelCliente;

    @FXML
    private CheckBox veganoCheckBoxCliente;

    @FXML
    private CheckBox alcoolicaCheckBoxCliente;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private AnchorPane infoBebidaView;

    @FXML
    private AnchorPane infoComidaView;

    @FXML
    private Label tipoCozinhaLabel;

    
    @FXML
    void mostrarViewSelecionada() {
        if (comidaRButton.isSelected()) {
            opcoesComidaView.setVisible(true);
            opcoesBebidaView.setVisible(false);
        }
        else if (bebidaRButton.isSelected()){
            opcoesComidaView.setVisible(false);
            opcoesBebidaView.setVisible(true);
        }
    }
    
    Usuario usuarioConectado;
    
    @FXML
void adicionarProdutoRestaurante() {
    DonoRestaurante usuario = (DonoRestaurante) usuarioConectado;

    String nome = nomeProdutoTextField.getText();
    String descricao = descricaoProdutoTextField.getText();
    double preco;
    int preparo;

    try {
        // Validação inicial
        if (nome == null) {
            showAlert("Erro de validação", "O nome do produto não pode ser vazio.");
            return;
        }

        preco = Double.parseDouble(precoProdutoTextField.getText());
        preparo = Integer.parseInt(PreparoTextField.getText());

        // Criar produto de acordo com a seleção
        Produto produto;
        if (comidaRButton.isSelected()) {
            String tipoCozinha = tipoCozinhaTextField.getText();
            boolean vegetariano = vegetaranoCheckBox.isSelected();
            boolean vegano = veganoCheckBox.isSelected();

            produto = new Comida(tipoCozinha, vegetariano, vegano, nome, descricao, preco, preparo);
        } else {
            int tamanho = Integer.parseInt(tamanhoTextField.getText());
            boolean alcoolica = alcoolicaCheckBox.isSelected();

            produto = new Bebida(tamanho, alcoolica, nome, descricao, preco, preparo);
        }

        // Adicionar produto ao restaurante
        usuario.getRestaurante().addProduto(produto);
        listaProdutosDono.getItems().add(produto.getNome());
        showMessage("Sucesso", "Produto adicionado com sucesso!");
        
    } catch (NumberFormatException e) {
        showAlert("Erro de validação", "Por favor, insira valores numéricos válidos para preço, preparo ou tamanho.");
    } catch (IllegalArgumentException e) {
        showAlert("Erro de validação", e.getMessage());
    } catch (Exception e) {
        e.printStackTrace(); // achar erro de NullPointerException
        showAlert("Erro inesperado", "Ocorreu um erro inesperado. Consulte o console para mais detalhes.");
    }
}
    
    @FXML
private void modificarProdutoRestaurante() {
    try {
        // Recupera o produto selecionado
        String produtoSelecionadoNome = listaProdutosDono.getSelectionModel().getSelectedItem();
        if (produtoSelecionadoNome == null) {
            showAlert("Erro", "Nenhum produto selecionado!");
            return;
        }

        // Busca o produto correspondente no restaurante
        DonoRestaurante usuario = (DonoRestaurante) usuarioConectado;
        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            showAlert("Erro", "O restaurante não está inicializado!");
            return;
        }

        Produto produtoSelecionado = restaurante.getProduto(produtoSelecionadoNome);
        if (produtoSelecionado == null) {
            showAlert("Erro", "Produto não encontrado no restaurante!");
            return;
        }

        // Atualiza os valores do produto
        String novoNome = nomeProdutoTextField.getText();
        String novaDescricao = descricaoProdutoTextField.getText();
        double novoPreco = 0;
        int novoTempoPreparo = 0;

        try {
            novoPreco = Double.parseDouble(precoProdutoTextField.getText());
            novoTempoPreparo = Integer.parseInt(PreparoTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Erro de validação", "Preço ou tempo de preparo inválidos!");
            return;
        }

        // Atualiza os dados do produto
        produtoSelecionado.setNome(novoNome);
        produtoSelecionado.setDescricao(novaDescricao);
        produtoSelecionado.setPreco(novoPreco);
        produtoSelecionado.setTempoDePreparo(novoTempoPreparo);

        // Atualiza a interface (se necessário)
        listaProdutosDono.getItems().remove(produtoSelecionadoNome);
        listaProdutosDono.getItems().add(produtoSelecionado.getNome());
        showMessage("Sucesso", "Produto alterado com sucesso!");

    } catch (Exception e) {
        showAlert("Erro inesperado", "Ocorreu um erro: " + e.getMessage());
        e.printStackTrace(); //procurando erro
    }
}
     
     @FXML
     void removerProdutoRestaurante(ActionEvent event) {
         String item  = listaProdutosDono.getSelectionModel().getSelectedItem();
         listaProdutosDono.getItems().remove(item);
         DonoRestaurante usuario = (DonoRestaurante) usuarioConectado;
         usuario.getRestaurante().getProdutos().remove(usuario.getRestaurante().getProduto(item));
     }
    
    double precoAtual = 0;  
    int tempoEstimado=0;

    @FXML
    void adicionarProdutoCarrinho(ActionEvent event) {
        String nomeRestauranteProduto = listaRestaurantes.getSelectionModel().getSelectedItem();
        String nomeProduto =  listaProdutos.getSelectionModel().getSelectedItem();
        Produto produto = BancoDeDados.encontrarRestaurante(nomeRestauranteProduto).getProduto(nomeProduto);
        double precoProduto = produto.getPreco();
        int tempoProduto = produto.getTempoDePreparo();
        precoAtual += precoProduto;
        tempoEstimado += tempoProduto;
        // escrever h ou min caso o tempo estimado passe de uma hora
        String formatada =formatarTempo(tempoEstimado);
        
        tempoEstimadoLabel.setText(formatada);
        custoTotalLabel.setText(String.format("%.2f R$", precoAtual));
        listaCarrinho.getItems().add(nomeProduto);
    }
    
    @FXML
    void limparCarrinho(ActionEvent event) {
        precoAtual = 0;
        tempoEstimado = 0;
        tempoEstimadoLabel.setText("min");
        custoTotalLabel.setText("R$");
        listaCarrinho.getItems().clear();
        
    }

    @FXML
    void handleCriarContaCliente(ActionEvent event) {
     if (BancoDeDados.encontrarUsuario(loginClienteTextField.getText()) == null) {
         try {
            Cliente cliente = new Cliente(
                loginClienteTextField.getText(),
                senhaClienteTextField.getText()
            );

            BancoDeDados.addUsuario(cliente);

            showMessage("Sucesso", "Sua conta de usuário foi criada com sucesso!");

            // voltar a tela de login inicial
            irParaMenu();

        } catch (RuntimeException e) {
             showAlert("Erro de Validação", e.getMessage());
            
        }
     } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ocorreu um erro");
        alert.setHeaderText("Já existe um usuario com esse login! ");
        alert.setContentText("Escolha um nome diferente");
        alert.showAndWait();
        }
    
}

    @FXML
    void handleCriarContaRestaurante(ActionEvent event) {
        // procura para ver se já existe um usuario com o mesmo login e senha inseridos
        if (BancoDeDados.encontrarUsuario(loginDonoTextField.getText()) == null && BancoDeDados.encontrarRestaurante(NomeRestauranteDonoTextField.getText()) == null) {
            try {
                BancoDeDados.addUsuario(                        
                        new DonoRestaurante(
                                loginDonoTextField.getText(), 
                                senhaDonoTextField.getText(), 
                                EnderecoDonoTextField.getText(), 
                                NomeRestauranteDonoTextField.getText(),
                                null
                        )
                    );
                listaRestaurantes.getItems().add(NomeRestauranteDonoTextField.getText());
                showMessage("Sucesso", "Sua conta de restaurante foi criada com sucesso!");
                //voltar a tela de login inicial-
                irParaMenu();
            } catch (RuntimeException e) {
                showAlert("Erro de Validação", e.getMessage());
            }
            
            
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ocorreu um erro");
            alert.setHeaderText("Já existe um usuario com esse login ou restaurante com esse nome! ");
            alert.setContentText("Escolha um nome diferente");
            alert.showAndWait();
        }
        
    }

    // checando se o usuario existe pra entrar no sistema
    @FXML
    void handleEntrar(ActionEvent event) {
        Usuario usuario = BancoDeDados.encontrarUsuario(loginTextField.getText());
        
        if (!(usuario == null)){
            if (usuario instanceof Cliente) {
                // ENTRAR NO SISTEMA DE CLIENTE
                usuarioConectado = usuario;
                LoginViews.setVisible(false);
                interfaceCliente.setVisible(true);
            }
            else if (usuario instanceof  DonoRestaurante) {
                // ENTRAR NO SISTEMA DE DONO DE RESTAURANTE
                usuarioConectado = usuario;
                LoginViews.setVisible(false);
                interfaceDono.setVisible(true);
                // Zerar conteúdo
                listaProdutosDono.getItems().clear();
                listaProdutosDono.getItems().addAll(((DonoRestaurante) usuario).getRestaurante().getProdutosComoStrings());
                nomeRestauranteLabel.setText(((DonoRestaurante) usuario).getRestaurante().getNome());
                
                nomeProdutoTextField.setText(null);
                descricaoProdutoTextField.setText((null));
                precoProdutoTextField.setText(null);
                PreparoTextField.setText(null);
                comidaRButton.setSelected(true);
                bebidaRButton.setSelected(false);
                veganoCheckBox.setSelected(false);
                vegetaranoCheckBox.setSelected(false);
                alcoolicaCheckBox.setSelected(false);
                tamanhoTextField.setText(null);
                mostrarViewSelecionada();
                
            }
        } 
        else {
            // mostrando pro usuario que não foi possivel acessar o seu usuário
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ocorreu um erro");
            alert.setHeaderText("Nome de usuário e / ou senha inválida");
            alert.setContentText("Certifique-se de que não há erros de digitação\nNovo aqui? Crie uma conta primeiro!");
            alert.showAndWait();
        }
            
    }

    @FXML
    void mostrarCadastroCliente(ActionEvent event) {
        cadastroRestaurante.setVisible(false);
        cadastroCliente.setVisible(true);
        login.setVisible(false);

    }

    @FXML
    void mostrarCadastroRestaurante(ActionEvent event) {
        cadastroRestaurante.setVisible(true);
        cadastroCliente.setVisible(false);
        login.setVisible(false);
    }
    
    @FXML
    void irParaMenu() {
        usuarioConectado = null;
        LoginViews.setVisible(true);
        login.setVisible(true);
        cadastroCliente.setVisible(false);
        cadastroRestaurante.setVisible(false);
        interfaceCliente.setVisible(false);
        interfaceDono.setVisible(false);
        
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // INICIALIZA OS DADOS NO BANCO DE DADOS
        for (Restaurante restaurante : BancoDeDados.getRestaurantes()) {
            listaRestaurantes.getItems().add(restaurante.getNome());
        }
        
        listaRestaurantes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(BancoDeDados.encontrarRestaurante(listaRestaurantes.getSelectionModel().getSelectedItem()).getNome());
                System.out.println(BancoDeDados.encontrarRestaurante(listaRestaurantes.getSelectionModel().getSelectedItem()).getProdutosComoStrings());

                ArrayList<String> produtos = BancoDeDados.encontrarRestaurante(listaRestaurantes.getSelectionModel().getSelectedItem()).getProdutosComoStrings();
                listaProdutos.getItems().clear();
                listaProdutos.getItems().addAll(produtos);
                nomeRestaurante.setText(listaRestaurantes.getSelectionModel().getSelectedItem());
                
            }
        });
        
        listaProdutos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String nomeProduto = listaProdutos.getSelectionModel().getSelectedItem();
                String nomeRestauranteProduto = listaRestaurantes.getSelectionModel().getSelectedItem();
                Produto produto = BancoDeDados.encontrarRestaurante(nomeRestauranteProduto).getProduto(nomeProduto);
                
                
                //mudanças especificas de subclasse
                if (produto instanceof  Comida) {
                    infoComidaView.setVisible(true);
                    infoBebidaView.setVisible(false);
                    vegetarianoCheckBoxCliente.setSelected(((Comida) produto).isVegetariano());
                    veganoCheckBoxCliente.setSelected(((Comida) produto).isVegano());
                    tipoCozinhaLabel.setText(((Comida) produto).getTipoCozinha());
                    
                } else if (produto instanceof  Bebida){
                    infoBebidaView.setVisible(true);
                    infoComidaView.setVisible(false);
                    alcoolicaCheckBoxCliente.setSelected(((Bebida) produto).isAlcoolica());
                    tamanhoLabelCliente.setText(String.format("%d", ((Bebida) produto).getTamanho()));
                }
                //mudanças pra todo Produto
                produtoSelecionadoLabel.setText(nomeProduto);
                precoProdutoLabel.setText(String.format("%.2f R$", produto.getPreco()));
                tempoPreparolabel.setText(formatarTempo(produto.getTempoDePreparo()));
                descricaoTextArea.setText(produto.getDescricao());
                
                
            }
            });
        
        listaProdutosDono.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                DonoRestaurante usuario = (DonoRestaurante) usuarioConectado;
                Produto produto = BancoDeDados.encontrarRestaurante(usuario.getRestaurante().getNome()).getProduto(listaProdutosDono.getSelectionModel().getSelectedItem());
                if (produto instanceof Comida){
                    nomeProdutoTextField.setText(produto.getNome());
                    descricaoProdutoTextField.setText(produto.getDescricao());
                    precoProdutoTextField.setText(" "+produto.getPreco());
                    PreparoTextField.setText("" + produto.getTempoDePreparo());
                    comidaRButton.setSelected(true);
                    bebidaRButton.setSelected(false);
                    veganoCheckBox.setSelected(((Comida) produto).isVegano());
                    vegetaranoCheckBox.setSelected(((Comida) produto).isVegetariano());
                    mostrarViewSelecionada();
                } else if (produto instanceof Bebida) {
                    
                    nomeProdutoTextField.setText(produto.getNome());
                    descricaoProdutoTextField.setText(produto.getDescricao());
                    precoProdutoTextField.setText(" "+produto.getPreco());
                    PreparoTextField.setText("" + produto.getTempoDePreparo());
                    comidaRButton.setSelected(false);
                    bebidaRButton.setSelected(true);
                    alcoolicaCheckBox.setSelected(((Bebida) produto).isAlcoolica());
                    tamanhoTextField.setText(("" + ((Bebida) produto).getTamanho()));
                    mostrarViewSelecionada();

                }
            }
            
        });
    }
    
    public static String formatarTempo(int minutos) {
            return  minutos>=60? ((Integer) minutos/60) + "h " + minutos%60 + "min": minutos + "min";
        }
    
    private static void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    
    private static void showMessage(String title, String message) {
        Platform.runLater(() ->{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}