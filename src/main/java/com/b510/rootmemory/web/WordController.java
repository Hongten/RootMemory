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
package com.b510.rootmemory.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b510.rootmemory.common.Common;
import com.b510.rootmemory.service.WordService;
import com.b510.rootmemory.vo.Root;
import com.b510.rootmemory.vo.Word;

/**
 * @author Hongwei
 * @created 10 Sep 2018
 */
@Controller
@RequestMapping("/word")
public class WordController {

	private static final Logger logger = Logger.getLogger(WordController.class);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Common.DATE_FORMAT_YYYY);

	@Autowired
	protected WordService wordService;

	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
	public String search(@PathVariable("keyword") String keyword, Model model, HttpSession session) {
		logger.info("search method... : " + keyword);
		Word word = null;
		if (wordService.isLoadData() && !wordService.isWordExist(keyword)) {
			word = Common.getDefaultWord(keyword);
			session.setAttribute(Common.COPY_RIGHT_YEAR, simpleDateFormat.format(new Date()));
		} else {
			word = wordService.findWord(keyword);
		}

		session.setAttribute(Common.SESSION_MY_WORD, word);
		session.setAttribute(Common.SESSION_MY_WORD_LIST, wordService.getAllWords());
		model.addAttribute("word", word);
		return "word/word";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {
		logger.info("show method...");
		Word word = wordService.findWord("abandon");
		word.setCopyRightYear(simpleDateFormat.format(new Date()));
		session.setAttribute(Common.SESSION_MY_WORD, word);
		model.addAttribute("word", word);
		return "word/word";
	}

	@RequestMapping(value = "/examples", method = RequestMethod.GET)
	public String examples(Model model, HttpSession session) {
		logger.info("examples method...");
		Word word = (Word) session.getAttribute(Common.SESSION_MY_WORD);
		if (word == null) {
			word = new Word();
		}
		model.addAttribute("word", word);
		return "word/examples";
	}

	@RequestMapping(value = "/relationship", method = RequestMethod.GET)
	public String relationship(Model model, HttpSession session) {
		logger.info("relationship method...");
		List<Map<String, String>> relationwordList = new ArrayList<Map<String, String>>();
		Word word = (Word) session.getAttribute(Common.SESSION_MY_WORD);
		if (word == null) {
			word = new Word();
		}

		Root root = word.getRoot();
		String rootName = "";
		String result = "";
		if (root != null) {
			rootName = root.getRootName();
			List<Word> relationWords = root.getRelatedWord();
			if (relationWords != null && relationWords.size() > 0) {
				for (Word w : relationWords) {
					Map<String, String> map = new HashMap<String, String>();
					String value = w.getWordName() + "\t" + w.getWordMeanings().get(0).getMeaning();
					map.put(rootName, value);
					relationwordList.add(map);
				}
			}
			result = generateSrc(relationwordList, rootName);
		}
		model.addAttribute("wordData", result);
		return "word/relationship";
	}

	private String generateSource(String source, String target) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{").append("\"source\":\"").append(source).append("\",\"type\":\"").append("licensing").append("\",\"target\":\"").append(target).append("\"},");
		return stringBuilder.toString();
	}

	private String generateSrc(List<Map<String, String>> relationwordList, String root) {
		if (relationwordList != null && relationwordList.size() > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			for (Map<String, String> map : relationwordList) {
				String target = map.get(root);
				String src = generateSource(root, target);
				stringBuilder.append(src);
			}
			String result = stringBuilder.toString();
			if (result != null && result.length() > 0) {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		} else {
			return "";
		}
	}

	@RequestMapping(value = { "/autocomplete/{v}/{t}" })
	@ResponseBody
	public String autocomplete(@PathVariable("v") String v, @PathVariable("t") String t) {
		String data = "";
		if (v == null || "".equals(v)) {
			return null;
		}
		data = wordService.findBooksAjax(v);
		return data;
	}

	@RequestMapping(value = "/aboutpage", method = RequestMethod.GET)
	public String aboutpage(Model model, HttpSession session) {
		logger.info("aboutpage method...");
		loadData(session);
		return "word/aboutpage";
	}

	private void loadData(HttpSession session) {
		if (!wordService.isLoadData()) {
			wordService.loadData();
			session.setAttribute(Common.COPY_RIGHT_YEAR, simpleDateFormat.format(new Date()));
		}
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		logger.info("about method...");
		List<Map<String, String>> aboutList = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		map.put(Common.ABOUT_NAME, Common.ABOUT_DESC1);
		aboutList.add(map);
		map = new HashMap<String, String>();
		map.put(Common.ABOUT_NAME, Common.ABOUT_DESC2);
		aboutList.add(map);
		map = new HashMap<String, String>();
		map.put(Common.ABOUT_NAME, Common.ABOUT_DESC3);
		aboutList.add(map);

		String result = generateSrc(aboutList, Common.ABOUT_NAME);

		model.addAttribute("about", result);
		return "word/about";
	}

	@RequestMapping(value = "/contactpage", method = RequestMethod.GET)
	public String contactpage(Model model, HttpSession session) {
		logger.info("contactpage method...");
		loadData(session);
		return "word/contactpage";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model) {
		logger.info("about method...");
		List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		map.put(Common.CONTACT_NAME, Common.CONTACT_EMAIL);
		contactList.add(map);
		map = new HashMap<String, String>();
		map.put(Common.CONTACT_NAME, Common.CONTACT_WEBSITE);
		contactList.add(map);
		map = new HashMap<String, String>();
		map.put(Common.CONTACT_NAME, Common.CONTACT_GITHUB);
		contactList.add(map);

		String result = generateSrc(contactList, Common.CONTACT_NAME);

		model.addAttribute("contact", result);
		return "word/contact";
	}

}
