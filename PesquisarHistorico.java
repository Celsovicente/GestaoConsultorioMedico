/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PesquisarHistorico.java
Data: 16.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarHistorico extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public PesquisarHistorico()
    {
        super("Pesquisas do Historico");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  idJTF, diagnosticoJTF;
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
            
            add(new JLabel("Digite o Diagnostico Procurado"));
            add(diagnosticoJTF = new JTextField());
            diagnosticoJTF.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorDiagnostico.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDiagnosticoProcurado()
        {
            return diagnosticoJTF.getText().trim();
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
                diagnosticoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorDiagnostico)
            {
                idJTF.setEnabled(false);
                diagnosticoJTF.setEnabled(true);
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
                    HistoricoFile.pesquisarPorId(centro.getIdProcurado());
                else if(centro.getTipoPesquisa() == 2)
                    HistoricoFile.pesquisarPorDiagnostico(centro.getDiagnosticoProcurado());
            }
            else 
                dispose();
        }
    }
}
