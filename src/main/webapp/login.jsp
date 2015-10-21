<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<title>Sistema de Ouvidoria</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<meta http-equiv="refresh" content="3600;url=/ouvidoria" />
	<link rel="stylesheet" href="${context}/resources/css/modal.css" />
	<link rel="stylesheet" href="${context}/resources/css/estilo.css" />
	<link rel="stylesheet" href="${context}/resources/css/estiloGoogle.css" />
	<script src="${context}/resources/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$.get("/ouvidoria/aviso", function(data) {
				if(data.titulo) {
					$("#avisoTitulo").append(data.titulo);
					$("#avisoConteudo").append(data.conteudo);
					$(".modal").show();
				}
			}, "json" );
		})
	</script>
</head>

<body>
	<div class="global">
		<div class="bgSistemaOuvidoria">
			<div class="cabecalho2">
				<div class="logo-externa">
					<img src="${context}/img/logo-ouvidoria-baloes.png"/>
				</div>
			</div>
			<!-- conteúdo Login -->
			<form method="post" action="${context}/login">
				<div class="conteudo">
					<div class="combo-login">
						<div class="topoLoginBox"></div>
						<div class="conteudoLogin">
							<div class="mensagem">
								<ul>
									<c:forEach var="error" items="${errors}">
										<li>
											<c:out value="${error}" />
										</li>
									</c:forEach>
									<c:choose>
										<c:when test="${param.logout == 'expired'}">
											<li>Sessão Expirada</li>
										</c:when>
									</c:choose>
								</ul>
							</div>
							<div class="panelLogin">
								<div class="alingBox">
									<div class="loginBox">
										<label for="username" style="font-family: 'Open Sans'; font-size: 14px">Login:</label>&nbsp;&nbsp;
										<input type="text" name="username" style="font-family: sans-serif; font-size: 12px" size="24" />
									</div>
									<div class="loginBoxPw">
										<label for="password" style="font-family: 'Open Sans'; font-size: 14px">Senha:</label>&nbsp;&nbsp;
										<input type="password" name="password" style="font-family: sans-serif; font-size: 12px" size="24" />
									</div>
								</div>

								<div class="alingButton">
									<div class="rSenha">
										<a href="${context}/recuperarsenha.xhtml">Recuperar Senha</a>
									</div>
									<div class="dadosCadastro1">
										<button type="submit" style="border: none;"
												class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left">
											<span class="ui-button-text">ACESSAR</span>
										</button>
									</div>
								</div>
								<div class="cadastroUsuariosOuvidoria">
									Não é cadastrado?
									<a href="${context}/pages/externo/cadastrarManifestante.xhtml">Cadastre-se</a>
								</div>
							</div>
						</div>

						<div class="dadosCadastro2">
							<div class="notch"></div>
							<div class="botaoCadastro">
								<img src="${context}/img/addManifestacao.png" />
								<a href="${context}/pages/manifestacao/cadastrar.xhtml">Cadastrar Manifestação</a>
							</div>
							<br />
							<p>
								À Ouvidoria compete receber, examinar e encaminhar as
								reclamações, sugestões, elogios, denúncias e solicitação de
								informações, especialmente aquelas que demandam maior urgência.
							</p>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="rodape">
			<div class="geralRodape">
				<div class="box1">
					<h3>Sobre a Ouvidoria</h3>
					<p>
						A implantação da Ouvidoria na AGR se deu por meio da Lei 13.569 de 27 de dezembro de 1999. 
						Buscou-se, com sua criação, garantir aos usuários dos serviços públicos no Estado de Goiás um canal
						de comunicação onde possam registrar suas manifestações (reclamações, denúncias, elogios, sugestões etc),
						e providenciar soluções para os conflitos entre os usuários e concessionárias ou permissionários.
					</p>

					<p>Participe! A qualidade de nossos serviços depende de sua participação.</p>
				</div>
				<div class="box2">
					<h3>Links Importantes</h3>
					<ul>
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="http://www.cge.go.gov.br/ouvidoria/">Ouvidoria Geral do Estado de Goiás</a>
						</li>
						<br />
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="http://www.transparencia.go.gov.br/">Goiás Transparente</a>
						</li>
						<br />
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="http://vaptvupt.goias.gov.br/vvv/index">Vapt Vupt Digital</a>
						</li>
						<br />
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="http://www.saneago.com.br/lai/ouvidoria/">Ouvidoria da Saneago</a>
						</li>
						<br />
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="http://www.celg.com.br/paginas/clientes/centralAtendimento.aspx">Central de Atendimento CELG</a>
						</li>
						<br />
						<!-- 
						<li>
							<img src="${context}/resources/img/setaHome.png" />
							<a href="${context}/pages/externo/listarAnexos.xhtml">Relatórios da Ouvidoria</a>
						</li>
						 -->
					</ul>
				</div>
			</div>
		</div>
		<div class="endereco">
			Coordenação de Informática
			- Agência Goiana de Regulação. Todos os Direitos Reservados.
			<a href="http://www.agr.go.gov.br/" target="_blank">AGR</a>
		</div>
	</div>
	
	<div class="modal" id="avisoModal">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" onclick="$('.modal').hide();"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="avisoTitulo"></h4>
			</div>
			<div class="modal-body"><div id="avisoConteudo"></div></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" onclick="$('.modal').hide();">Fechar</button>
			</div>
		</div>
	</div>
	
	<script src="http://barra.brasil.gov.br/barra.js" type="text/javascript"></script>
</body>
</html>
