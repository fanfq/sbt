package com.fanfq.sbt;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.fanfq.sbt.service.MailService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailTest {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailService mailService;
	
	private String to = "fangqing.fan@hotmail.com.com";
	
	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail(to, "主题：简单邮件", "das2邮件内容");
	}

	@Autowired
	Configuration configuration; //freeMarker configuration
	
	@Test
	public void sendHtmlMailUsingFreeMarker() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ts", "Fred:"+System.currentTimeMillis());
		model.put("msg", "Fred msg");
		Template t = configuration.getTemplate("hello.ftl"); // freeMarker template
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

		logger.debug(content);
		mailService.sendHtmlMail(to, "主题：html邮件", content);
	}

	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "/Users/fred/Desktop/WX20181127-121534.png");
	}
	
	@Test
	public void sendInlineResourceMail() {
		String rscId = "rscId001";
		mailService.sendInlineResourceMail(to,
				"主题：嵌入静态资源的邮件",
				"<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
				"/Users/fred/Desktop/WX20181127-121534.png",
				rscId);
	}


	@Test
	public void sendMailWithExcel() throws IOException {
		String[] headers = {"col1","col2","col3"};
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		int rowIndex = 1;

		for(int j=0; j<3; j++){
			row = sheet.createRow(rowIndex);
			rowIndex++;
			HSSFCell cell1 = row.createCell(0);
			cell1.setCellValue(j);
			cell1 = row.createCell(1);
			cell1.setCellValue(j+1);
			cell1 = row.createCell(2);
			cell1.setCellValue(j+2);
		}
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
		wb.write(os);
		wb.close();

		InputStreamSource iss = new ByteArrayResource(os.toByteArray());
		os.close();

		mailService.sendAttachmentsMail("fangqing.fan@hotmail.com.com",
				"attachmentMail subject",
				"I have an attachment",
				iss, "abc1.xlsx");

	}
}
