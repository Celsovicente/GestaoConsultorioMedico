/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: DefesaFile.java
Data: 14/07/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class DefesaFile extends ObjectsFile
{
    public DefesaFile()
    {
        super("DefesaFile.dat", new DefesaModelo());
    }  

    public void salvarDados(DefesaModelo modelo)
    {
        try
        {
            // colocar o file pointer no final do ficheiro
            stream.seek(stream.length());

            // escrever no modelo
            modelo.write(stream);

            incrementarProximoCodigo();
            JOptionPane.showMessageDialog(null,  "Dados Salvos com Sucessso");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao Salvar os Dados");
        }
    }

    public void alterarDados(DefesaModelo modelo_novo)
	{
		DefesaModelo modelo_antigo = new DefesaModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public static void listarDefesa() 
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();

        String[] colunas = 
        {
            "Id", "Descricao", "Valor", "Data", "Metodo de Pagamento","Conferencia", "Diocese", 
            "Paroquia", "Data Fundacao"
        };

        java.util.List<Object[]> linhas = new java.util.ArrayList<>();

        try {
            file.stream.seek(4);

            for (int i = 0; i < file.getNregistos(); i++) 
            {
                modelo.read(file.stream);

                if (modelo.getStatus()) {
                    Object[] linha = {
                        modelo.getId(),
                        modelo.getDescricao(),
                        modelo.getValor(),
                        modelo.getDataPagamento(),
                        modelo.getMetodoPagamento(),
                        modelo.getConferencia(),
                        modelo.getDiocese(),
                        modelo.getParoquia(),
                        modelo.getDataFundacao()
                    };
                    linhas.add(linha);
                }
            }

            Object[][] dados = new Object[linhas.size()][colunas.length];
            for (int i = 0; i < linhas.size(); i++) 
            {
                dados[i] = linhas.get(i);
            }

            JTable tabela = new JTable(dados, colunas);
            tabela.setFillsViewportHeight(true);

            // ESSENCIAL: desativa redimensionamento automático para permitir scroll horizontal
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            // Você pode definir larguras mínimas para cada coluna se quiser
            for (int i = 0; i < colunas.length; i++) {
                tabela.getColumnModel().getColumn(i).setPreferredWidth(150);
            }

            JScrollPane scroll = new JScrollPane(tabela,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JDialog dialogo = new JDialog();
            dialogo.setTitle("Gestao de Consultorio Medico - Lista de Defesas");
            dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogo.setLayout(new BorderLayout());
            dialogo.add(scroll, BorderLayout.CENTER);
            dialogo.setSize(1000, 500); 
            dialogo.setLocationRelativeTo(null);
            dialogo.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static StringVector getAllConferencias()
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getConferencia());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

    public static StringVector getAllDioceses()
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getDiocese());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

    public static StringVector getAllParoquias()
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getParoquia());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

    public static void pesquisarPorConferencia(String conferenciaProcurada)
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getConferencia().equalsIgnoreCase(conferenciaProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, conferencia nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void pesquisarPorDiocese(String dioceseProcurada)
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDiocese().equalsIgnoreCase(dioceseProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, diocese nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void pesquisarPorParoquia(String paroquiaProcurada)
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getParoquia().equalsIgnoreCase(paroquiaProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, paroquia nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void pesquisarPorDataFundacao(String dataProcurada)
    {
        DefesaFile file = new DefesaFile();
        DefesaModelo modelo = new DefesaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDataFundacao().equalsIgnoreCase(dataProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, data de fundacao nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}