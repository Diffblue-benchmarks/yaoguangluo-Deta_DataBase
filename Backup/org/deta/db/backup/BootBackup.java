package org.deta.db.backup;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.zip.ZipEntry;  
import java.util.zip.ZipOutputStream;

import org.lyg.cache.CacheManager;  

/** 
 *  uncheck and untest
 * @author https://wenda.so.com/q/1512966734215123 gets
 * @author aczjc refer
 * @fix yaoguangluo 2019-1-31
 * 
 */  

public class BootBackup {  
	private BootBackup(){}   
	public static void BootBackupByUsingGzip(String zipPath){  
		String sourceFilePath = CacheManager.getCacheInfo("DBPath").getValue().toString();
		String zipFilePath = zipPath;  
		long time = System.currentTimeMillis();
		String fileName = "zip_" + time;  
		boolean flag = BootBackup.fileToZip(sourceFilePath, zipFilePath, fileName);  
		if(flag){  
			System.out.println("�ļ�����ɹ�!");  
		}else{  
			System.out.println("�ļ����ʧ��!");  
		}  
		//delete all binlog file;
	}     

	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName){  
		boolean flag = false;  
		File sourceFile = new File(sourceFilePath);  
		FileInputStream fileInputStream = null;  
		BufferedInputStream bufferedInputStream = null;  
		FileOutputStream fileOutputStream = null;  
		ZipOutputStream zipOutputStream = null;     
		if(!sourceFile.exists()){  
			System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath + "������.");  
		}else{  
			try {  
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");  
				if(zipFile.exists()){  
					System.out.println(zipFilePath + "Ŀ¼�´�������Ϊ:" + fileName + ".zip" + "����ļ�.");  
				}else{  
					File[] sourceFiles = sourceFile.listFiles();  
					if(null == sourceFiles || sourceFiles.length < 1){  
						System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath + "���治�����ļ�������ѹ��.");  
					}else{  
						fileOutputStream = new FileOutputStream(zipFile);  
						zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));  
						byte[] bufs = new byte[1024 * 10];  
						for(int i=0; i<sourceFiles.length; i++){  
							//����ZIPʵ�壬����ӽ�ѹ����  
							ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
							zipOutputStream.putNextEntry(zipEntry);  
							//��ȡ��ѹ�����ļ���д��ѹ������  
							fileInputStream = new FileInputStream(sourceFiles[i]);  
							bufferedInputStream = new BufferedInputStream(fileInputStream, 1024 * 10);  
							int read = 0;  
							while((read = bufferedInputStream.read(bufs, 0, 1024 * 10)) != -1){  
								zipOutputStream.write(bufs,0,read);  
							}  
						}  
						flag = true;  
					}  
				}  
			} catch (FileNotFoundException e) {  
				e.printStackTrace();  
				throw new RuntimeException(e);  
			} catch (IOException e) {  
				e.printStackTrace();  
				throw new RuntimeException(e);  
			} finally{  
				try {  
					if(null != bufferedInputStream) bufferedInputStream.close();  
					if(null != zipOutputStream) zipOutputStream.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
					throw new RuntimeException(e);  
				}  
			}  
		}  
		return flag;  
	}  
}