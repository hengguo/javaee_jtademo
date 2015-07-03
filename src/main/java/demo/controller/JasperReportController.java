package demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.UserService;
import demo.util.SpringUtil;

@Controller
@RequestMapping("/jasper")
public class JasperReportController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/demo1")
	public String demo1(){
		return "jasperreports/demo1";
	}
	
	@RequestMapping(value="/print")
	@ResponseBody
	public void print(HttpServletRequest request, HttpServletResponse response) throws JRException, SQLException, IOException{
		File jasperFile = new File(request.getSession().getServletContext()
				.getRealPath("/WEB-INF/reports/report1.jasper"));
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("report_name", "我的报表1");
		//设置数据源样式
		parameters.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd HH:mm:ss");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, ((DataSource)SpringUtil.getBean("dataSource")).getConnection());
		JasperExportManager.exportReportToPdf(jasperPrint);
		if (jasperPrint!=null) {
			FileBufferedOutputStream fbos = new FileBufferedOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			try {
				exporter.exportReport();
				fbos.close();
				if (fbos.size() > 0) {
					response.setContentType("application/pdf");
					response.setContentLength(fbos.size());
					ServletOutputStream ouputStream = response.getOutputStream();
					try {
						fbos.writeData(ouputStream);
						fbos.dispose();
						ouputStream.flush();
					} finally {
						if (ouputStream!=null) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			}finally{
				if(fbos!=null){
					fbos.close();
					fbos.dispose();
				}
			}
		}
	}
	
	public void pdf(){
		
	}
}
