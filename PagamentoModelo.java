/*------------------------------------
Tema: Gestão de Consultorio Medico
Nome: Celso Segunda Vicente
Numero: 33019
Ficheiro: PagamentoModelo.java
Data: 02/06/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoModelo implements RegistGeneric
{
    private int id;
    private float valor;
    private StringBufferModelo descricao, metodoPagamento;
    private DataModelo dataPagamento;
 
    public PagamentoModelo()
    {
        id = 0;
        descricao = new StringBufferModelo("", 20);
        valor = 0;
        dataPagamento = new DataModelo();
        metodoPagamento = new StringBufferModelo("", 20);
    }

    public PagamentoModelo(int id, String descricao, float valor, String dataPagamento, String metodoPagamento)
    {
        this.id = id;
        this.descricao = new StringBufferModelo(descricao, 20);
        this.valor = valor;
        this.dataPagamento = new DataModelo(dataPagamento);
        this.metodoPagamento = new StringBufferModelo(metodoPagamento, 20);
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

    // metodo toString
    public String toString()
    {
        String dados = "Dados do Pagamento \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Descricao: " + getDescricao() + "\n";
        dados += "Valor: " + getValor() + "\n";
        dados += "Data Pagamento: " + getDataPagamento() + "\n";
        dados += "Metodo Pagamento: " + getMetodoPagamento() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 40 * 2 + 4 + 12 + 4;
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
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        PagamentoFile file = new PagamentoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        PagamentoFile file = new PagamentoFile();
        file.alterarDados(this);
    }
}