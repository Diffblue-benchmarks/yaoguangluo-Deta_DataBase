package org.lyg.cache;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import org.lyg.db.reflection.Base;
import org.lyg.db.reflection.DB;
import org.lyg.db.reflection.Table;
@SuppressWarnings("unused")
public class DetaDBBufferCacheManager {
	private static DB db = new DB();
	private DetaDBBufferCacheManager() {
		super();
	}
	public static void reflection() {
		ConcurrentHashMap<String, Base> bases = new ConcurrentHashMap<>();
		//1��ȡbase·����
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString();
		File fileDBPath = new File(DBPath);
		if (fileDBPath.isDirectory()) {
			String[] baseNames = fileDBPath.list();
			for(int i = 0; i < baseNames.length; i++) {
				Base base = new Base();
				ConcurrentHashMap<String, Object> tables = new ConcurrentHashMap<>();
				String DBasePath = DBPath + "/" + baseNames[i];
				File fileDBasePath = new File(DBasePath);
				if (fileDBasePath.isDirectory()) {
					ConcurrentHashMap<String, Object> tableMap = new ConcurrentHashMap<>();
					
					tableMap.put(baseNames[i], tableMap);
				}
				bases.put(baseNames[i], base);
			}
		}
		db.setBases(bases);
		//2��ȡtable·��
		//3��ȡ��ͷ
		//4��ȡ������
		//5��ȡ����
		//6��ȡ����
	}
	
} 