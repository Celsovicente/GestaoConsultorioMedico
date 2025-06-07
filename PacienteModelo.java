/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PacienteModelo.java
Data: 02/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PacienteModelo implements RegistGeneric
{  
    private int id;
    private StringBufferModelo nome, numeroDocumento, genero, nacionalidade, provincia, municipio, 
    comuna, telefone, email; 
    private DataModelo dataNascimento;

    public PacienteModelo()
    {
        id = 0;
        nome = new StringBufferModelo("", 50);
        dataNascimento = new DataModelo();
        numeroDocumento = new StringBufferModelo("", 30);
        genero = new StringBufferModelo("", 10);
        nacionalidade = new StringBufferModelo("", 20);
        provincia = new StringBufferModelo("", 20);
        municipio = new StringBufferModelo("", 20);
        comuna = new StringBufferModelo("", 20);
        telefone = new StringBufferModelo("", 20);
        email = new StringBufferModelo("", 30);
    }

    public PacienteModelo(int id, String nome, String dataNascimento, String numeroDocumento, String genero, 
    String nacionalidade, String provincia, String municipio, String comuna, String telefone, String email)
    {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.dataNascimento = new DataModelo(dataNascimento);
        this.numeroDocumento = new StringBufferModelo(numeroDocumento, 30);
        this.genero = new StringBufferModelo(genero, 10);
        this.nacionalidade = new StringBufferModelo(nacionalidade, 20);
        this.provincia = new StringBufferModelo(provincia, 20);
        this.municipio = new StringBufferModelo(municipio , 20);
        this.comuna = new StringBufferModelo(comuna , 20);
        this.telefone = new StringBufferModelo(telefone , 20);
        this.email = new StringBufferModelo(email , 30);
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome.toStringEliminatingSpaces();
    }

    public String getDataNascimento()
    {
        return dataNascimento.toString();
    }

    public String getNumeroDocumento()
    {
        return numeroDocumento.toStringEliminatingSpaces();
    }

    public String getGenero()
    {
        return genero.toStringEliminatingSpaces();
    }

    public String getNacionalidade()
    {
        return nacionalidade.toStringEliminatingSpaces();
    }

    public String getProvincia()
    {
        return provincia.toStringEliminatingSpaces();
    }

    public String getMunicipio()
    {
        return municipio.toStringEliminatingSpaces();
    }

    public String getComuna()
    {
        return comuna.toStringEliminatingSpaces();
    }

    public String getTelefone()
    {
        return telefone.toStringEliminatingSpaces();
    }

    public String getEmail()
    {
        return email.toStringEliminatingSpaces();
    }
   
   // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setNome(String nome)
    {
        this.nome = new StringBufferModelo(nome, 50); 
    }

    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = new DataModelo(dataNascimento);
    }
        
    public void setNumeroDocumento(String numeroDocumento)
    {
        this.numeroDocumento = new StringBufferModelo(numeroDocumento, 30);
    }    
        
    public void setGenero(String genero)
    {
        this.genero = new StringBufferModelo(genero, 10);
    }
        
    public void setNacionalidade(String nacionalidade)
    {
        this.nacionalidade = new StringBufferModelo(nacionalidade, 20);
    }

    public void setProvincia(String provincia)
    {
        this.provincia = new StringBufferModelo(provincia, 20);
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = new StringBufferModelo(municipio , 20);
    }

    public void setComuna(String comuna)
    {
        this.comuna = new StringBufferModelo(comuna , 20);
    }
    
    public void setTelefone(String telefone)
    {
        this.telefone = new StringBufferModelo(telefone , 20);
    }

    public void setEmail(String email)
    {
        this.email = new StringBufferModelo(email , 30);
    }
        
    // metodo toString
    public String toString()
    {
        String dados = "Dados do Paciente: \n";

        dados += "Id: " + getId() + "\n";
        dados += "Nome: " + getNome() + "\n";
        dados += "Data de Nascimento: " + getDataNascimento() + "\n";
        dados += "Numero do Documento: " + getNumeroDocumento() + "\n";
        dados += "Genero: " + getGenero() + "\n";
        dados += "Nacionalidade: " + getNacionalidade() + "\n";
        dados += "Provincia: " + getProvincia() + "\n";
        dados += "Municipio: " + getMunicipio() + "\n";
        dados += "Comuna: " + getComuna() + "\n";
        dados += "Telefone: " + getTelefone() + "\n";
        dados += "Email: " + getEmail() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 220 * 2 + 4 + 12;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    // metodo write
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.writeInt(id);
            nome.write(stream);
            dataNascimento.write(stream);
            numeroDocumento.write(stream);
            genero.write(stream);
            nacionalidade.write(stream);
            provincia.write(stream);
            municipio.write(stream);
            comuna.write(stream);
            telefone.write(stream);
            email.write(stream);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no Ficheiro");
        }
    }

    // metodo read
    public void read(RandomAccessFile stream)
    {
        try
        {
            id = stream.readInt();
            nome.read(stream);
            dataNascimento.read(stream);
            numeroDocumento.read(stream);
            genero.read(stream);
            nacionalidade.read(stream);
            provincia.read(stream);
            municipio.read(stream);
            comuna.read(stream);
            telefone.read(stream);
            email.read(stream);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        PacienteFile file = new PacienteFile();
        file.salvarDados(this);
    }
}