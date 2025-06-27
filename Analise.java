/*------------------------------------
Tema: Gestão de Consultório Médico
Nome: Celso Vicente
Numero: 33019
Ficheiro: Analise.java
Data: 28/05/2025
--------------------------------------*/

/*
1. Objectivo
Este projeto tem como objetivo gerir informações relacionadas ao atendimento clínico de pacientes,
permitindo o cadastro de pacientes, marcação de consultas, controle de pagamentos e o histórico do paciente.

2. Visao [Interfaces Gráficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipalVisao
- PacienteVisao
- ConsultaVisao
- PagamentoVisao
- HistoricoPacienteVisao

3. Entidades Fortes e Seus Atributos (Modelo)
- PacienteModelo
    int id
    String nome
    String dataNascimento
    String numeroDocumento
    String genero
    String nacionalidade
    String provincia    
    String muncicipio
    String comuna
    String telefone
    String email
    boolean status

- ConsultaModelo
    int id
    PacienteModelo paciente [Fk]
    String medico
    String especialidade
    String dataConsulta
    String horaConsulta
    String observacoes
    boolean status

- PagamentoModelo
    int id
    ConsultaModelo consulta [Fk]
    String descricao
    double valor
    String dataPagamento
    String metodoPagamento
    boolean status

- HistoricoPacienteModelo
    int id
    PacienteModelo paciente [Fk]
    String diagnostico
    String tratamento
    String dataRegistro
    String medicoResponsavel
    boolean status

4. Ficheiro | Persistência de Dados
- PacienteFile.dat
- ConsultaFile.dat
- PagamentoFile.dat
- HistoricoFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Nacionalidades.tab
- Provincias.tab
- Municipios.tab
- Comunas.tab
- Especialidades.tab
- Medicos.tab
- MedicoResponsavel.tab
- MetodoPagamento.tab
- HorariosDisponiveis.tab

6 - Listagens e Pesquisas
Listagem dos Pacientes
Pesquisa por Id
Pesquisa por Nome
Pesquisa por NumeroDocumento
Pesquisa por Genero
Pesquisa por Nacionalidade
Pesquisa Por Telefone

Listagem dos Pagamentos 
Pesquisa por Id
Pesquisa por Descricao

Listagem das Consultas
Pesquisa por Id
Pesquisa por Medico

Listagem do Historico
Pesquisa por Id
Pesquisa por Historico

7. Diversos
7.1 - Implementação: Java Swing
7.2 - IDE:VS Code
*/
