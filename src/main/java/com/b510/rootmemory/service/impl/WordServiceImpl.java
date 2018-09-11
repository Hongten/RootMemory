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
package com.b510.rootmemory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.b510.rootmemory.dao.WordDao;
import com.b510.rootmemory.service.WordService;
import com.b510.rootmemory.vo.Word;

/**
 * @author Hongwei
 * @created 10 Sep 2018
 */
@Service("wordService")
public class WordServiceImpl implements WordService {

	@Override
	public List<Word> getWords() {
		if (WordDao.isLoadData) {
			return WordDao.wordList;
		}
		return new ArrayList<Word>();
	}

	@Override
	public boolean isWordExist(String word) {
		return WordDao.wordSet.contains(word);
	}

	@Override
	public String[] getWordArray() {
		if (!WordDao.isLoadData) {
			WordDao.loadData();
		}
		return WordDao.wordArray;
	}

	@Override
	public Set<String> getAllRoots() {
		return WordDao.rootSet;
	}

	@Override
	public Set<String> getAllWords() {
		return WordDao.wordSet;
	}

	@Override
	public Word findWord(String word) {
		if (!WordDao.isLoadData) {
			WordDao.loadData();
		}
		if (isWordExist(word)) {
			return WordDao.wordMaps.get(word);
		}
		return new Word();
	}

	@Override
	public boolean isLoadData() {
		return WordDao.isLoadData;
	}

	public String findBooksAjax(String name) {
		String res = "";
		int i = 0;
		for (String s : WordDao.wordSet) {
			if (s.startsWith(name)) {
				if (i > 0) {
					res += "," + s;
				} else {
					res += s;
				}
				if (i++ > 5) {
					break;
				}
			}
		}
		return res;
	}

	@Override
	public void loadData() {
		if (!WordDao.isLoadData) {
			WordDao.loadData();
		}
	}

}
