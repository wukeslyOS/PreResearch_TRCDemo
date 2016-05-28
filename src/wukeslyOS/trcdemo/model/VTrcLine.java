package wukeslyOS.trcdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * trc 歌词行，一行歌词
 * @author wuxiangli
 *
 */
public class VTrcLine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4745557357487660572L;
	/**
	 * 歌词对应的时间点
	 */
	private int mTimePoint;
	/**
	 * 一行歌词，由一个一个的词{@link VTrcWord}组成
	 */
	private List<VTrcWord> mWords;
	
	private String trcStr = null;
	
	public VTrcLine(int mTimePoint, List<VTrcWord> mWords) {
		super();
		this.mTimePoint = mTimePoint;
		this.mWords = mWords;
		initTrcStr();
	}

	/**
	 * 获取歌词行，由一个一个的{@link VTrcWord}组成
	 * @return
	 */
	public List<VTrcWord> getmWords() {
		return mWords;
	}
	
	public void setmWords(List<VTrcWord> mWords) {
		this.mWords = mWords;
		initTrcStr();
	}
	
	/**
	 * 获取该行歌词对应的该是时间（ms）
	 * @return
	 */
	public int getmTimePoint() {
		return mTimePoint;
	}
	
	public void setmTimePoint(int mTimePoint) {
		this.mTimePoint = mTimePoint;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return trcStr;
	}
	
	private void initTrcStr(){
		StringBuilder builder = new StringBuilder();
		if(mWords != null){
			int len = mWords.size();
			for(int i = 0; i < len; i++){
				VTrcWord word = mWords.get(i);
				if(word != null){
					builder.append(word.getmWord());
				}
			}
			trcStr = builder.toString();
		}else{
			trcStr = null;
		}
	}
}
