package com.lllllw.autosendlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Tool {
	/**
	 * 获取日期字符串
	 */
	public String getdate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	/**
	 * 获取日志内容
	 * 
	 * @throws IOException
	 */
	public String getlog() {
		Tool tool = new Tool();
		File log = new File("C:/Users/chenjunhao/Desktop/log/" + tool.getdate() + ".txt");
		StringBuilder logContent = new StringBuilder(); // 最后生成的日志字符串
		String tempstr = "";
		BufferedReader br;
		boolean flag = true;
		Scanner scan = null;
		while (flag) {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(log), "UTF-8"));
			while ((tempstr = br.readLine()) != null) {
				logContent.append(tempstr);
				logContent.append("<br>");// 换行符为<br>
			}
			flag = false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			flag = true;
		} catch (FileNotFoundException e) {
			System.out.println("没有找到日志文件");
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			flag = true;
		}
		if (flag) {
			System.out.println("输入回车再次查找文件");
			scan = new Scanner(System.in);
			scan.nextLine();
		}
	}
		/*
		 * try { logContent = FileUtils.readFileToString(log, "utf-8"); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return logContent.toString();
	}

	public static void main(String[] args) throws IOException {
		Tool tool = new Tool();
		System.out.println(tool.getlog());
	}
}
