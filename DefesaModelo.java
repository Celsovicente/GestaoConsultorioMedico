/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: DefesaModelo.java
Data: 14/07/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class DefesaModelo implements RegistGeneric
{
    private int id;
    private float valor;
    private StringBufferModelo descricao, metodoPagamento, conferencia, diocese, paroquia;
    private DataModelo dataPagamento, dataFundacao;
    private boolean status;

    public DefesaModelo()
    {
        id = 0;
        descricao = new StringBufferModelo("", 20);
        valor = 0;
        dataPagamento = new DataModelo();
        metodoPagamento = new StringBufferModelo("", 20);
        conferencia = new StringBufferModelo("", 20);
        diocese = new StringBufferModelo("", 20);
        paroquia = new StringBufferModelo("", 20);
        dataFundacao = new DataModelo();
        status = false;
    }

    public DefesaModelo(int id, String descricao, float valor, String dataPagamento, String metodoPagamento, 
    String conferencia, String diocese, String paroquia, String dataFundacao,boolean status)
    {
        this.id = id;
        this.descricao = new StringBufferModelo(descricao, 20);
        this.valor = valor;
        this.dataPagamento = new DataModelo(dataPagamento);
        this.metodoPagamento = new StringBufferModelo(metodoPagamento, 20);
        this.conferencia = new StringBufferModelo(conferencia, 20);
        this.diocese = new StringBufferModelo(diocese, 20);
        this.paroquia = new StringBufferModelo(paroquia, 20);
        this.dataFundacao = new DataModelo(dataFundacao);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getDescricao()
    {
        return descricao.toStringEliminatingSpaces();
    }

    public float getValor()
    {
        return valor;
    }

    public String getDataPagamento()
    {
        return dataPagamento.toString();
    }

    public String getMetodoPagamento()
    {
        return metodoPagamento.toStringEliminatingSpaces();
    }

    public String getConferencia()
    {
        return conferencia.toStringEliminatingSpaces();
    }

    public String getDiocese()
    {
        return diocese.toStringEliminatingSpaces();
    }

    public String getParoquia()
    {
        return paroquia.toStringEliminatingSpaces();
    }

    public String getDataFundacao()
    {
        return dataFundacao.toString();
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

    public void setDescricao(String descricao)
    {
        this.descricao = new StringBufferModelo(descricao, 20);
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }

    public void setDataPagamento(String dataPagamento)
    {
        this.dataPagamento = new DataModelo(dataPagamento);
    }

    public void setMetodoPagamento(String metodoPagamento)
    {
        this.metodoPagamento = new StringBufferModelo(metodoPagamento, 20);
    }

    public void setConferencia(String conferencia)
    {
        this.conferencia = new StringBufferModelo(conferencia, 20);
    }

    public void setDiocese(String diocese)
    {
        this.diocese = new StringBufferModelo(diocese, 20);
    }

    public void setParoqia(String paroquia)
    {
        this.paroquia = new StringBufferModelo(paroquia, 20);
    }

    public void setDataFundacao(String dataFundacao)
    {
        this.dataFundacao = new DataModelo(dataFundacao);
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
        dados += "Descricao: " + getDescricao() + "\n";
        dados += "Valor: " + getValor() + "\n";
        dados += "Data Pagamento: " + getDataPagamento() + "\n";
        dados += "Metodo Pagamento: " + getMetodoPagamento() + "\n";
        dados += "Conferencia: " + getConferencia() + "\n";
        dados += "Diocese: " + getDiocese()  + "\n";
        dados += "Paroquia: " + getParoquia() + "\n";
        dados += "Data de Fundacao: " + getDataFundacao() + "\n";
        dados += "Estado: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 100 * 2 + 4 + 12 * 2 + 4 + 1;
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
            descricao.write(stream);
            stream.writeFloat(valor);            
            dataPagamento.write(stream);
            metodoPagamento.write(stream);
            conferencia.write(stream);
            diocese.write(stream);
            paroquia.write(stream);
            dataFundacao.write(stream);
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
            descricao.read(stream);
            valor = stream.readFloat();
            dataPagamento.read(stream);
            metodoPagamento.read(stream);
            conferencia.read(stream);
            diocese.read(stream);
            paroquia.read(stream);
            dataFundacao.read(stream);
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
        DefesaFile file = new DefesaFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        DefesaFile file = new DefesaFile();
        file.alterarDados(this);
    }
}