/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: HistoricoModelo.java
Data: 15/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class HistoricoModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo diagnostico,tratamento, medicoResponsavel;
    private DataModelo dataRegistro;
    private boolean status;
 
    public HistoricoModelo()
    {
        id = 0;
        diagnostico = new StringBufferModelo("", 50);
        tratamento = new StringBufferModelo("", 40);
        medicoResponsavel = new StringBufferModelo("", 20);
        dataRegistro = new DataModelo();
        status = false;
    }

    public HistoricoModelo(int id, String diagnostico, String tratamento, String medicoResponsavel, String dataRegistro,
    boolean status)
    {
        this.id = id;
        this.diagnostico = new StringBufferModelo(diagnostico, 50);
        this.tratamento = new StringBufferModelo(tratamento, 40);
        this.medicoResponsavel = new StringBufferModelo(medicoResponsavel, 20);
        this.dataRegistro = new DataModelo(dataRegistro);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getDiagnostico()
    {
        return diagnostico.toStringEliminatingSpaces();
    }

    public String getTratamento()
    {
        return tratamento.toStringEliminatingSpaces();
    }

    public String getMedicoResponsavel()
    {
        return medicoResponsavel.toStringEliminatingSpaces();
    }

    public String getDataRegistro()
    {
        return dataRegistro.toString();
    }

    public boolean getStatus()
    {
        return status;
    }
    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setDiagnostico(String diagnostico)
    {
        this.diagnostico = new StringBufferModelo(diagnostico, 50);
    }

    public void setTratamento(String tratamento)
    {
        this.tratamento = new StringBufferModelo(tratamento, 40);
    }

    public void setMedicoReponsavel(String medicoResponsavel)
    {
        this.medicoResponsavel = new StringBufferModelo(medicoResponsavel, 20);
    }

    public void setDataRegistro(String dataRegistro)
    {
        this.dataRegistro = new DataModelo(dataRegistro);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados do Pagamento \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Diagnostico: " + getDiagnostico() + "\n";
        dados += "Tratamento: " + getTratamento() + "\n";
        dados += "Medico Reponsavel: " + getMedicoResponsavel() + "\n";
        dados += "Data de Registro: " + getDataRegistro() + "\n";
        dados += "Estado: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 110 * 2 + 12 + 4 + 1;
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
            diagnostico.write(stream);
            tratamento.write(stream);
            medicoResponsavel.write(stream);
            dataRegistro.write(stream);
            stream.writeBoolean(status);
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
            diagnostico.read(stream);
            tratamento.read(stream);
            medicoResponsavel.read(stream);
            dataRegistro.read(stream);
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        HistoricoFile file = new HistoricoFile();
        file.salvarDados(this);
    }

    
    public void salvarDados()
    {
        HistoricoFile file = new HistoricoFile();
        file.alterarDados(this);
    }
}