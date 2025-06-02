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

public class PagamentoModelo 
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
}