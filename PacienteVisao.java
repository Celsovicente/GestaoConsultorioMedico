/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PacienteVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PacienteVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public PacienteVisao(boolean alterar, PacienteModelo modelo)
    {
        super("Cadastro dos Pacientes");

       editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, nomeJTF, dataNascimentoJTF, telefoneJTF, emailJTF, numeroDocumentoJTF;
        private JComboBox generoJCB, nacionalidadeJCB, provinciaJCB, municipioJCB, comunaJCB; 
        private String generoArray[] = {"Masculino", "Feminino"};
        private JTextFieldData txtData;
        private JComboBoxTabela3_Tabela3 provinciaComMunicipio;
        private PacienteFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(12, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new PacienteFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // 3º linha
            add(new JLabel("Data de Nascimento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 4º linha
            add(new JLabel("Numero de Documento"));
            add(numeroDocumentoJTF = new JTextField());

            // 5º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(generoArray));

            // 6º linha
            add(new JLabel("Nacionalidade"));
            add(nacionalidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Nacionalidades.tab"));

            // 7º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());

            // 8º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());

            // 9º linha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());

            // 10º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());

            // 11º linha
            add(new JLabel("Email"));
            add(emailJTF = new JTextField());
        }

        public PainelCentro(PacienteModelo modelo)
        {
            setLayout(new GridLayout(12, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new PacienteFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" + modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());
            nomeJTF.setText(modelo.getNome());

            // 3º linha
            add(new JLabel("Data de Nascimento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataNascimento());

            // 4º linha
            add(new JLabel("Numero de Documento"));
            add(numeroDocumentoJTF = new JTextField());
            numeroDocumentoJTF.setText(modelo.getNumeroDocumento());

            // 5º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(generoArray));
            generoJCB.setSelectedItem(modelo.getGenero());

            // 6º linha
            add(new JLabel("Nacionalidade"));
            add(nacionalidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Nacionalidades.tab"));
            nacionalidadeJCB.setSelectedItem(modelo.getNacionalidade());

            // 7º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());
            provinciaJCB.setSelectedItem(modelo.getProvincia());

            // 8º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());
            municipioJCB.setSelectedItem(modelo.getMunicipio());

            // 9º linha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());
            comunaJCB.setSelectedItem(modelo.getComuna());

            // 10º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());
            telefoneJTF.setText(modelo.getTelefone());

            // 11º linha
            add(new JLabel("Email"));
            add(emailJTF = new JTextField());
            emailJTF.setText(modelo.getEmail());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome()
        {
            return nomeJTF.getText().trim();
        }

        public String getDataNascimento()
        {
            return   txtData.getDTestField().getText();
        }

        public String getNumeroDocumento()
        {
            return numeroDocumentoJTF.getText().trim();
        }

        public String getGenero()
        {
            return String.valueOf(generoJCB.getSelectedItem());
        }

        public String getNacionalidade()
        {
            return String.valueOf(nacionalidadeJCB.getSelectedItem());
        }

        public String getProvincia()
        {
            return String.valueOf(provinciaJCB.getSelectedItem());
        }

        public String getMunicipio()
        {
            return String.valueOf(municipioJCB.getSelectedItem());
        }

        public String getComuna()
        {
            return String.valueOf(comunaJCB.getSelectedItem());
        }

        public String getTelefone()
        {
            return telefoneJTF.getText().trim();
        }

        public String getEmail()
        {
            return emailJTF.getText().trim();
        }

        // metodos setters
        public void setId(int id)
        {
           idJTF.setText("" + id);
        }

        public void setNome(String nome)
        {
            nomeJTF.setText(nome);
        }

        public void setDataNascimento(String dataNascimento)
        {
            txtData.getDTestField().setText(dataNascimento);
        }

        public void setNumeroDocumento(String numeroDocumento)
        {
            numeroDocumentoJTF.setText(numeroDocumento);
        }

        public void setGenero(String genero)
        {
           generoJCB.setSelectedItem(genero);
        }

        public void setNacionalidade(String nacionalidade)
        {
           nacionalidadeJCB.setSelectedItem(nacionalidade);
        }

        public void setProvincia(String provincia)
        {
           provinciaJCB.setSelectedItem(provincia);
        }

        public void setMunicipio(String municipio)
        {
            municipioJCB.setSelectedItem(municipio);
        }

        public void setComuna(String comuna)
        {
            comunaJCB.setSelectedItem(comuna);
        }

        public void setTelefone(String telefone)
        {
            telefoneJTF.setText(telefone);
        }

        public void setEmail(String email)
        {
            emailJTF.setText(email);
        }

        // metodo salvar
        public void salvar()
        {
            PacienteModelo modelo = new PacienteModelo(
            getId() ,
            getNome() ,
            getDataNascimento() ,
            getNumeroDocumento(), 
            getGenero(), 
            getNacionalidade() , 
            getProvincia() , 
            getMunicipio() ,
            getComuna(), 
            getTelefone(),
            getEmail());

            JOptionPane.showMessageDialog(null, modelo.toString());
            
            modelo.salvar();
            dispose();
        }

    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton salvarJBT, cancelarJBT;
        
        public PainelSul()
        {
           setLayout(new FlowLayout());

            add(salvarJBT = new JButton("Salvar", new ImageIcon("image/save24.png")));
            add(cancelarJBT = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            salvarJBT.setBackground(Color.GREEN);
            cancelarJBT.setBackground(Color.RED);

            salvarJBT.setForeground(Color.WHITE);
            cancelarJBT.setForeground(Color.WHITE);
            
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == salvarJBT)
                centro.salvar();
            else
                dispose();
        }
    }

    public void definirTema() 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
                {
                    if ("Nimbus".equals(info.getName())) 
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }
    }


    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new PacienteVisao(false, new PacienteModelo());
    }
}