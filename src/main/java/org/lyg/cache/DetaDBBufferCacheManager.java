package org.lyg.cache;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unused")
public class DetaDBBufferCacheManager {
	private static ConcurrentHashMap<String, Object> baseMap = new ConcurrentHashMap<>();
	private DetaDBBufferCacheManager() {
		super();
	}
	public static void reflection() {
		//1��ȡbase·����
//		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString();
//		File fileDBPath = new File(DBPath);
//		if (fileDBPath.isDirectory()) {
//			String[] baseNames = fileDBPath.list();
//			for(int i=0; i<baseNames.length; i++) {
//				ConcurrentHashMap<String, Object> tableMap = new ConcurrentHashMap<>();
//				String DBasePath = DBPath + "/" + baseNames[i];
//				File fileDBasePath = new File(DBasePath);
//				if (fileDBasePath.isDirectory()) {
//					ConcurrentHashMap<String, Object> tableMap = new ConcurrentHashMap<>();
//					tableMap.put(baseNames[i], tableMap);
//				}
//				baseMap.put(baseNames[i], tableMap);
//			}
//		}
		//2��ȡtable·��
		//3��ȡ��ͷ
		//4��ȡ������
		//5��ȡ����
		//6��ȡ����
	}
	
} 