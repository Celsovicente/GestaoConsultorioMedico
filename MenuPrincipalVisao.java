/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: MenuPrincipalVisao.java
Data: 31/05/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class MenuPrincipalVisao extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu menuPaciente, menuConsulta, menuPagamento, menuHistorico, menuTabelas, menuAjuda, 
    menuListagem, menuDefesa;
    
    private JMenuItem novoPacienteItem, editarPacienteItem, eliminarPacienteItem, sairPacienteItem;
    private JMenuItem novaConsultaItem, editarConsultaItem, eliminarConsultaItem, sairConsultaItem;
    private JMenuItem novoPagamentoItem, editarPagamentoItem, eliminarPagamentoItem, sairPagamentoItem;
    private JMenuItem novoHistoricoItem, editarHistoricoItem, eliminarHistoricoItem, sairHistoricoItem;
    private JMenuItem nacionalidadeItem, municipioItem,  comunaItem, provinciaItem, metodoPagamentoItem, 
    especialidadeItem, horariosDisponiveisItem, medicoItem;
    private JMenuItem listarPacientesItem, listarPagamentosItem, listarConsultasItem, listarHistoricoItem,
    listarDefesaItem;
    private JMenuItem novaDefesaItem,pesquisarPacientesItem, pesquisarPagamentosItem, pesquisarHistoricoItem, 
    pesquisarConsultaItem, pesquisarDefesaItem;

    public MenuPrincipalVisao()
    {
        super("Menu Principal");

        instanciarObjectos();

        setJMenuBar(menuBar);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void instanciarObjectos()
    {
        menuBar = new JMenuBar();

        // adicionando os elementos no menuBar
        menuBar.add(menuPaciente = new JMenu("Paciente"));
        menuPaciente.setMnemonic('P');
        menuPaciente.setIcon(new ImageIcon("image/funcionario32.png"));
        menuBar.add(menuConsulta = new JMenu("Consulta"));
        menuConsulta.setMnemonic('C');
        //menuConsulta.setIcon(new ImageIcon(""));
        menuBar.add(menuHistorico = new JMenu("Historico"));
        menuHistorico.setMnemonic('H');
        //menuHistorico.setIcon(new ImageIcon(""));
        menuBar.add(menuPagamento = new JMenu("Pagamento"));
        menuPagamento.setMnemonic('P');
        //menuPagamento.setIcon(new ImageIcon(""));
        menuBar.add(menuListagem = new JMenu("Listagens/Pesquisas"));
        menuListagem.setMnemonic('L');
        menuListagem.setIcon(new ImageIcon("image/search32.png"));
        menuBar.add(menuTabelas = new JMenu("Tabelas"));
        menuTabelas.setMnemonic('T');
        menuTabelas.setIcon(new ImageIcon("image/tabela.png"));
        menuBar.add(menuAjuda = new JMenu("Ajuda"));
        menuAjuda.setMnemonic('A');
        menuAjuda.setIcon(new ImageIcon("image/help.png"));
        menuBar.add(menuDefesa = new JMenu("Defesa"));
        menuDefesa.setMnemonic('D');

        // instanciando os elementos do menuPaciente
        menuPaciente.add(novoPacienteItem = new JMenuItem("Novo Paciente", new ImageIcon("image/novo24.png")));
        novoPacienteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menuPaciente.add(editarPacienteItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        menuPaciente.add(eliminarPacienteItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));
        menuPaciente.addSeparator();
        menuPaciente.add(sairPacienteItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));

        // instanciando os elementos do menuConsulta
        menuConsulta.add(novaConsultaItem = new JMenuItem("Nova Consulta", new ImageIcon("image/novo24.png")));
        novaConsultaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuConsulta.add(editarConsultaItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        menuConsulta.add(eliminarConsultaItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));
        menuConsulta.addSeparator();
        menuConsulta.add(sairConsultaItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));

        // instanciando os elementos do menuHistorico
        menuHistorico.add(novoHistoricoItem = new JMenuItem("Novo Historico", new ImageIcon("image/novo24.png")));
        novoHistoricoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        menuHistorico.add(editarHistoricoItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        menuHistorico.add(eliminarHistoricoItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));
        menuHistorico.addSeparator();
        menuHistorico.add(sairHistoricoItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));

        //instanciando os elementos do menuPagamento
        menuPagamento.add(novoPagamentoItem = new JMenuItem("Novo Pagamento", new ImageIcon("image/novo24.png")));
        novoPagamentoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menuPagamento.add(editarPagamentoItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        menuPagamento.add(eliminarPagamentoItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));
        menuPagamento.addSeparator();
        menuPagamento.add(sairPagamentoItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));

        // instanciando os elementos do menuListagem
        menuListagem.add(listarPacientesItem = new JMenuItem("Listar Pacientes"));
        menuListagem.add(pesquisarPacientesItem = new JMenuItem("Pesquisar Pacientes"));
        menuListagem.addSeparator();
        menuListagem.add(listarConsultasItem = new JMenuItem("Listar Consultas"));
        menuListagem.add(pesquisarConsultaItem = new JMenuItem("Pesquisar Consultas"));
        menuListagem.addSeparator();
        menuListagem.add(listarHistoricoItem = new JMenuItem("Listar Historico"));
        menuListagem.add(pesquisarHistoricoItem = new JMenuItem("Pesquisar Historico"));
        menuListagem.addSeparator();
        menuListagem.add(listarPagamentosItem = new JMenuItem("Listar Pagamentos"));
        menuListagem.add(pesquisarPagamentosItem = new JMenuItem("Pesquisar Pagamento"));
        
        // instanciando o menu defesa
        menuDefesa.add(novaDefesaItem = new JMenuItem("Cadastrar Defesa"));
        menuDefesa.add(listarDefesaItem = new JMenuItem("Listar Defesas"));
        menuDefesa.add(pesquisarDefesaItem = new JMenuItem("Pesquisar Defesa"));

        // instancindo os elementos do menuTabela
        menuTabelas.add(nacionalidadeItem = new JMenuItem("Nacionalidades"));
        menuTabelas.add(provinciaItem = new JMenuItem("Provincias"));
        menuTabelas.add(municipioItem = new JMenuItem("Municipios"));
        menuTabelas.add(comunaItem = new JMenuItem("Comunas"));
        menuTabelas.add(metodoPagamentoItem = new JMenuItem("Metodos de Pagamento"));
        menuTabelas.add(especialidadeItem = new JMenuItem("Especialidades"));
        menuTabelas.add(medicoItem = new JMenuItem("Medicos"));
        menuTabelas.add(horariosDisponiveisItem = new JMenuItem("Horarios Disponiveis"));   

        //adicionando evento de click
        novoPacienteItem.addActionListener(this);
        listarPacientesItem.addActionListener(this);
        pesquisarPacientesItem.addActionListener(this);
        sairPacienteItem.addActionListener(this);
        
        novaConsultaItem.addActionListener(this);
        listarConsultasItem.addActionListener(this);
        pesquisarConsultaItem.addActionListener(this);
        sairConsultaItem.addActionListener(this);
        
        novoHistoricoItem.addActionListener(this);
        listarHistoricoItem.addActionListener(this);
        pesquisarHistoricoItem.addActionListener(this);
        sairHistoricoItem.addActionListener(this);
        
        novoPagamentoItem.addActionListener(this);
        listarPagamentosItem.addActionListener(this);
        pesquisarPagamentosItem.addActionListener(this);
        sairPagamentoItem.addActionListener(this);

        listarDefesaItem.addActionListener(this);
        pesquisarDefesaItem.addActionListener(this);

        nacionalidadeItem.addActionListener(this);
        provinciaItem.addActionListener(this);
        comunaItem.addActionListener(this);
        municipioItem.addActionListener(this);
        metodoPagamentoItem.addActionListener(this);
        medicoItem.addActionListener(this);
        especialidadeItem.addActionListener(this);
        horariosDisponiveisItem.addActionListener(this);
        
        }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == novoPacienteItem)
            new PacienteVisao();
        else if(event.getSource() == novoHistoricoItem)
            new HistoricoVisao();
        else if(event.getSource() == novaConsultaItem)
            new ConsultaVisao();
        else if(event.getSource() == novoPagamentoItem)
            new PagamentoVisao();
        else if(event.getSource() == listarPacientesItem)
            PacienteFile.listarPacientes();
        else if(event.getSource() == nacionalidadeItem)
            Tabela2.editarNovosItems("Nacionalidades.tab", "Nova Nacionalidade");
        else if(event.getSource() == metodoPagamentoItem)
            Tabela2.editarNovosItems("MetodoPagamento.tab", "Novo Metodo de Pagamento");
        else if(event.getSource() == horariosDisponiveisItem)
            Tabela2.editarNovosItems("HorariosDisponiveis.tab", "Novo Horario Disponivel");
        else if(event.getSource() == provinciaItem)
            Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
        else if(event.getSource() == municipioItem)
            Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincia", "Municipio", 
            "Novo Municipio");
        else if(event.getSource() == comunaItem)
            Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", "Provincia", 
            "Municipio", "Comuna", "Nova Comuna");
        else if(event.getSource() == especialidadeItem)
            Tabela2.editarNovosItems("Especialidades.tab", "Nova Especialidade");
        else if(event.getSource() == medicoItem)
            Tabela3_2.editarNovosItems("Especialidades.tab", "Medicos.tab", "Especialidade", "Medico", 
            "Novo Medico");
        else if(event.getSource() == sairPacienteItem)
            dispose();
        else if(event.getSource() == sairHistoricoItem)
            dispose();
        else if(event.getSource() == sairConsultaItem)
            dispose();
        else if(event.getSource() == sairPagamentoItem)
            dispose();
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new MenuPrincipalVisao();
    }
}