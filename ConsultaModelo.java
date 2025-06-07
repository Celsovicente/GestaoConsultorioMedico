/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: ConsultaModelo .java
Data: 07/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ConsultaModelo implements RegistGeneric
{

    private int id;
    private DataModelo dataConsulta;
    private StringBufferModelo especialidade, medico,horaConsulta, observacoes;

    public ConsultaModelo()
    {
        id = 0;
        medico = new StringBufferModelo("", 40);
        especialidade = new StringBufferModelo("", 30);
        dataConsulta = new DataModelo();
        horaConsulta = new StringBufferModelo("", 20);
        observacoes = new StringBufferModelo("", 50);
    }

    public ConsultaModelo(int id, String medico, String especialidade, String dataConsulta, String horaConsulta,
    String observacoes)
    {
        this.id = id;
        this.medico = new StringBufferModelo(medico, 40);
        this.especialidade = new StringBufferModelo(especialidade, 30);
        this.dataConsulta = new DataModelo(dataConsulta);
        this.horaConsulta = new StringBufferModelo(horaConsulta, 20);
        this.observacoes = new StringBufferModelo(observacoes, 50);
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getMedico()
    {
        return medico.toStringEliminatingSpaces();
    }

    public String getEspecialidade()
    {
        return especialidade.toStringEliminatingSpaces();
    }

    public String getDataConsulta()
    {
        return dataConsulta.toString();
    }

    public String getHoraConsulta()
    {
         return horaConsulta.toStringEliminatingSpaces();
    }

    public String getObservacoes()
    {
        return observacoes.toStringEliminatingSpaces();
    }

    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setMedico(String medico)
    {
        this.medico = new StringBufferModelo(medico, 40);
    }

    public void setEspecialidade(String especialidade)
    {
        this.especialidade = new StringBufferModelo(especialidade, 30);
    }

    public void setDataConsulta(String dataConsulta)
    {
        this.dataConsulta = new DataModelo(dataConsulta);
    }

    public void setHoraConsulta(String horaConsulta)
    {
        this.horaConsulta = new StringBufferModelo(horaConsulta, 20);
    }

    public void setObservacoes(String observacoes)
    {
        this.observacoes = new StringBufferModelo(observacoes, 50);
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados Da Consulta: \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Medico: " + getMedico() + "\n";
        dados += "Especialidade: " + getEspecialidade() + "\n";
        dados += "Data da Consulta: " + getDataConsulta() + "\n";
        dados += "Hora da Consulta: " + getHoraConsulta() + "\n";
        dados += "Observacoes: " + getObservacoes() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 140 * 2 + 4 + 12;
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
            medico.write(stream);
            especialidade.write(stream);
            dataConsulta.write(stream);
            horaConsulta.write(stream);
            observacoes.write(stream);
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
            medico.read(stream);
            especialidade.read(stream);
            dataConsulta.read(stream);
            horaConsulta.read(stream);
            observacoes.read(stream);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        ConsultaFile file = new ConsultaFile();
        file.salvarDados(this);
    }
}