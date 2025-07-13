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
        
        // Cabeçalhos da tabela
        String[] colunas = {"Id", "Descricao", "Valor", "Data", "Metodo", "Conferencia", "Diocese", "Paroquia", "Data Fundacao"};

        // lista para aramazenar temporariamente os dados
        java.util.List<Object[]>linhas = new java.util.ArrayList<>();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                {
                    Object[] linha = 
                    {
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

            // Converter lista para array de objetos para JTable
            Object[][] dados = new Object[linhas.size()][colunas.length];
            
            for(int i = 0; i < linhas.size(); i++)
            {
                dados[i] = linhas.get(i);
            }

             JTable tabela = new JTable(dados, colunas);
            JScrollPane scroll = new JScrollPane(tabela);
            tabela.setFillsViewportHeight(true);

            JOptionPane.showMessageDialog(null, scroll,
                "Gestao de Consultorio Medico", JOptionPane.INFORMATION_MESSAGE);

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