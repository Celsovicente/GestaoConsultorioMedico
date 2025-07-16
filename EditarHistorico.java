/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: EditarHistorico.java
Data: 19.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarHistorico extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EditarHistorico()
    {
        super("Pesquisas do Historico Para Edicao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  idJTF;
        private JComboBox diagnosticoJCB;
        private JRadioButton pesquisarPorId, pesquisarPorDiagnostico;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorDiagnostico = new JRadioButton("Pesquisa Por Diagnostico"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorDiagnostico);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Escolha o Diagnostico Procurado"));
            add(diagnosticoJCB = new JComboBox(HistoricoFile.getAllDiagnosticos()));
            diagnosticoJCB.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorDiagnostico.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDiagnosticoProcurado()
        {
            return String.valueOf(diagnosticoJCB.getSelectedItem());
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
                diagnosticoJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorDiagnostico)
            {
                idJTF.setEnabled(false);
                diagnosticoJCB.setEnabled(true);
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
                HistoricoModelo modelo; 
                if(centro.getTipoPesquisa() == 1)
                {
                   modelo =  HistoricoFile.getPesquisaPorId(centro.getIdProcurado());
                   new HistoricoVisao(true, modelo);
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = HistoricoFile.getPesquisarPorDiagnostico(centro.getDiagnosticoProcurado());
                    new HistoricoVisao(true, modelo);
                }
            }
            else 
                dispose();
        }
    }
}
