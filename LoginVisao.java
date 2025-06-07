/*------------------------------------
Tema: Gestão de Um Centro de Formação Profissional
Nome: Celso Vicente
Numero: 33019
Ficheiro: loginVisao.java
Data: 07.06.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class LoginVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;

    public LoginVisao()
    {
        super("Tela de Login");

        JPanel painelNorte = new JPanel();
        JLabel lbImagem = new JLabel(new ImageIcon("image/descarregar(1).jpj"));
        painelNorte.add(lbImagem);
        getContentPane().add(painelNorte , BorderLayout.NORTH);
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        
        //pack();
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel 
    {   
        private JTextField userJTF;
        private JPasswordField passwordJTF;
        private String corretUser = "33019", corretPassword = "ucan";

        public PainelCentro()
        {
            setLayout(new GridLayout(2, 2));
            
            add(new JLabel("Username:"));
            add(userJTF = new JTextField());

            add(new JLabel("Password:"));
            add(passwordJTF = new JPasswordField());
            passwordJTF.setEchoChar('#');
        }

        public String getUser()
        {
            return userJTF.getText().trim();
        }

        public String getPassword()
        {
            return passwordJTF.getText().trim();
        }

        public boolean loginValido()
        {
            if(getUser().equals(corretUser) && getPassword().equalsIgnoreCase(corretPassword))
            return true;

            return false;
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;
        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));


            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == entrarJB)
            {
                if(centro.loginValido())
                {
                    String user = centro.getUser();
                    dispose();
                    new MenuPrincipal(user);
                }
                else
                    JOptionPane.showMessageDialog(null, "Login Invalido, tente novamente!", "Invalid Login", JOptionPane.ERROR_MESSAGE);   
            }
            else
                dispose();
        }
    }

}