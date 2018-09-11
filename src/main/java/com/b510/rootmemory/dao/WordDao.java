/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.b510.rootmemory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.b510.rootmemory.util.XmlUtil;
import com.b510.rootmemory.vo.Word;

/**
 * @author Hongwei
 * @created 10 Sep 2018
 */
public class WordDao {

	// save all words
	public static Set<String> wordSet = new HashSet<String>();
	public static String[] wordArray = new String[10];
	// save all roots
	public static Set<String> rootSet = new HashSet<String>();
	// to save all words details
	public static List<Word> wordList = new ArrayList<Word>();
	public static Map<String, Word> wordMaps = new HashMap<String, Word>();
	// check if the data is loaded already.
	public static boolean isLoadData = false;

	private WordDao() {
	}

	public static List<Word> loadData() {
		isLoadData = true;
		// wordSet, rootSet will be initialed in loadPlayListFromXML() method.
		wordList = XmlUtil.loadPlayListFromXML();
		initWordArray();
		return wordList;
	}
	
	public static void initWordArray() {
		if (isLoadData && wordSet != null && wordSet.size() > 0) {
			wordArray = new String[wordSet.size()];
			int i = 0;
			for (String s : wordSet) {
				wordArray[i++] = s;
			}
		}
	}
}
