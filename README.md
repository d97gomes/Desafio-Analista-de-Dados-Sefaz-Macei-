# 📊 Desafio Técnico — Análise de Dados Sefaz Maceió

## 🎯 Objetivo

Analisar as despesas das capitais brasileiras (2020–2025), comparando os valores empenhados e pagos por função, com o objetivo de entender a execução orçamentária.

---

## 🏗️ Etapas do Projeto

### 🔹 1. Extração

- Leitura dos arquivos ZIP da pasta `dados_compactos`
- Tratamento de encoding (ISO-8859-1)
- Remoção de metadados

---

### 🔹 2. Transformação

- Conversão dos dados para estrutura organizada (`Despesa`)
- Tratamento de valores monetários
- Filtragem dos estágios relevantes:
  - Despesas Empenhadas
  - Despesas Pagas

---

### 🔹 3. Consolidação

- Integração dos dados de **2020 a 2025**
- Geração de uma base única (`dados_limpos.csv`)

---

### 🔹 4. Análise

- Utilização do DuckDB para consultas SQL performáticas
- Cálculo da taxa de execução:
- Taxa = Pago / Empenhado

---

## 🧪 Tecnologias utilizadas

- Java 21
- Maven
- DuckDB

---

## ▶️ Como executar

1. Executar a classe `Main.java`
2. O sistema irá:
  - Processar todos os anos
  - Gerar o CSV
  - Executar análises
3. Os resultados serão exibidos no console

---

## 📊 Principais Insights

### ✅ Execução Orçamentária

Observou-se que nem sempre as capitais conseguem executar totalmente os valores empenhados, havendo diferenças entre o planejado e o efetivamente pago.

---

### ✅ Diferenças por Função

Algumas funções apresentam menor taxa de execução, indicando maior volume de despesas que ficam como restos a pagar.

---

### ✅ Comparação entre Capitais

Há grande variação entre capitais, com algumas apresentando execução próxima de 100%, enquanto outras possuem desempenho inferior.

---

### ✅ Caso específico: Maceió

A análise de Maceió mostra que a execução varia conforme a função, indicando áreas com maior eficiência e outras com oportunidade de melhoria.

---

### ⚠️ Observação sobre 2025

O ano de 2025 possui dados incompletos, pois nem todas as capitais reportaram suas informações. Por isso, comparações devem ser feitas com cautela.

---

## ✅ Conclusão

O projeto permitiu identificar diferenças na execução orçamentária entre capitais e funções, revelando padrões relevantes e possíveis pontos de melhoria na gestão dos recursos públicos.