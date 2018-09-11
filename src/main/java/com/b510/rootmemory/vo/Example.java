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

/**
 * @author Hongten
 * @created 1/9/2018
 */
public class Example implements Serializable {

	private static final long serialVersionUID = 8980394970029107253L;

	private String englishSentence;
	private String chineseSentence;

	public String getEnglishSentence() {
		return englishSentence;
	}

	public void setEnglishSentence(String englishSentence) {
		this.englishSentence = englishSentence;
	}

	public String getChineseSentence() {
		return chineseSentence;
	}

	public void setChineseSentence(String chineseSentence) {
		this.chineseSentence = chineseSentence;
	}

}