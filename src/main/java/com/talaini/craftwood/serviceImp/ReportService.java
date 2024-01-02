package com.talaini.craftwood.serviceImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.talaini.craftwood.entity.Commande;
import com.talaini.craftwood.repository.CommandeRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
    private CommandeRepository repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Simplon Tasks";
        List<Commande> commandes = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:vente.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(commandes);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Mohammed Talaini");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //if (reportFormat.equalsIgnoreCase("html")) {
        System.out.println(jasperPrint.toString());
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Sales.pdf");
        /*}
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Sales.pdf");
        }*/

        return "list_cmd";
    }
}
