/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: EliminarPacientes.java
Data: 05.07.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarPacientes extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarPacientes()
    {
        super("Pesquisas Para Eliminacao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        private JComboBox nomesJCB , numeroDocumentoJCB, generoJCB, telefoneJCB, nacionalidadeJCB;
        private JTextField idJTF;
        private JRadioButton pesquisarPorNome, pesquisarPorDocumento, pesquisarPorId, 
        pesquisarPorTelefone, pesquisarPorGenero, pesquisarPorNacionalidade;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(9, 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorNome = new JRadioButton("Pesquisa Por Nome"));
            add(pesquisarPorDocumento = new JRadioButton("Pesquisa Pelo Numero do Documento"));
            add(pesquisarPorId = new JRadioButton("Pesquisar Por Id"));
            add(pesquisarPorTelefone = new JRadioButton("Pesquisar Por Telefone"));
            add(pesquisarPorGenero = new JRadioButton("Pesquisar Por Genero"));
            add(pesquisarPorNacionalidade = new JRadioButton("Pequisar Por Nacionalidade"));

            grupo.add(pesquisarPorNome);
            grupo.add(pesquisarPorDocumento);
            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorTelefone);
            grupo.add(pesquisarPorGenero);
            grupo.add(pesquisarPorNacionalidade);
            
            add(new JLabel("Escolha o Nome Procurado"));
            add(nomesJCB = new JComboBox (PacienteFile.getAllNames()));
            nomesJCB.setEnabled(false);

            add(new JLabel("Digite o numero do Documento Procurado"));
            add(numeroDocumentoJCB = new JComboBox(PacienteFile.getAllNumeroDocumento()));
            numeroDocumentoJCB.setEnabled(false);

            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);

            add(new JLabel("Escolha o Telefone Procurado"));
            add(telefoneJCB = new JComboBox(PacienteFile.getAllTelefone()));
            telefoneJCB.setEnabled(false);

            add(new JLabel("Digite o Genero Procurado"));
            add(generoJCB = new JComboBox(PacienteFile.getAllGenero()));
            generoJCB.setEnabled(false);

            add(new JLabel("Digite a Nacionalidade Procurada"));
            add(nacionalidadeJCB = new JComboBox(PacienteFile.getAllNacionalidade()));
            nacionalidadeJCB.setEnabled(false);
            
            pesquisarPorNome.addActionListener(this);
            pesquisarPorDocumento.addActionListener(this);
            pesquisarPorId.addActionListener(this);
            pesquisarPorTelefone.addActionListener(this); 
            pesquisarPorGenero.addActionListener(this);
            pesquisarPorNacionalidade.addActionListener(this);
        }

        public String getNomeProcurado()
        {
            return String.valueOf(nomesJCB.getSelectedItem());
        }

        public String getNumeroDocumentoProcurado()
        {
            return String.valueOf(numeroDocumentoJCB.getSelectedItem());
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getTelefoneProcurado()
        {
            return String.valueOf(telefoneJCB.getSelectedItem());
        }
        
        public String getGeneroProcurado()
        {
            return String.valueOf(generoJCB.getSelectedItem());
        }

        public String getNacionalidadeProcurada()
        {
            return String.valueOf(nacionalidadeJCB.getSelectedItem());
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
            else if(pesquisarPorGenero.isSelected())
                return 5;
            else 
                return 6;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorNome)
            {
                nomesJCB.setEnabled(true);
                numeroDocumentoJCB.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJCB.setEnabled(false);
                generoJCB.setEnabled(false);
                nacionalidadeJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorDocumento)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJCB.setEnabled(true);
                idJTF.setEnabled(false);
                telefoneJCB.setEnabled(false);
                generoJCB.setEnabled(false);
                nacionalidadeJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorId)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJCB.setEnabled(false);
                idJTF.setEnabled(true);
                telefoneJCB.setEnabled(false);
                generoJCB.setEnabled(false);
                nacionalidadeJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorTelefone)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJCB.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJCB.setEnabled(true);
                generoJCB.setEnabled(false);
                nacionalidadeJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorGenero)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJCB.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJCB.setEnabled(false);
                generoJCB.setEnabled(true);   
                nacionalidadeJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorNacionalidade)
            {
                nomesJCB.setEnabled(false);
                numeroDocumentoJCB.setEnabled(false);
                idJTF.setEnabled(false);
                telefoneJCB.setEnabled(false);
                generoJCB.setEnabled(false);   
                nacionalidadeJCB.setEnabled(true);    
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
                PacienteModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = PacienteFile.getPesquisarPorNome(centro.getNomeProcurado());

                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = PacienteFile.getPesquisarPorNumeroDocumento(centro.getNumeroDocumentoProcurado());

                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                else if(centro.getTipoPesquisa() == 3)
                {
                    modelo = PacienteFile.getPesquisarPorId(centro.getIdProcurado());
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }   
                else if(centro.getTipoPesquisa() == 4)
                {
                    modelo = PacienteFile.getPesquisarPorTelefone(centro.getTelefoneProcurado());
                
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                else if(centro.getTipoPesquisa() == 5)
                {
                    modelo = PacienteFile.getPesquisarPorGenero(centro.getGeneroProcurado());

                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                          
                else if(centro.getTipoPesquisa() == 6)
                {
                    modelo = PacienteFile.getPesquisarPorNacionalidade(centro.getNacionalidadeProcurada());

                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new PacienteFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
            }
            else 
                dispose();
        }
    }
}
