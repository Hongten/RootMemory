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
 * One <code>Root</code> include one or more than one <code>Word</code>.<br>
 * each <code>Word</code> may have one <code>Root</code>.
 * 
 * @author Hongten
 * @created 1/9/2018
 * 
 * @see Word
 * @since 1.0
 */
public class Root implements Serializable {

	private static final long serialVersionUID = -6288364064826029857L;

	private String rootName;

	private List<Word> relatedWord;

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public List<Word> getRelatedWord() {
		if (relatedWord == null) {
			relatedWord = new ArrayList<Word>();
		}
		return relatedWord;
	}

	public void setRelatedWord(List<Word> relatedWord) {
		this.relatedWord = relatedWord;
	}

}
