package br.com.agr.ouvidoria.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.xti.ouvidoria.exception.InfrastructureException;

@SuppressWarnings("deprecation")
public class GeradorRelatorio {

    private Map<String, Object> parametros;
    private String nomeInicialRelatorio;
    private String nomeFinalRelatorio;
    private StreamedContent file = null;
    private File tempFile = null;

    public void baixarPDF(String nomeArquivoJasper, List<?> objetos, String nomeRelatorio) throws InfrastructureException {
        JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(objetos);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(nomeArquivoJasper + ".jasper");
        JasperReport jasperReport = null;
        JasperPrint printer = null;
        File pdfFile = null;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            printer = JasperFillManager.fillReport(jasperReport, getParametros(),
                    (objetos == null ? new JREmptyDataSource() : jrds));
            setNomeInicialRelatorio(Long.toString(new Date().getTime())
                    + ".pdf");
            pdfFile = new File(classLoader.getResource("").getPath() + getNomeInicialRelatorio());
            setNomeFinalRelatorio(nomeRelatorio);
            if (pdfFile.exists()) {
                try {
                    pdfFile.delete();
                } catch (Exception e) {
                    throw new InfrastructureException(e.getMessage());
                }
            }
            JRPdfExporter jrpdfexporter = new JRPdfExporter();
            jrpdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, printer);
            jrpdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE, pdfFile);
            jrpdfexporter.exportReport();
        } catch (JRException e) {
            throw new InfrastructureException(e.getMessage());
        } catch (Exception erro) {
            throw new InfrastructureException(erro.getMessage());
        }
    }

    public void imprimirPDF(String nomeArquivoJasper, List<?> objetos, String nomeRelatorio) throws InfrastructureException {
        JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(objetos);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(nomeArquivoJasper + ".jasper");
        JasperReport jasperReport = null;
        JasperPrint printer = null;
        File pdfFile = null;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            printer = JasperFillManager.fillReport(jasperReport, getParametros(),
                    (objetos == null ? new JREmptyDataSource() : jrds));
            setNomeInicialRelatorio(Long.toString(new Date().getTime())
                    + ".pdf");
            pdfFile = new File(classLoader.getResource("").getPath() + getNomeInicialRelatorio());
            setNomeFinalRelatorio(nomeRelatorio);
            JRPdfExporter jrpdfexporter = new JRPdfExporter();
            jrpdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, printer);
            jrpdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE, pdfFile);
            jrpdfexporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
            jrpdfexporter.exportReport();

            FileInputStream stream = new FileInputStream(pdfFile);
            byte[] b = IOUtils.toByteArray(stream);

            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                    .getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=crc.pdf");
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();

            stream.close();
            if (pdfFile.exists())
                pdfFile.delete();
        } catch (JRException e) {
            throw new InfrastructureException(e.getMessage());
        } catch (Exception erro) {
            throw new InfrastructureException(erro.getMessage());
        } finally {

        }
    }

    public void adicionaParametroRelatorio(String chave, Object valor) {
        getParametros().put(chave, valor);
    }

    public StreamedContent getFilePDF() {
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        tempFile = new File(classLoader.getResource("").getPath() + getNomeInicialRelatorio());
        try {
            file = new DefaultStreamedContent(new FileInputStream(tempFile), "application/pdf", getNomeFinalRelatorio()
                    + ".pdf");
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        return file;
    }

    public void deletaArquivo() {
        if (tempFile != null) {
            try {
                file.getStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                tempFile.delete();
                tempFile = null;
            }
        }
    }

    public Map<String, Object> getParametros() {
        if (parametros == null) {
            parametros = new HashMap<String, Object>();
        }
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getNomeInicialRelatorio() {
        return nomeInicialRelatorio;
    }

    public void setNomeInicialRelatorio(String nomeInicialRelatorio) {
        this.nomeInicialRelatorio = nomeInicialRelatorio;
    }

    public String getNomeFinalRelatorio() {
        return nomeFinalRelatorio;
    }

    public void setNomeFinalRelatorio(String nomeFinalRelatorio) {
        this.nomeFinalRelatorio = nomeFinalRelatorio;
    }
}
