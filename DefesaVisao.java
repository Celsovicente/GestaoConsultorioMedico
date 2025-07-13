/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: DefesaVisao.java
Data: 14/07/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class DefesaVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;

    public DefesaVisao()
    {

        super("Defesa");

        definirTema();
        
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, descricaoJTF, valorJTF, dataPagamentoJTF, dataFundacaoJTF;
        private JComboBox metodoPagamentoJCB, conferenciaJCB, dioceseJCB,paroquiaJCB;
        private JTextFieldData txtData, txtData2; 
        private JComboBoxTabela3_Tabela3 conferenciaDioceseParoquia;
        private DefesaFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(9, 2));
            conferenciaDioceseParoquia = new JComboBoxTabela3_Tabela3("Conferencias.tab", "Dioceses.tab","Paroquias.tab");
            file = new DefesaFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());

            // 3º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());

            // 4º linha
            add(new JLabel("Data de Pagamento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 5º linha
            add(new JLabel("Metodo de Pagamento"));
            add(metodoPagamentoJCB = UInterfaceBox.createJComboBoxsTabela2("MetodoPagamento.tab"));

            // 6º linha
            add(new JLabel("Conferencia"));
            add(conferenciaJCB = conferenciaDioceseParoquia.getComboBoxFather());

            // 7º linha
            add(new JLabel("Diocese"));
            add(dioceseJCB = conferenciaDioceseParoquia.getComboBoxSun());

            // 8º linha
            add(new JLabel("Paroquia"));
            add(paroquiaJCB = conferenciaDioceseParoquia.getComboBoxNeto());

            // 9º linha
            add(new JLabel("Data de Fundacao"));
            JPanel painelData2 = new JPanel( new GridLayout(1, 1) );
			txtData2 = new JTextFieldData("Data ?");
			painelData2.add( txtData2.getDTestField());
			painelData2.add( txtData2.getDButton());
			add(painelData2);
        }
       
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDescricao()
        {
            return descricaoJTF.getText().trim();
        }   

        public float getValor()
        {
            return Float.parseFloat(valorJTF.getText().trim());
        }

        public String getDataPagamento()
        {
            return txtData.getDTestField().getText();
        }

        public String getMetodoPagamento()
        {
            return String.valueOf(metodoPagamentoJCB.getSelectedItem());
        }

        public String getConferencia()
        {
            return String.valueOf(conferenciaJCB.getSelectedItem());
        }

        public String getDiocese()
        {
            return String.valueOf(dioceseJCB.getSelectedItem());
        }

        public String getParoquia()
        {
            return String.valueOf(paroquiaJCB.getSelectedItem());
        }

        public String getDataFundacao()
        {
            return txtData2.getDTestField().getText();
        }
        
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setDescricao(String descricao)
        {
            descricaoJTF.setText(descricao);
        }   

        public void setValor(float valor)
        {
            valorJTF.setText("" + valor);
        }

        public void setDataPagamento(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setMetodoPagamento(String metodoPagamento)
        {
           metodoPagamentoJCB.setSelectedItem(metodoPagamento);
        }
        
        public void setConferencia(String conferencia)
        {
            conferenciaJCB.setSelectedItem(conferencia);
        }

        public void setDiocese(String diocese)
        {
            dioceseJCB.setSelectedItem(diocese);
        }

        public void setParoqia(String paroquia)
        {
            paroquiaJCB.setSelectedItem(paroquia);
        }

        public void setDataFundacao(String data)
        {
            txtData2.getDTestField().setText(data);
        }

        // metodo salvar
        public void salvar()
        {
            DefesaModelo modelo = new DefesaModelo(
            getId(),
            getDescricao(),
            getValor(),
            getDataPagamento(),
            getMetodoPagamento(),
            getConferencia(),
            getDiocese(),
            getParoquia(),
            getDataFundacao(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // alterar
        public void alterar()
        {
            DefesaModelo modelo = new DefesaModelo(
            getId(),
            getDescricao(),
            getValor(),
            getDataPagamento(),
            getMetodoPagamento(),
            getConferencia(),
            getDiocese(),
            getParoquia(),
            getDataFundacao(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
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
            {
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
        new DefesaVisao();
    }
}