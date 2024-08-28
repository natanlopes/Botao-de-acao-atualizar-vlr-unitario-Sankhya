# Botão de Ação atualizar o Valor unitario dos itens Sankhya



# Atualização de Valores Unitários

Este projeto contém um código Java para atualizar o valor unitário de produtos em uma nota fiscal específica. O código também gera um relatório em formato HTML que é exibido ao usuário, indicando quais produtos tiveram seus valores unitários atualizados.

## Estrutura do Código

O código está dividido em duas partes principais:

1. **Método `doAction(ContextoAcao ctx)`**: 
   - Este método é responsável por iterar sobre os registros selecionados, atualizando o valor unitário de cada item na nota fiscal. 
   - Além disso, ele cria uma mensagem de sucesso em formato HTML, que exibe os produtos processados e seus respectivos valores unitários.

2. **Método `AtualizaeVlrUnitario(BigDecimal nota, Double vlrunitario, BigDecimal seq)`**:
   - Este método realiza a atualização do valor unitário no banco de dados para um item específico em uma nota fiscal, identificada por `NUNOTA` e `SEQUENCIA`.

## Funcionalidade Detalhada

### Método `doAction(ContextoAcao ctx)`

- **Parâmetros:**
  - `ctx`: Contexto da ação, que contém informações sobre os registros selecionados e os parâmetros fornecidos.

- **Fluxo de execução:**
  - A função começa construindo uma mensagem HTML que será exibida ao usuário após o processamento.
  - Em seguida, itera sobre cada registro (`Registro`) selecionado:
    - Obtém os valores `CODPROD`, `NUNOTA`, `SEQUENCIA` e o novo `VLRUNIT`.
    - Chama o método `AtualizaeVlrUnitario` para atualizar o valor unitário no banco de dados.
    - Adiciona informações do produto processado à mensagem HTML.
  - Ao final, a mensagem HTML é configurada como mensagem de retorno do contexto.

### Método `AtualizaeVlrUnitario(BigDecimal nota, Double vlrunitario, BigDecimal seq)`

- **Parâmetros:**
  - `nota`: Número único da nota fiscal (`NUNOTA`).
  - `vlrunitario`: Novo valor unitário a ser aplicado.
  - `seq`: Sequência do item na nota fiscal (`SEQUENCIA`).

- **Fluxo de execução:**
  - O método abre uma sessão JDBC e executa uma consulta SQL para atualizar o campo `VLRUNIT` da tabela `TGFITE`, baseado nos parâmetros fornecidos.
  - Em caso de falha, uma exceção é lançada com a mensagem de erro.

## Exemplo de Saída HTML

A saída HTML gerada pelo método `doAction` terá a seguinte estrutura:

```
 html
<!DOCTYPE html>
<html>
<body>
    <div style='text-align: center;'>
        <img src='https://argofruta.com/wp-content/uploads/2021/05/Logo-text-green.png' style='width:120px; height:90px;'>
    </div>
    <div style='display: flex; align-items: center; justify-content: center;'>
        <img src='https://cdn-icons-png.flaticon.com/256/189/189677.png' style='width:23px; height:23px; margin-right:5px;'>
        <p style='color:#274135; font-family:verdana; font-size:15px; margin: 0;'><b>Atualização de Valores Unitários</b></p>
    </div>
    <p style='font-family:courier; color:#274135;'>Produtos processados e valores unitários foram: <br><br>
        12345 - Valor Unitário: 12.34<br>
        67890 - Valor Unitário: 56.78<br>
        <!-- Outros produtos -->
    </p>
</body>
</html>
```

