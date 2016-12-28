package com.yw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import com.aspose.words.SaveOptions;

public class Html2Word {


	public static boolean HtmlToWord(String html, String filename) {

		try {

			OutputStream out = new FileOutputStream(new File(filename));
			Document doc = new Document();
			DocumentBuilder builder = new DocumentBuilder(doc);
			builder.insertHtml(html);
			doc.save(out, SaveOptions.createSaveOptions(SaveFormat.DOC));// 生成doc文件
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
