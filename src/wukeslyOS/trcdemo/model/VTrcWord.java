/**
 * 
 */
package wukeslyOS.trcdemo.model;

import java.io.Serializable;

/**
 * 歌词行中的一个词
 * @author wuxiangli
 */
public class VTrcWord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905587663150197005L;
	/**
	 * 该词对应的文本
	 */
	private String mWord;
	/**
	 * 该词持续的时间
	 */
	private int mDuration;
	
	public VTrcWord(String mWord, int mDuration) {
		super();
		this.mWord = mWord;
		this.mDuration = mDuration;
	}

	public String getmWord() {
		return mWord;
	}

	public void setmWord(String mWord) {
		this.mWord = mWord;
	}

	public int getmDuration() {
		return mDuration;
	}

	public void setmDuration(int mDuration) {
		this.mDuration = mDuration;
	}
	
	

}
