package com.next.common.util;

import java.text.DecimalFormat;

public class StudyFileUtil {

	/**
	 * 사이즈값을 입력받아서 팬시사이즈를 구해서 리턴
	 */
	public static String fancySize(long size) {
		DecimalFormat df = new DecimalFormat("#,###.0");
		if(size < 1024) {
			return size + "bytes";
		}else if(size < 1024 * 1024) {
			return df.format( size / 1024.0) + "Kb"; 
		}else if(size < 1024 * 1024 * 1024) {
			return df.format( size / (1024.0 * 1024.0)) + "Mb";
		}else {
			return df.format( size / (1024.0 * 1024.0 * 1024.0)) + "Gb";
		}
	}
	
}
