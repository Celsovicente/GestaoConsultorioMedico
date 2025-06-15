/*---------------------------------------------------
Tema: Gestão de um Consultorio Medico
Nome: Celso Vicente
Numero: 33019
Ficheiro: PesquisarPaciente.java
Data: 15.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarPaciente extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public PesquisarPaciente()
    {
        super("Pesquisas");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        private JComboBox nomesJCB;
        private JTextField numeroDocumentoJTF, idJTF, generoJTF, telefoneJTF;
        private JRadioButton pesquisarPorNome, pesquisarPorDocumento, pesquisarPorId, 
        pesquisarPorTelefone, pesquisarPorGenero;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(10, 5));
            
            grupo = new ButtonGroup();

            add(pesquisarPorNome = new JRadioButton("Pesquisa Por Nome"));
            add(pesquisarPorDocumento = new JRadioButton("Pesquisa Pelo Numero do Documento"));
            add(pesquisarPorId = new JRadioButton("Pesquisar Por Id"));
            add(pesquisarPorTelefone = new JRadioButton("Pesquisar Por Telefone"));
            add(pesquisarPorGenero = new JRadioButton("Pesquisar Por Genero"));

            grupo.add(pesquisarPorNome);
            grupo.add(pesquisarPorDocumento);
            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorTelefone);
            grupo.add(pesquisarPorGenero);
            
            add(new JLabel("Escolha o Nome Procurado"));
            add(nomesJCB = new JComboBox (PacienteFile.getAllNames()));
            nomesJCB.setEnabled(false);

            add(new JLabel("Digite o numero do Documento Procurado"));
            add(numeroDocumentoJTF = new JTextField());
            numeroDocumentoJTF.setEnabled(false);

            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);

            add(new JLabel("Escolha o Telefone Procurado"));
            add(telefoneJTF = new JTextField());
            telefoneJTF.setEnabled(false);

            add(new JLabel("Digite o Genero Procurado"));
            add(generoJTF = new JTextField());
            generoJTF.setEnabled(false);
            
            pesquisarPorNome.addActionListener(this);
            pesquisarPorDocumento.addActionListener(this);
            pesquisarPorId.addActionListener(this);
            pesquisarPorTelefone.addActionListener(this); 
            pesquisarPorGenero.addActionListener(this);
        }

        public String getNomeProcurado()
        {
            return String.valueOf(nomesJCB.getSelectedItem());
        }

        public String getNumeroDocumentoProcurado()
        {
            return numeroDocumentoJTF.getText().trim();
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getTelefoneProcurado()
        {
            return telefoneJTF.getText().trim();
        }
        
        public String getGeneroProcurado()
        {
            return generoJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorNome.isSelected())
                return 1;
            else if(pesquisarPorDocumento.isSelected())
                return 2;
            else if(pesquisarPorId.isSelected())
                return 3;
            else if(pesquisarPorTelefone.isSelected())
                return 4;
            else
                return 5;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorNome)
            {
                nomesJCB.setEnabled(true);
                numeroDocumentoJTF.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJTF.setEnabled(false);
                generoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorDocumento)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJTF.setEnabled(true);
                idJTF.setEnabled(false);
                telefoneJTF.setEnabled(false);
                generoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorId)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJTF.setEnabled(false);
                idJTF.setEnabled(true);
                telefoneJTF.setEnabled(false);
                generoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorTelefone)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJTF.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJTF.setEnabled(true);
                generoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorGenero)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJTF.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJTF.setEnabled(false);
                generoJTF.setEnabled(true);   
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton pesquisarJB, cancelarJB;

        public PainelSul()
        {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.PNG")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.PNG")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarJB)
            {    
                if(centro.getTipoPesquisa() == 1)
                    PacienteFile.pesquisarPorNome(centro.getNomeProcurado());
                else if(centro.getTipoPesquisa() == 2)
                    PacienteFile.pesquisarPorNumeroDocumento(centro.getNumeroDocumentoProcurado());
                else if(centro.getTipoPesquisa() == 3)
                    PacienteFile.pesquisarPorId(centro.getIdProcurado());
                else if(centro.getTipoPesquisa() == 4)
                    PacienteFile.pesquisarPorTelefone(centro.getTelefoneProcurado());
                else if(centro.getTipoPesquisa() == 5)
                    PacienteFile.pesquisarPorGenero(centro.getGeneroProcurado());          
            }
            else 
                dispose();
        }
    }
}
