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
package com.b510.rootmemory.common;

import com.b510.rootmemory.vo.Root;
import com.b510.rootmemory.vo.Word;

/**
 * All the constants will be here.
 * 
 * @author Hongten
 * @created 1/9/2018
 */
public class Common {

	public static final String YOUDAO_DICTIONARY_URL = "http://dict.youdao.com/w/";
	public static final String YOUDAO_DICTIONARY_URL_PARA = "/#keyfrom=dict2.top";

	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final String CARRIAGE_RETURN = "\r\n";
	public static final String POUND_SIGN = "########";
	public static final String EMPTY = "";

	public static final String DATE_FORMAT_YYYY = "yyyy";
	public static final String COPY_RIGHT_YEAR = "copyRightYear";

	public static final String SESSION_MY_WORD = "myword";
	public static final String SESSION_MY_WORD_LIST = "mywordlist";

	public static final String WORD_FILE_PATH = "lib/word/words.txt";
	public static final String XML_FILE_PATH = "xml/rootmemory.xml";

	public static final String CAN_NOT_FIND = "Can not find.";

	// rootmemory.xml file element
	public static final String ROOT_ELEMENT_WORDS = "words";
	public static final String COMMENT = "all words here.";
	public static final String COMMENT_TOTAL = "Total : ";
	public static final String SUB_ELEMENT_WORD = "word";
	public static final String SUB_ELEMENT_NAME = "name";
	public static final String SUB_ELEMENT_ENG_PRO = "engPro";
	public static final String SUB_ELEMENT_AME_PRO = "amePro";
	public static final String SUB_ELEMENT_MEANINGS = "meanings";
	public static final String SUB_ELEMENT_MEANING = "meaning";
	public static final String SUB_ELEMENT_EXAMPLES = "examples";
	public static final String SUB_ELEMENT_EXAMPLE = "example";
	public static final String SUB_ELEMENT_ROOT = "root";
	public static final String SUB_ELEMENT_SAME_ROOT_WORDS = "sameRootWords";
	public static final String SUB_ELEMENT_SAME_ROOT_WORD = "sameRootWord";

	public static Word getDefaultWord(String name) {
		Word w = new Word();
		w.setWordName(name);
		w.setEnglishProunciation("[-]");
		w.setAmericanProunciation("[-]");

		Root root = new Root();
		root.setRootName(CAN_NOT_FIND);
		w.setRoot(root);
		return w;
	}
	
	//about
	public static final String ABOUT_NAME = "Root Memory";
	public static final String ABOUT_DESC1 = "单词： 根据搜索单词，查询相应单词的英式和美式发音，单词的中文意思。";
	public static final String ABOUT_DESC2 = "词根： 系统会动态生成该单词对于的词根，以及此词根对应的其他单词。";
	public static final String ABOUT_DESC3 = "例句： 系统也会提供相应单词的例句，帮助我们记住该单词。";
	
	//contact
	public static final String CONTACT_NAME = "Hongten";
	public static final String CONTACT_EMAIL = "Email: hongtenzone@foxmail.com";
	public static final String CONTACT_WEBSITE = "Website: http://www.cnblogs.com/hongten";
	public static final String CONTACT_GITHUB = "Github: https://github.com/Hongten";

}
