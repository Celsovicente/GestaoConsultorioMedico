/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PesquisarConsulta.java
Data: 16.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarConsulta extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public PesquisarConsulta()
    {
        super("Pesquisas da Consulta");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        private JTextField  idJTF, medicoJTF;
        private JRadioButton pesquisarPorId, pesquisarPorMedico;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorMedico = new JRadioButton("Pesquisa Por Medico"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorMedico);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Digite o Medico Procurado"));
            add(medicoJTF = new JTextField());
            medicoJTF.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorMedico.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getMedicoProcurado()
        {
            return medicoJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorId.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorId)
            {
                idJTF.setEnabled(true);
                medicoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorMedico)
            {
                idJTF.setEnabled(false);
                medicoJTF.setEnabled(true);
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
                    ConsultaFile.pesquisarPorId(centro.getIdProcurado());
                else if(centro.getTipoPesquisa() == 2)
                    ConsultaFile.pesquisarPorMedico(centro.getMedicoProcurado());
            }
            else 
                dispose();
        }
    }
}
