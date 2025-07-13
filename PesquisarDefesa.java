/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PesquisarDefesa.java
Data: 16.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarDefesa extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public PesquisarDefesa()
    {
        super("Pesquisas da Defesa");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        private JTextField dataFundacaoJTF;
        private JComboBox conferenciaJCB, dioceseJCB, paroquiaJCB;
        private JRadioButton pesquisarConferencia, pesquisarDiocese, pesquisarParoquia, pesquisarData;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(8 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarConferencia = new JRadioButton("Pesquisa Por Conferencia"));
            add(pesquisarDiocese = new JRadioButton("Pesquisa Por Diocese"));
            add(pesquisarParoquia = new JRadioButton("Pesquisar Por Paroquia"));
            add(pesquisarData = new JRadioButton("Pesquisar Por Data de Fundacao"));

            grupo.add(pesquisarConferencia);
            grupo.add(pesquisarDiocese);
            grupo.add(pesquisarParoquia);
            grupo.add(pesquisarData);
            
            add(new JLabel("Escolha a Conferencia Procurada"));
            add(conferenciaJCB = new JComboBox());
            conferenciaJCB.setEnabled(false);
            
            add(new JLabel("Escolha a Diocese Procurada"));
            add(dioceseJCB = new JComboBox());
            dioceseJCB.setEnabled(false);

            add(new JLabel("Escolha Paroquia Procurada"));
            add(paroquiaJCB = new JComboBox());
            paroquiaJCB.setEnabled(false);

            add(new JLabel("Digite a Data Procurada"));
            add(dataFundacaoJTF = new JTextField());
            dataFundacaoJTF.setEnabled(false);
            
            pesquisarConferencia.addActionListener(this);
            pesquisarDiocese.addActionListener(this);
            pesquisarParoquia.addActionListener(this);
            pesquisarData.addActionListener(this);
        }

        public String getConferenciaProcurada() 
        {
            return String.valueOf(conferenciaJCB.getSelectedItem());
        }

        public String getDioceseProcurada()
        {
            return String.valueOf(dioceseJCB.getSelectedItem());
        }

        public String getParoquiaProcurada()
        {
            return String.valueOf(paroquiaJCB.getSelectedItem());
        }

        public String getDataProcurada()
        {
            return dataFundacaoJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarConferencia.isSelected())
                return 1;
            else if(pesquisarDiocese.isSelected())
                return 2;
            else if(pesquisarParoquia.isSelected())
                return 3;
            else
                return 4;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarConferencia)
            {
                conferenciaJCB.setEnabled(true);
                dioceseJCB.setEnabled(false);
                paroquiaJCB.setEnabled(false);
                dataFundacaoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarDiocese)
            {
                conferenciaJCB.setEnabled(false);
                dioceseJCB.setEnabled(true);
                paroquiaJCB.setEnabled(false);
                dataFundacaoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarParoquia)
            {
                conferenciaJCB.setEnabled(false);
                dioceseJCB.setEnabled(false);
                paroquiaJCB.setEnabled(true);
                dataFundacaoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarData)
            {
                conferenciaJCB.setEnabled(false);
                dioceseJCB.setEnabled(false);
                paroquiaJCB.setEnabled(false);
                dataFundacaoJTF.setEnabled(true);
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
                    DefesaFile.pesquisarPorConferencia(centro.getConferenciaProcurada());
                else if(centro.getTipoPesquisa() == 2)
                    DefesaFile.pesquisarPorDiocese(centro.getDioceseProcurada());
                else if(centro.getTipoPesquisa() == 3)
                    DefesaFile.pesquisarPorParoquia(centro.getParoquiaProcurada());
                else if(centro.getTipoPesquisa() == 4)
                    DefesaFile.pesquisarPorDataFundacao(centro.getDataProcurada());
            }
            else 
                dispose();
        }
    }
}
