/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: ApresentacaoVisao.java
Data: 07/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class ApresentacaoVisao extends JFrame 
{
    
    private PainelCentro centro;
    private PainelSul sul;

    public ApresentacaoVisao()
    {
        super("Tela de Boas Vindas");

        JPanel painelNorte = new JPanel();
        JLabel lbImagem = new JLabel(new ImageIcon("image/images.jpg"));
        painelNorte.add(lbImagem);
        getContentPane().add(painelNorte , BorderLayout.NORTH);
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
            JTextArea areaPrincipal;
            JCheckBox concordarJCB;
            public PainelCentro()
            {
                setLayout(new GridLayout(2, 1));
                
                add(new JScrollPane(areaPrincipal = new JTextArea(80 , 60)));
                areaPrincipal.setFocusable(false);
                areaPrincipal.setText("Bem Vindo ao Sistema de Gestao do Consultorio Medico.\n" +
                "\tTema: Gestao de Consultorio Medico\n" +
                "Este projecto tem o objectivo de gerir informacoes  relacionadas ao atendimento clinico de pacientes\n" +
                "permitindo o cadastro de pacientes, marcacao de consultas, controle de pagamentos e o historico do paciente." +
                "Este projeto foi desenvolvido no ambito da cadeira de Fundamentos de Programacao 2,\n" +
                "no Curso de Engenharia Informatica na UCAN. E de uso exclusivo aos Recursos Humanos.\n" +
                "Este projeto foi desenvolvido para Facilitar o Controlo e Gestao da inofrmacao sobre os pacientes do consultorio\n" +
                "medico, permitindo localizar os dados de forma concisa e segura.\n" +
                "Este projeto foi desenvolvido por Celso Segunda Vicente Estudante do I ano id: 33019.\n" +
                "Se concorda com os termos e condicoes clique em Concordar para Continuar");

                add(concordarJCB = new JCheckBox("Concordo com os termos a seguir"));

                concordarJCB.addActionListener(this);
            }

            public void actionPerformed(ActionEvent event)
            {
                if(event.getSource() == concordarJCB)
                    if(concordarJCB.isSelected())
                        sul.ativarBotao(true);
                    else
                        sul.ativarBotao(false);
            }
    }

    
    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;
        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));

            ativarBotao( false );

            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void ativarBotao(boolean status)
        {
            entrarJB.setEnabled(status);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == entrarJB)
            {
                dispose();
                new LoginVisao();
            }
            else
                dispose();
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new ApresentacaoVisao();       
    }
}