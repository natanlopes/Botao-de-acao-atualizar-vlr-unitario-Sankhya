package br.com.argo.btn.update.vlrunitario;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;

import br.com.sankhya.modelcore.util.EntityFacadeFactory;


public class AtualizarVlrUnitario implements AcaoRotinaJava {
//269182
	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
	    Registro[] linhas = ctx.getLinhas();
	    StringBuilder mensagemSucesso = new StringBuilder();
	    try {
	    	// Iniciando a construção da mensagem de sucesso com a tabela
	        mensagemSucesso.append("<!DOCTYPE html>").append("<html>").append("<body>")
	            .append("<div style='text-align: center;'>") // Centraliza a imagem e o texto
	            .append("<img src='https://argofruta.com/wp-content/uploads/2021/05/Logo-text-green.png' style='width:120px; height:90px;'>") // Adiciona a imagem centralizada
	            .append("</div>")
	            .append("<div style='display: flex; align-items: center; justify-content: center;'>")
	            .append("<img src='https://cdn-icons-png.flaticon.com/256/189/189677.png' style='width:23px; height:23px; margin-right:5px;'>") // Link direto da imagem
	            .append("<p style='color:#274135; font-family:verdana; font-size:15px; margin: 0;'><b>Atualização de Valores Unitários</b></p>")
	            .append("</div>")
	            .append("<p style='font-family:courier; color:#274135;'>Produtos processados e valores unitários foram: <br><br>");
	        // Itera sobre cada registro selecionado
			for (Registro registro : linhas) {
				// Obtendo o valor unitário a ser atualizado
				Double paramVlrunitario = (Double) ctx.getParam("VLRUNIT");
				BigDecimal nrUnico = (BigDecimal) registro.getCampo("NUNOTA");
				BigDecimal seque = (BigDecimal) registro.getCampo("SEQUENCIA");
				BigDecimal codpro = (BigDecimal) registro.getCampo("CODPROD");
				mensagemSucesso.append(codpro).append("  - ").append("Valor Unitário: ")
						.append(String.format("%.2f", paramVlrunitario)).append("<br>");

	            AtualizaeVlrUnitario(nrUnico, paramVlrunitario, seque);

			}

			// Finaliza a mensagem de sucesso com a formatação HTML
			mensagemSucesso.append("</b></p>").append("</body>").append("</html>");

			// Define a mensagem de retorno
			ctx.setMensagemRetorno(mensagemSucesso.toString());

		} catch (Exception e) {
			ctx.mostraErro(e.getMessage());
		}
	}

	public void AtualizaeVlrUnitario(BigDecimal nota, Double vlrunitario, BigDecimal seq) throws Exception {
	    SessionHandle hnd = JapeSession.open();
	    hnd.setFindersMaxRows(-1);
	    EntityFacade entity = EntityFacadeFactory.getDWFFacade();
	    JdbcWrapper jdbc = entity.getJdbcWrapper();
	    jdbc.openSession();
	    try {
	        NativeSql sql = new NativeSql(jdbc);
	        sql.appendSql("UPDATE TGFITE SET VLRUNIT = :VLRUNIT WHERE NUNOTA = :NUNOTA AND SEQUENCIA = :SEQUENCIA");
	        sql.setNamedParameter("VLRUNIT", vlrunitario);
	        sql.setNamedParameter("NUNOTA", nota);
	        sql.setNamedParameter("SEQUENCIA", seq);
	        sql.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Erro ao executar AtualizaeVlrUnitario: " + e.getMessage());
	    } finally {
	        JdbcWrapper.closeSession(jdbc);
	        JapeSession.close(hnd);
	    }
	}
	  

}
