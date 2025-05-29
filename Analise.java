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
- HistoricoMedicoVisao

3. Entidades Fortes e Seus Atributos (Modelo)
- PacienteModelo
    int id
    String nome
    String dataNascimento
    String genero
    String telefone
    String email
    String numeroDocumento
    String nacionalidade
    String provincia  
    String muncicipio
    String comuna
    
- ConsultaModelo
    int id
    PacienteModelo paciente [Fk]
    String medico
    String especialidade
    String dataConsulta
    String horaConsulta
    String observacoes

- PagamentoModelo
    int id
    ConsultaModelo consulta [Fk]
    String descricao
    double valor
    String dataPagamento
    String metodoPagamento

- HistoricoModelo
    int id
    PacienteModelo paciente [Fk]
    String diagnostico
    String tratamento
    String dataRegistro
    String medicoResponsavel

4. Ficheiro | Persistência de Dados
- PacienteFile.dat
- ConsultaFile.dat
- PagamentoFile.dat
- HistoricoFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Nacionalidade.tab
- Provincias.tab
- Municipios.tab
- Comunas.tab
- Especialidades.tab
- Medicos.tab
- MetodosPagamento.tab
- HorariosDisponiveis.tab

6. Diversos
6.1 - Implementação: Java Swing
6.2 - IDE:VS Code
*/
