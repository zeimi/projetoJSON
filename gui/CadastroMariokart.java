package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class CadastroMariokart extends JFrame {

  private static Component labelpersonagens = null;
  private static Component labelnivel = null;


  private JLabel labeltxt;
  private JTextField txtNome;
  private JTextField txtcpf;
  private JLabel labeljog;
  private JTextField txtjog;
  private JButton botaoSalvar;
  private JLabel panel;
  private JComboBox<String> personagens1;
  private JComboBox<String> nivel1;
  private JTextArea textArea;
  private JLabel labelCpf;
  private String cpf;
  private JLabel myLabel, myLabel2;
  private RadioButtonHandler handler;
  private JRadioButton Sala1, Sala2, sim, nao;
  private ButtonGroup GrupoSalas;
  private ButtonGroup GruposSN;

  public CadastroMariokart() {
        
    super("Cadastro de Equipe");

    JLabel CadastroMariokart = new JLabel("JOGADOR");

    // inicialização dos cadastros J1
    labeljog = new JLabel("Nome do jogador:");
    labelCpf = new JLabel("CPF:");
    
    txtNome = new JTextField(20);
    txtjog = new JTextField(20);
    txtcpf = new JTextField(20);
    myLabel = new JLabel("Deseja jogar em qual sala?");
    myLabel2 = new JLabel("\nVocê tem certeza disso?");
    GrupoSalas = new ButtonGroup();
    GruposSN = new ButtonGroup();
    Sala1 = new JRadioButton("sala1", false);
    Sala2= new JRadioButton("sala2", false);
    sim = new JRadioButton("Sim", false);
    nao = new JRadioButton("Não", false);

    // VALIDAÇÃO DO CAMPO CPF
    String cpf = txtcpf.getText(); // obter o cpf completo digitado
    cpf = cpf.replace(".", "");
    cpf = cpf.replace("-", "");
    cpf = cpf.replace(" ", "");

    // Escolher o persongaem do jogador
    labelpersonagens= new JLabel(" Escolha o seu personagem:");
    String[] personagens = {"Mário","Princesa Peach","Luigi","Princesa Daisy","Toad","Toadette","Yoshi", "Birdo"};
    personagens1 = new JComboBox<String> (personagens);

    // Escolher o nível de dificuldade 
    labelnivel= new JLabel("Qual o nivel de dificldade voc~e suporta:");
    String[] nivel = {"Easy", "Medium", "Hard"};
    nivel1 = new JComboBox<String> (nivel);
  

    // xxxxxxxxx declarando a localização xxxxxxx
    JPanel panel = (JPanel) getContentPane(); // obtém o painel de conteúdo desta janela
    panel.setLayout(new GridBagLayout());
    panel.setBorder(new EmptyBorder(10,10,10,10) );

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx=1;
    constraints.weighty=1;
    constraints.fill=GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,5,5,10);

    // Ajeitando as localizações 
    constraints.gridx=0; // coluna 0
    constraints.gridy=1; // linha 1
    panel.add(labeljog, constraints);
    constraints.gridx=1; // coluna 1
    constraints.gridy=1; // linha 1
    panel.add(txtjog, constraints);
    constraints.gridx=0; // coluna 0
    constraints.gridy=2; // linha 2
    //txtcpf
    panel.add(labelCpf, constraints);
    constraints.gridx=1; // coluna 1
    constraints.gridy=2; // linha 2
    panel.add(txtcpf, constraints);
    // personagens
    constraints.gridx=0; // coluna 2
    constraints.gridy=3; // linha 3
    panel.add (personagens1 , constraints);
    // SALAS
    constraints.gridx=0; // coluna 2
    constraints.gridy=4; // linha 4
    panel.add (myLabel, constraints);
   // sala1
    constraints.gridx=0; // coluna 3
    constraints.gridy=5; // linha 4
    panel.add (Sala1, constraints);
   // sala2
    constraints.gridx=0; // coluna 4
    constraints.gridy=6; // linha 4
    panel.add (Sala2, constraints);
    // Confirmação de sala
    constraints.gridx=2; // coluna 2
    constraints.gridy=4; // linha 7
    panel.add (myLabel2, constraints); 

    constraints.gridx=2; // coluna 4
    constraints.gridy=5; // linha 4
    panel.add (sim, constraints);

    constraints.gridx=2; // coluna 4
    constraints.gridy=6; // linha 4
    panel.add (nao, constraints);
   // Nivel
    constraints.gridx=0; // coluna 4
    constraints.gridy=6; // linha 4
    panel.add (nivel1, constraints);
    
    // configuração da janela
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true); // impede o redimensionamento da janela
    setLocation(800, 500);
    pack(); // define o tamanho da janela (menor possível para caber o conteúdo)
    setVisible(true);
            
    // Configuração do botão
              
              
    GrupoSalas.add(Sala1);
    GrupoSalas.add(Sala2);
              
    GruposSN = new ButtonGroup();
              GruposSN.add(sim);
              GruposSN.add(nao);
              
              Sala1.addItemListener(handler);
              Sala2.addItemListener(handler);
              sim.addItemListener(handler);
              nao.addItemListener(handler); 
             
  }

  /* private boolean validacaoSalvar() {
    // VALIDAÇÃO DO CAMPO NOME
    if (txtNome.getText().length() == 0) { // se o campo 'nome' está vazio
      JOptionPane.showInternalConfirmDialog(JLabelcpf, "O campo 'nome' deve estar preenchido!", "Erro de validação",
          JOptionPane.WARNING_MESSAGE);
      return false;
    }

    if (cpf.length() < 11) {
      JOptionPane.showInputDialog(this, "O campo 'cpf' deve ter 11 números!");
      return false;
    }
    return true;
  } */

  private class RadioButtonHandler implements ItemListener{

    @Override
    public void itemStateChanged(ItemEvent event) {
     if(Sala1.isSelected() && sim.isSelected()) // Modificar depois
      JOptionPane.showMessageDialog(null,"Ok, sala1 selecionada!");
      if(Sala1.isSelected() && nao.isSelected())
       JOptionPane.showMessageDialog(null,"Ok,selecione outra sala");
     if(Sala2.isSelected() && sim.isSelected())
      JOptionPane.showMessageDialog(null,"Ok, sala2 selecionada !");
      if(Sala2.isSelected() && nao.isSelected())
       JOptionPane.showMessageDialog(null,"Ok, selecione outra sala");

    }
    
  }
}
