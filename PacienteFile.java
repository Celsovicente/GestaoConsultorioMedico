/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PacienteFile.java
Data: 07/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PacienteFile extends ObjectsFile
{
    public PacienteFile()
    {
        super("PacienteFile.dat", new PacienteModelo());
    }  

    public void salvarDados(PacienteModelo modelo)
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

    public void alterarDados(PacienteModelo modelo_novo)
	{
		PacienteModelo modelo_antigo = new PacienteModelo();
		
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

    public void eliminarDados(PacienteModelo modelo_novo)
	{
		PacienteModelo modelo_antigo = new PacienteModelo();
		
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

    public static void listarPacientes()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        String dados = "Listagem dos Dados do Paciente\n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                {    
                    dados += "==============================\n";
                    dados += modelo.toString() + "\n\n";
                }
            }

            JTextArea area = new JTextArea(40 , 60);
            area.setText(dados);
            area.setFocusable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(area),
            "Gestao de Consultorio Medico", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // getPacientePorNome
    public static void pesquisarPorNome(String nomeProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNome().equalsIgnoreCase(nomeProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return;
    }

    public static StringVector getAllNames()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getNome());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

    public static void pesquisarPorNumeroDocumento(String numeroDocumentoProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNumeroDocumento().equalsIgnoreCase(numeroDocumentoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, numero do documento nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static int pesquisarPorId(int idProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
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

    public static void pesquisarPorTelefone(String telefoneProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getTelefone().equalsIgnoreCase(telefoneProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, telefone nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //pesquisarPorGenero
    public static void pesquisarPorGenero(String generoProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getGenero().equalsIgnoreCase(generoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, genero nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // pesquisarPorNacionalidade
    public static void pesquisarPorNacionalidade(String nacionalidadeProcurada)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNacionalidade().equalsIgnoreCase(nacionalidadeProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, nacionalidade nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // pesquisas para edicao
    public static PacienteModelo getPesquisarPorId(int idProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
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

    // getPesquisarPorNome 
    public static PacienteModelo getPesquisarPorNome(String nomeProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNome().equalsIgnoreCase(nomeProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                
                JOptionPane.showMessageDialog(null, "Erro, nome nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    // getPesquisarPorNumeroDocumento 
    public static PacienteModelo getPesquisarPorNumeroDocumento(String numeroDocumentoProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNumeroDocumento().equalsIgnoreCase(numeroDocumentoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, numero documento nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    // getPesquisarPorTelefone 
    public static PacienteModelo getPesquisarPorTelefone(String telefoneProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getTelefone().equalsIgnoreCase(telefoneProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, telefone nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    // getPesquisarPorGenero 
    public static PacienteModelo getPesquisarPorGenero(String generoProcurado)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getGenero().equalsIgnoreCase(generoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, genero nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }


    // getPesquisarPorNacionalidade 
    public static PacienteModelo getPesquisarPorNacionalidade(String nacionalidadeProcurada)
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        boolean encontrado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getNacionalidade().equalsIgnoreCase(nacionalidadeProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    encontrado = true;
                    return modelo;
                }
            }
            if(!encontrado)
            {
                JOptionPane.showMessageDialog(null, "Erro, nacionalidade nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    public static StringVector getAllNumeroDocumento()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getNumeroDocumento());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

    public static StringVector getAllTelefone()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getTelefone());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

        public static StringVector getAllGenero()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getGenero());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

        public static StringVector getAllNacionalidade()
    {
        PacienteFile file = new PacienteFile();
        PacienteModelo modelo = new PacienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getNacionalidade());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }
}