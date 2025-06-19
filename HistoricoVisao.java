/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: HistoricoVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class HistoricoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public HistoricoVisao(boolean alterar, HistoricoModelo modelo)
    {
        super("Cadastro do Historico do Paciente");

        editar = alterar;

        definirTema();
        
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, diagnosticoJTF, dataRegistroJTF, medicoResponsavelJTF;
        private JTextArea tratamentoJTA;
        private JScrollPane scroll;
        private HistoricoFile file;
        private JTextFieldData txtData;

        public PainelCentro()
        {
            setLayout(new GridLayout(5, 2));
            file = new HistoricoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);
            
            // 2º linha
            add(new JLabel("Diagnostico"));
            add(diagnosticoJTF = new JTextField());

            // 3º linha
            add(new JLabel("Para Tratamento"));
            add(scroll = new JScrollPane(tratamentoJTA = new JTextArea(5,10)));   

            // 4º linha
            add(new JLabel("Data de Registro"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 5º linha
            add(new JLabel("Medico Responsavel"));
            add(medicoResponsavelJTF = new JTextField());
        }

        public PainelCentro(HistoricoModelo modelo)
        {
            setLayout(new GridLayout(5, 2));
            file = new HistoricoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" + modelo.getId());
            idJTF.setFocusable(false);
            
            // 2º linha
            add(new JLabel("Diagnostico"));
            add(diagnosticoJTF = new JTextField());
            diagnosticoJTF.setText(modelo.getDiagnostico());

            // 3º linha
            add(new JLabel("Para Tratamento"));
            add(scroll = new JScrollPane(tratamentoJTA = new JTextArea(5,10)));   
            tratamentoJTA.setText(modelo.getTratamento());

            // 4º linha
            add(new JLabel("Data de Registro"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataRegistro());

            // 5º linha
            add(new JLabel("Medico Responsavel"));
            add(medicoResponsavelJTF = new JTextField());
            medicoResponsavelJTF.setText(modelo.getMedicoResponsavel());
        }
        
         // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDiagnostico()
        {
            return diagnosticoJTF.getText().trim();
        }

        public String getTratamento()
        {
            return tratamentoJTA.getText();
        }

        public String getMedicoResponsavel()
        {
            return medicoResponsavelJTF.getText().trim();
        }

        public String getDataRegistro()
        {
            return txtData.getDTestField().getText();
        }

        // metodos setters
        public void setId(int id)
        {
           idJTF.setText("" +id);
        }

        public void setDiagnostico(String diagnostico)
        {
            diagnosticoJTF.setText(diagnostico);
        }

        public void setTratamento(String tratamento)
        {
            tratamentoJTA.setText(tratamento);
        }

        public void setMedicoResponsavel(String medicoResponsavel)
        {
            medicoResponsavelJTF.setText(medicoResponsavel);
        }

        public void setDataRegistro(String data)
        {
            txtData.getDTestField().setText(data);
        }

        // metodo salvar
        public void salvar()
        {
            HistoricoModelo modelo = new HistoricoModelo(
            getId(),
            getDiagnostico(),
            getTratamento(),
            getMedicoResponsavel(),
            getDataRegistro());

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
        new HistoricoVisao(false, new HistoricoModelo());
    }
}