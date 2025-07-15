/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: ConsultaVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ConsultaVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ConsultaVisao(boolean alterar, ConsultaModelo modelo)
    {
        super("Marcacao das Consultas");

        definirTema();

        editar = alterar;

        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 280);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, dataConsultaJTF; 
        public JComboBox medicoJCB, especialidadeJCB, horaConsultaJCB;
        private JComboBoxTabela2_Tabela3 especialidadeMedico;
        private JTextFieldData txtData;
        private JTextArea observacoesJTA;
        private JScrollPane scroll;
        private ConsultaFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(6, 2));
            especialidadeMedico = new JComboBoxTabela2_Tabela3("Especialidades.tab", "Medicos.tab");
            file = new ConsultaFile();
            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);


            // 2º linha
            add(new JLabel("Especialidade"));
            add(especialidadeJCB = especialidadeMedico.getComboBoxFather());

            // 3º linha
            add(new JLabel("Medico"));
            add(medicoJCB = especialidadeMedico.getComboBoxSun());

            // 4º linha
            add(new JLabel("Data da Consulta"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 5º linha
            add(new JLabel("Hora da Consulta"));
            add(horaConsultaJCB = UInterfaceBox.createJComboBoxsTabela2("HorariosDisponiveis.tab"));

            // 6º linha
            add(new JLabel("Observacoes"));
            add(scroll = new JScrollPane(observacoesJTA = new JTextArea(5,10)));
        }

        public PainelCentro(ConsultaModelo modelo)
        {
            setLayout(new GridLayout(6, 2));
            especialidadeMedico = new JComboBoxTabela2_Tabela3("Especialidades.tab", "Medicos.tab");
            file = new ConsultaFile();
            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" + modelo.getId());
            idJTF.setFocusable(false);


            // 2º linha
            add(new JLabel("Especialidade"));
            add(especialidadeJCB = especialidadeMedico.getComboBoxFather());
            especialidadeJCB.setSelectedItem(modelo.getEspecialidade());

            // 3º linha
            add(new JLabel("Medico"));
            add(medicoJCB = especialidadeMedico.getComboBoxSun());
            medicoJCB.setSelectedItem(modelo.getMedico());

            // 4º linha
            add(new JLabel("Data da Consulta"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataConsulta());

            // 5º linha
            add(new JLabel("Hora da Consulta"));
            add(horaConsultaJCB = UInterfaceBox.createJComboBoxsTabela2("HorariosDisponiveis.tab"));
            horaConsultaJCB.setSelectedItem(modelo.getHoraConsulta());

            // 6º linha
            add(new JLabel("Observacoes"));
            add(scroll = new JScrollPane(observacoesJTA = new JTextArea(5,10)));
            observacoesJTA.setText(modelo.getObservacoes());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getEspecialidade()
        {
            return String.valueOf(especialidadeJCB.getSelectedItem());
        }

        public String getMedico()
        {
            return String.valueOf(medicoJCB.getSelectedItem());
        }
        
        public String getDataConsulta()
        {
            return txtData.getDTestField().getText();
        }

        public String getHoraConsulta()
        {
            return String.valueOf(horaConsultaJCB.getSelectedItem());
        }

        public String getObservacoes()
        {
            return observacoesJTA.getText();
        }

        // metodos setters
        public void setId(int id)
        {
           idJTF.setText("" + id);
        }

        public void setEspecialidade(String especialidade)
        {
            especialidadeJCB.setSelectedItem(especialidade);
        }

        public void setMedico(String medico)
        {
            medicoJCB.setSelectedItem(medico);
        }
        
        public void setDataConsulta(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setHoraConsulta(String hora)
        {
            horaConsultaJCB.setSelectedItem(hora);
        }

        public void setObservacoes(String observacoes)
        {
            observacoesJTA.setText(observacoes);
        }
        
        // metodo para validar os campos
        public boolean validarDados()
        {
            if (getMedico().isEmpty() || getEspecialidade().isEmpty() || getDataConsulta().isEmpty() ||
                getHoraConsulta().isEmpty() || getObservacoes().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            return true;
        } 

        // metodo salvar
        public void salvar()
        {
            if(validarDados())
            {
                ConsultaModelo modelo = new ConsultaModelo(
                    getId(),
                    getMedico(),
                    getEspecialidade(),
                    getDataConsulta(), 
                    getHoraConsulta(),
                    getObservacoes(),
                    true);

                JOptionPane.showMessageDialog(null, modelo.toString());

                modelo.salvar();
                dispose();
            }        
        }

        // metodo alterar
        public void alterar()
        {
            if(validarDados())
            {
                ConsultaModelo modelo = new ConsultaModelo(
                    getId(),
                    getMedico(),
                    getEspecialidade(),
                    getDataConsulta(), 
                    getHoraConsulta(),
                    getObservacoes(),
                    true);

                JOptionPane.showMessageDialog(null, modelo.toString());

                modelo.salvarDados();
                dispose();
            }        
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
            {   
                if(editar) 
                    centro.alterar();
                else
                    centro.salvar();
            }
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
        new ConsultaVisao(false, new ConsultaModelo());
    }
}