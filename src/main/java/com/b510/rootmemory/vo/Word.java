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
package com.b510.rootmemory.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Each <code>Word</code> may have one <code>Root</code>.
 * 
 * @author Hongten
 * @created 1/9/2018
 * 
 * @see Root
 * @since 1.0
 */
public class Word implements Serializable {

	private static final long serialVersionUID = 1893414244812551313L;

	private String wordName;
	private String englishProunciation;
	private String americanProunciation;

	private List<WordMeaning> wordMeanings;
	private List<Example> examples;

	private Root root;

	private String copyRightYear;

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getEnglishProunciation() {
		return englishProunciation;
	}

	public void setEnglishProunciation(String englishProunciation) {
		this.englishProunciation = englishProunciation;
	}

	public String getAmericanProunciation() {
		return americanProunciation;
	}

	public void setAmericanProunciation(String americanProunciation) {
		this.americanProunciation = americanProunciation;
	}

	public List<WordMeaning> getWordMeanings() {
		if (wordMeanings == null) {
			wordMeanings = new ArrayList<WordMeaning>();
		}
		return wordMeanings;
	}

	public void setWordMeanings(List<WordMeaning> wordMeanings) {
		this.wordMeanings = wordMeanings;
	}

	public List<Example> getExamples() {
		if (examples == null) {
			examples = new ArrayList<Example>();
		}
		return examples;
	}

	public void setExamples(List<Example> examples) {
		this.examples = examples;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public String getCopyRightYear() {
		return copyRightYear;
	}

	public void setCopyRightYear(String copyRightYear) {
		this.copyRightYear = copyRightYear;
	}

}
