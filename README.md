# Segware Test

Teste para vaga de desenvolvedor Java Back-end para integrações.

## Instruções para rodar o projeto

Ler atentamente as instruções abaixo.

Elas constam no codigo também.

```bash
        ***************************************************************************
        * PARA O FLUXO FUNCIONAR CORRETAMENTA, SIGA OS PASSOS ABAIXO              *
        *                                                                         *
        *-------------------------------------------------------------------------*
        * 1 - INSERIR OS VALORES NO FORMATO JSON. ESTA VERSÃO DO PROGRAMA POR     *
        * ENQUANTO ACEITA APENAS ENTRADAS EM FORMATO JSON                         *
        *                                                                         *
        * 2 - AO INSERIR OS VALORES E NÃO OBTER A RESPOSTA DESEJADA, POR FAVOR    *
        * TENTAR NOVAMENTE COM OS MESMOS DADOS OU OUTROS DADOS.                   *
        *                                                                         *
        * 3 - QUALQUER ENTRADA FORA DO PADRÃO SOLICITADO NÃO FUNCIONARÁ           *
        *                                                                         *
        * 4 - NÃO FOI NOTADA DIFERENÇA ENTRE INICIAR O CLIENTE OU SERVER PRIMEIRO *
        ***************************************************************************
```

## String em formato JSON a ser inserido

```python
{"init": "0x0A","bytes": "0x09","frame": "0x01","textMessage": "48 65 6C 6C 6F 20 57 6F 72 6C 64","crc": "0xA1","end": "0xA2"}

{"init": "0x0A","bytes": "0x09","frame": "0x01","textMessage": "31323334","crc": "0xA1","end": "0xA2"}

{"init": "0x0A","bytes": "0x09","frame": "0x03","date": "416D65726963612F53616F5F5061756C6F","crc": "0xA1","end": "0xA2"}

{"init": "0x0A","bytes": "0x20","frame": "0xA2","idade": "0x20","peso": "0x7A","altura": "0xC3","tamanhoNome": "0x0C","nome": "0x4D 0x69 0x63 0x68 0x65 0x6C 0x20 0x52 0x65 0x69 0x70 0x73","crc": "0xA1","end": "0xA2"}
```

## Requisitos

```python
- Java 11
- Maven
- Spring Boot
```

## IDE

```python
- Intellij IDEA Ultimate
- Intellij Community
```


## Local onde arquivos estão sendo salvos

```python
- C:\teste
  Obs: Não foi testado nem implementado em ambiente Linux/MacOS. Apenas Windows
```

## O que o programa faz

```python
Cliente
- Recebe entrada no cliente com o formato informado
- Salva os valores em um arquivo txt no local informado acima e permite escolher nome
- Recebe informações do servidor e exibe na tela de acordo com o tipo da mensagem

Server
- Recebe a mensagem vinda do cliente
- Faz verificação da entrada para ver se contém o campo frame, se tiver, identifica qual o tipo
- Faz tratamento de acordo com o tipo do frame para salvar em banco
- Possui checagem de CRC
- Armazena cada tipo de mensagem em sua tabela no banco
- Salva os valores em um arquivo txt no local informado acima e permite escolher nome

```
