/*---------------------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: EliminarConsulta.java
Data: 28.06.2025
--------------------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarConsulta extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarConsulta()
    {
        super("Pesquisas da Consulta Para Eliminacao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        private JTextField  idJTF;
        private JComboBox medicoJCB;
        private JRadioButton pesquisarPorId, pesquisarPorMedico;
        private JComboBoxTabela2_Tabela3 especialidadeMedico;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            especialidadeMedico = new JComboBoxTabela2_Tabela3("Especialidades.tab", "Medicos.tab");
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorMedico = new JRadioButton("Pesquisa Por Medico"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorMedico);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Escolha o Medico Procurado"));
            add(medicoJCB = especialidadeMedico.getComboBoxSun());
            medicoJCB.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorMedico.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getMedicoProcurado()
        {
            return String.valueOf(medicoJCB.getSelectedItem());
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
                medicoJCB.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorMedico)
            {
                idJTF.setEnabled(false);
                medicoJCB.setEnabled(true);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton pesquisarJB, cancelarJB;

        public PainelSul()
        {
            add(pesquisarJB = new JButton("Eliminar", new ImageIcon("image/delete24.PNG")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.PNG")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarJB)
            {    
                ConsultaModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = ConsultaFile.getPesquisaPorId(centro.getIdProcurado());
                    
                    JOptionPane.showMessageDialog(null, modelo.toString());

					int opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja eliminar");

					if (opcao == JOptionPane.YES_OPTION)
					{
						//eliminar dados
						modelo.setStatus(false);
						
						new ConsultaFile().eliminarDados( modelo );
                        dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador!");
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = ConsultaFile.getPesquisarPorMedico(centro.getMedicoProcurado());
                    
                    int opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja eliminar");

					if (opcao == JOptionPane.YES_OPTION)
					{
						//eliminar dados
						modelo.setStatus(false);						
						new ConsultaFile().eliminarDados( modelo );
                        dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador!");
                }
            }
            else 
                dispose();
        }
    }
}
