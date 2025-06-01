/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PagamentoVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;

    public PagamentoVisao()
    {
        super("Cadastro dos Pagamentos");

        definirTema();
        
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, descricaoJTF, valorJTF, dataPagamentoJTF;
        private JComboBox metodoPagamentoJCB; 
        public PainelCentro()
        {
            setLayout(new GridLayout(5, 2));

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());

            // 2º linha
            add(new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());

            // 3º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());

            // 4º linha
            add(new JLabel("Data de Pagamento"));
            add(dataPagamentoJTF = new JTextField());

            // 5º linha
            add(new JLabel("Metodo de Pagamento"));
            add(metodoPagamentoJCB = new JComboBox());
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
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
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
        new PagamentoVisao();
    }
}