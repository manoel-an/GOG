<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://adrai.github.io/flowchart.js/flowchart-latest.js"></script>
<script>
	window.onload = function () {
		var diagram = flowchart.parse("st=>start: Iniciar\n" + 
			"e=>end\n" + 
			"op=>operation: Configurar o Banco de Dados\n" + 
			"op0=>operation: Configurar o projeto no Eclipse\n" + 
			"op1=>operation: Configurar o Jboss\n" + 
			"op2=>operation: Executar carga inicial\n" + 
			"op3=>operation: Ajustar as configurações\n" + 
			"cond=>condition: Aplicação Montada?\n" + 
			"st->op0->op->op1->cond\n" + 
			"cond(yes)->op2->op3->e\n" + 
			"cond(no)->op");
		diagram.drawSVG('diagram');
	};
</script>

# Ambiente de Desenvolvimento
Neste documento apresentamos informações necessárias para realizar a montagem, configuração e utilização do ambiente de desenvolvimento do sistema GOG. 

As versões dos pacotes de instalação foram expressamente declaradas neste documentos com o simples objetivo de refletir a realidade vivenciada pela equipe de desenvolvimento do MinC. A intenção é garantir que este guia possa conduzir seu leitor a uma instalação de sucesso; entretanto, nada impede que o leitor tente utilizar novas versões das aplicações ou pacotes aqui citados.


## Montagem do ambiente de desenvolvimento

<div id='diagram' />

1. Preparação do ambiente
   * Configuração do sistema operacional
     * Instalação do Git
     * Instalação do Java JDK 8
     * Instalação do JBoss AS 7.1.1.Final
   * Configurar o Eclipse
     * Configurar projeto no Eclipse
2. Configurar o Banco de Dados
3. Configurar o Jboss
4. Executar carga inicial
5. Ajustar as configurações


## Preparação do ambiente

### Configuração do sistema operacional
Utilizamos o linux como sistema operacional e adotamos o Ubuntu como distribuição preferida. 

#### Instalação do Git
Faz-se necessária a instalação do git para integração com o repositório, o que pode ser configurado com a utilização dos seguintes comandos:

> **Instalação do GIT:**
```
$ sudo apt-get install git
```

> **Definição do repositório**

> * Definir o diretório do sistema
```
$ sudo mkdir /opt/desenv/GOG
$ cd /opt/desenv/GOG
```

> * Clonar o git no diretório definido
>   * *Utilizar o texto "**HTTPS clone URL**" oferecido no repositório do projeto*
```
$ git clone https://github.com/culturagovbr/GOG.git
```

#### Instalação do Java JDK 8

```
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```

#### Instalação do JBoss AS 7.1.1.Final
* O Jboss pode ser instalado a partir do download do arquivo disponibilizado na página:
```
http://jbossas.jboss.org/downloads
```


### Configurar o Eclipse

Instalar a versão do eclipse. Sugerimos dar preferência à versão **Eclipse Luna Package - Eclipse IDE for Java Developers** disponível para desenvolvedores Java JEE
```
http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr2
```

Instalar o plugin JbossTools (*JBoss Tools 4.2.3.Final*), para utilizar o servidor de aplicação na IDE, dentre outras facilidades
```
http://tools.jboss.org/downloads/jbosstools/luna/4.2.3.Final.html
```

#### Configuração do projeto no Eclipse
O projeto foi desenvolvido em Java, com uso do Maven. O arquivo "pom.xml" pode ser adequamente utilizado para importar o projeto em um IDE.

No Eclipse basta solicitar a importação de um novo projeto (Import ==> Import Existing Maven Projects) e apontar para a raiz do projeto.

## Configuração do Banco de Dados
Inicialmente, o projeto GOG foi desenvolvido para a plataforma SQLServer, utilizando a API EclipseLink para o mapeamento objeto relacional - ORM.
A versão mais atual do projeto passou a utilizar JPA (com hibernate), mantendo o mapeamento objeto relacional mais transparente. 

Com o mapeamento JPA, podemos utlizar qualquer gerenciador de banco de dados, bastando ajustar os drivers e os arquivos de configuração. Com isto, podemos utilizar o SGBD Postgresql como Banco de Dados do projeto GOG.

### Configuração do dataSource no projeto (arquivo "persistence.xml") 
Path: /GOG/src/main/resources/META-INF/persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
	<persistence-unit name="ouvidoriaPersistenceUnit">
		<jta-data-source>java:/datasources/GOGDSPostreSQL</jta-data-source>
		<properties>
			<!-- Properties for Hibernate -->
			<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect" />
			<property name="hibernate.hbm2ddl.auto" value="create-drop" /> 
			<property name="hibernate.format_sql" value="true" />
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.enable_lazy_load_no_trans" value="true" />
			<property name="jboss.as.jpa.providerModule" value="org.hibernate" />
		</properties>
	</persistence-unit>
</persistence>
```

> **Nota:** 

> - A criação da DLL do banco de dados pode ser comentada no arquivo de persistence, após a primeira disponibilização do projeto. Este procedimento garante a instalação do banco de dados durante a primeira execução do sistema.
```
<!-- 			<property name="hibernate.hbm2ddl.auto" value="create-drop" /> -->
```

> - Pode-se configurar a aplicação para não exibir os scritps SQL durante a execução do sistema, alterando o valor da propriedade.
```
 			<property name="hibernate.show_sql" value="false" /> 
```


## Configuração do Jboss (arquivo "standalone.xml")

A configuração do Jboss é feita basicamente em um único arquivo de propriedades, o 'standalone.xml', para ajustar a conexão com o banco de dados e para configurar os parâmetros de autenticação e segurança, conforme os seguintes passos:

- <i class="icon-pencil"></i> Criar o datasource: O data source precisa definir o jndi-name="java:/datasources/GOGDSPostreSQL" e as configurações de conexão com o banco de dados.

> - Para configurar o datasource no Jboss 7.1.1 será necessário criar e configurar um "module" para o funcionamento do driver do Banco de Dados ('jar' contendo as classes para acesso ao banco).
```xml
                <datasource jndi-name="java:/datasources/GOGDSPostreSQL" pool-name="GOGDSPostreSQL" enabled="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:postgresql://localhost:5432/GOG</connection-url>
                    <driver>org.postgresql</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <pool>
                        <min-pool-size>5</min-pool-size>
                        <max-pool-size>50</max-pool-size>
                        <prefill>true</prefill>
                        <use-strict-min>false</use-strict-min>
                        <flush-strategy>FailingConnectionOnly</flush-strategy>
                    </pool>
                    <security>
                        <user-name>java</user-name>
                        <password>java</password>
                    </security>
                    <timeout>
                        <set-tx-query-timeout>true</set-tx-query-timeout>
                        <blocking-timeout-millis>5000</blocking-timeout-millis>
                        <idle-timeout-minutes>15</idle-timeout-minutes>
                    </timeout>
                    <statement>
                        <track-statements>false</track-statements>
                        <prepared-statement-cache-size>100</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
                <drivers>
                    <driver name="org.postgresql" module="org.postgresql">
                        <driver-class>org.postgresql.Driver</driver-class>
                    </driver>
                    <driver name="JTDS" module="net.sourceforge.jtds">
                        <driver-class>net.sourceforge.jtds.jdbc.Driver</driver-class>
                    </driver>
                </drivers>
```

> **Criar o security domain - configuração de autenticação**
```xml
<security-domain name="OuvidoriaSecurityDomain">
	<authentication>
		<login-module code="br.com.xti.ouvidoria.security.OuvidoriaLoginModule" flag="required"/>
	</authentication>
</security-domain>
```

## Configuração e atualização dos módulos (jars) do JBOSS.

> **Criação do módulo para conexão com Banco de Dados Postgres**
> - Crie uma estrutura de diretórios correspondente ao jar do driver do Postgres, a partir do diretório "modules" do Jboss. 
>   - Exemplo: jboss-7.1.1/modules/org/postgresql/main
> - Inclua o arquivo do driver do Postgres 
> - Crie um arquivo "module.xml" com o seguinte conteúdo:
```xml
<?xml version="1.0" encoding="UTF-8"?>
  <module xmlns="urn:jboss:module:1.0" name="org.postgresql">
  <resources>
    <resource-root path="postgresql-9.4-1201.jdbc4.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

> **Upgrade das versões dos jars nos módulos do hibernate e javassist**
> - Versões utilizadas: **Hibernate 4.2.20.Final** e **Javassist 3.20.0-GA**
> - Para cada um desses diretórios já existentes no JBOSS:
>   - jboss-7.1.1/modules/org/hibernate/envers/main
>   - jboss-7.1.1/modules/org/hibernate/main
>   - jboss-7.1.1/modules/org/javassist/main
>      - Delete os arquivos **.index**.
>      - Substitua os jars existentes pelas versões atualizadas.
>      - Modifique os nomes dos jars no arquivo **module.xml**.
>      - Todos os arquivos module.xml serão modificados apenas na tag **resources**.

> - Exemplo, o diretório **jboss-7.1.1/modules/org/javassist/main** conterá o jar atualizando e o arquivo.xml com o nome do jar atualizado da seguinte forma: 

```xml
<?xml version="1.0" encoding="UTF-8"?>

<module xmlns="urn:jboss:module:1.1" name="org.javassist">
    <properties>
        <property name="jboss.api" value="private"/>
    </properties>

    <resources>
        <resource-root path="javassist-3.20.0-GA.jar"/>
    </resources>
    <!-- Nomes atualizados aqui /\ -->

    <dependencies>
    </dependencies>
</module>
```

## Executar carga inicial

A carga inicial do sistema, com o povoamento dos dados das tabelas de domínio utilizadas no sistema, deve ser realizada com a execução do script disponibilizado no arquivo src/main/resources/ScriptRevisado.sql

### Este script contém a tabela de parâmetros essenciais da aplicação

```sql
INSERT INTO TbPreferenciaSistema
	(idPreferenciaSistema, nomeOuvidoria, emailOuvidoria, hostEmail, portaEmail, usuarioEmail, 
	senhaEmail, sslEmail, encerrarTramiteEncaminhada, retornarTramiteOuvidoria, ctlPrazoManifSoluc,
	RespostasImediatas, prazoEntrada, prazoAreaSolucionadora, prazoRespostaCidadao)
VALUES 
	(1, 'Ouvidoria MinC', 'naoresponda.ouvidoria@cultura.gov.br', '10.0.0.54', 25, 'ouvidoria@cultura.gov.br',
	'', '2', '1', '1', '1', 
	'1', 1, 28, 1);
```

A tabela 'TbPreferenciaSistema' também pode ser editada internamente no sistema, utilizando-se de um usuário cadastrado como "Administrador" e acessando o menu "Sistema" - "Preferências do Sistema".

### Contém a configuração/criação de usuários do sistema:

```sql
--tbUsuario
/*
Insere um usuário Administrador (tpFuncao=5) com a senha 123456 e login = 'root'. Este usuário deve ser criado para permitir a configuração interna do sistema,
como a inclusão de novos usuários.
*/
INSERT INTO tbUsuario 
	(idUsuario, nmUsuario, stStatus, eeEmail, idUnidade, tpUsuario, nmLogin, 
	numTelefone, nmSenha, tpFuncao) 
VALUES 
	(1, 'root', 1, 'email@email.com', 1, '1', 'root', 
	'(61)99999999', 'E10ADC3949BA59ABBE56E057F20F883E', '5');

```

## Configuração JPAModel
Botão direito mouse no projeto, navegue na opção run as -> maven build ...
Em Goals digite clean install em seguida run
Toda configruação será iniciada, o processo pode levar alguns minutos. Será criada no diretório target/metamodel algumas classes, as mesmas devem ser mapeadas no projeto conforme passos abaixo:
Botão direito mouse no projeto -> Java Build Path -> Add Folder -> Adicione o diretório criado (target/metamodel)


> **Nota:** 

> - Um usuário com função de Administrador tem permissão de editar o cadastro de usuários do sistema e de acessar as funcionalidades disponíveis.

