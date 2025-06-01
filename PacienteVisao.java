/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PacienteVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PacienteVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;

    public PacienteVisao()
    {
        super("Cadastro dos Pacientes");

        definirTema();
        
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, nomeJTF, dataNascimentoJTF, telefoneJTF, emailJTF, numeroDocumentoJTF;
        private JComboBox generoJCB, nacionalidadeJCB, provinciaJCB, municipioJCB, comunaJCB; 
        public PainelCentro()
        {
            setLayout(new GridLayout(12, 2));

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // 3º linha
            add(new JLabel("Data de Nascimento"));
            add(dataNascimentoJTF = new JTextField());

            // 4º linha
            add(new JLabel("Numero de Documento"));
            add(numeroDocumentoJTF = new JTextField());

            // 5º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox());

            // 6º linha
            add(new JLabel("Nacionalidade"));
            add(nacionalidadeJCB = new JComboBox());

            // 7º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = new JComboBox());

            // 8º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = new JComboBox());

            // 9º linha
            add(new JLabel("Comuna"));
            add(comunaJCB = new JComboBox());

            // 10º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());

            // 11º linha
            add(new JLabel("Email"));
            add(emailJTF = new JTextField());
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
        new PacienteVisao();
    }
}