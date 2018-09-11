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
package com.b510.rootmemory.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import com.b510.rootmemory.common.Common;
import com.b510.rootmemory.dao.WordDao;
import com.b510.rootmemory.vo.Example;
import com.b510.rootmemory.vo.Root;
import com.b510.rootmemory.vo.Word;
import com.b510.rootmemory.vo.WordMeaning;

/**
 * Read or Write XML file
 * 
 * @author Hongten
 * @created 2/9/2018
 */
public class XmlUtil {

	static Logger logger = Logger.getLogger(XmlUtil.class);

	public static List<Word> loadPlayListFromXML() {
		logger.debug("reading word list from [" + Common.XML_FILE_PATH + "] file.");
		List<Word> wordList = new ArrayList<Word>();
		List<String> noDuplicateWord = new ArrayList<String>();
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:" + Common.XML_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (file.exists()) {
			logger.info("find xml file");
		} else {
			logger.info("can not find xml file");
		}
		processXML(wordList, noDuplicateWord, file);
		return wordList;
	}

	@SuppressWarnings("rawtypes")
	private static void processXML(List<Word> wordList, List<String> noDuplicateWord, File file) {
		if (file.exists()) {
			logger.debug("..... begin to loading ....." + file.getAbsolutePath());
			try {
				SAXReader saxReader = new SAXReader();
				Document document = saxReader.read(file.getAbsoluteFile());
				Element root = document.getRootElement();
				Word w = null;
				for (Iterator word = root.elementIterator(Common.SUB_ELEMENT_WORD); word.hasNext();) {
					Element wordEle = (Element) word.next();
					String name = wordEle.elementText(Common.SUB_ELEMENT_NAME);
					String engPro = wordEle.elementText(Common.SUB_ELEMENT_ENG_PRO);
					String amePro = wordEle.elementText(Common.SUB_ELEMENT_AME_PRO);

					Element meaningsElement = wordEle.element(Common.SUB_ELEMENT_MEANINGS);
					List<WordMeaning> wordMeanings = new ArrayList<WordMeaning>();
					for (Iterator m = meaningsElement.elementIterator(Common.SUB_ELEMENT_MEANING); m.hasNext();) {
						Element meaningEle = (Element) m.next();
						String meaningStr = meaningEle.getText();
						WordMeaning meaning = new WordMeaning(meaningStr);
						wordMeanings.add(meaning);
					}

					// examples
					Element examplesElement = wordEle.element(Common.SUB_ELEMENT_EXAMPLES);

					List<Example> exam = new ArrayList<Example>();
					for (Iterator m = examplesElement.elementIterator(Common.SUB_ELEMENT_EXAMPLE); m.hasNext();) {
						Element exeamEle = (Element) m.next();
						String exampleStr = exeamEle.getText();
						if (exampleStr != null && !Common.EMPTY.equals(exampleStr)) {
							String[] examplesArr = exampleStr.split(Common.POUND_SIGN);
							if (examplesArr != null && examplesArr.length > 1) {
								Example e = new Example();
								// logger.debug(examplesArr.length);
								e.setEnglishSentence(examplesArr[0]);
								e.setChineseSentence(examplesArr[1]);
								exam.add(e);
							}
						}
					}

					// root
					String rootName = wordEle.elementText(Common.SUB_ELEMENT_ROOT);
					Root r = new Root();
					r.setRootName(rootName);
					WordDao.rootSet.add(rootName);

					// same root word
					Element sameRootWords = wordEle.element(Common.SUB_ELEMENT_SAME_ROOT_WORDS);

					List<Word> sameWords = new ArrayList<Word>();
					for (Iterator m = sameRootWords.elementIterator(Common.SUB_ELEMENT_SAME_ROOT_WORD); m.hasNext();) {
						Element sameWord = (Element) m.next();
						String sameWordStr = sameWord.getText();
						if (sameWordStr != null && !Common.EMPTY.equals(sameWordStr)) {
							String[] sameWordsArr = sameWordStr.split(Common.POUND_SIGN);
							if (sameWordsArr != null && sameWordsArr.length > 1) {
								Word wo = new Word();
								wo.setWordName(sameWordsArr[0]);
								List<WordMeaning> wms = new ArrayList<WordMeaning>();
								WordMeaning wm = new WordMeaning(sameWordsArr[1]);
								wms.add(wm);
								wo.setWordMeanings(wms);
								sameWords.add(wo);
							}
						}
						r.setRelatedWord(sameWords);
					}

					w = new Word();
					w.setWordName(name);
					// set word
					WordDao.wordSet.add(name);
					w.setEnglishProunciation(engPro);
					w.setAmericanProunciation(amePro);

					w.setWordMeanings(wordMeanings);
					w.setExamples(exam);

					w.setRoot(r);

					boolean existing = false;
					if (noDuplicateWord.contains(name)) {
						existing = true;
						logger.debug("duplicate word :" + name);
					} else {
						noDuplicateWord.add(name);
					}

					if (!existing && rootName != null && !"".equals(rootName)) {
						WordDao.wordMaps.put(name, w);
						wordList.add(w);
					}
				}
				logger.debug("..... end to loading .....");
				logger.debug("Total : " + (wordList == null ? 0 : wordList.size()));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			logger.debug("can not find xml file.");
		}
	}

}
