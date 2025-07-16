/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PagamentoFile.java
Data: 07/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoFile extends ObjectsFile
{
    public PagamentoFile()
    {
        super("PagamentoFile.dat", new PagamentoModelo());
    }  

    public void salvarDados(PagamentoModelo modelo)
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

    public void alterarDados(PagamentoModelo modelo_novo)
	{
		PagamentoModelo modelo_antigo = new PagamentoModelo();
		
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

    public void eliminarDados(PagamentoModelo modelo_novo)
	{
		PagamentoModelo modelo_antigo = new PagamentoModelo();
		
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
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write(stream);
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

    public static void listarPagamentos()
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        
        // Cabeçalhos da tabela
        String[] colunas = {"Id", "Descricao", "Valor", "Data", "Metodo"};

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
                        modelo.getMetodoPagamento()
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

    public static int pesquisarPorId(int idProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return 0;
                }
            }
            if(!encontrado)
            { 
                JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idProcurado;
    }

    public static void pesquisarPorDescricao(String descricaoProcurada)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDescricao().equalsIgnoreCase(descricaoProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return;
                }
            }

            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, descricao nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static PagamentoModelo getPesquisaPorId(int idProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    public static PagamentoModelo getPesquisaPorDescricao(String descricaoProcurada)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDescricao().equalsIgnoreCase(descricaoProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, descricao nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }
}