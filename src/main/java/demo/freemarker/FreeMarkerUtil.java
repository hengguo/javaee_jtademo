package demo.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {
	 	public static void analysisTemplate(String templateName,String templateEncoding,Map<?,?> root){
		try {
			Configuration config=new Configuration();
			File file=new File("src/main/resources/freemarker");
			//设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(file);
			//设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			
			//获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			Template template=config.getTemplate(templateName,templateEncoding);
			//合并数据模型与模板
			Writer out = new OutputStreamWriter(System.out);
		    template.process(root, out);
		    out.flush();
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TemplateException e) {
			e.printStackTrace();
		}
		
	} 
}
