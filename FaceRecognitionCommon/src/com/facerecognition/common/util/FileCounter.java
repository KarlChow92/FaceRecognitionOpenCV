	package com.facerecognition.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.facerecognition.common.filter.DirectoryFilter;
import com.facerecognition.common.filter.ImageFilter;

public class FileCounter {

	public static Map<String, Integer> countImages(File folder) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (File file : folder.listFiles(new DirectoryFilter())) {
			File[] images = file.listFiles(new ImageFilter());
			map.put(file.getName(), images.length);
		}
		for (String key : map.keySet()) {
			System.out.println(key + " has " + map.get(key) + " objects");
		}
		return map;
	}
}
