package util.sqlUtil;

import java.util.HashMap;

/**序列号批量生成*/
public class SquencesUtil {

	/**
	 * 
	 * 序列号生成
	 * 
	 * 
	 * */
	private HashMap<String, sequenceBean> seq_map=new HashMap<String, sequenceBean>();
	
	public String getSequence(String seq){
		
		String sequence="";
		sequenceBean sBean=seq_map.get(seq);
		if(null==sBean){
			sequenceBean  seBean = new sequenceBean();
			//查询数据库中当前序列的最大值 
			long ss=00;
			//更新数据库中该序列的最大值 sequence=ss+sBean.getCasheSize()
			
			sequence=(ss+1)+"";
			seBean.setCurrentValue(ss+seBean.getCasheSize());
			seBean.setNextValue(ss+2);
		}else{
			long value=sBean.getNextValue();
			if(value<sBean.getCurrentValue()){
				sBean.setNextValue(value+1);
				sequence=value+"";
			}
			
			if(value==sBean.getCurrentValue()){
				//查询数据库中当前序列的最大值 
				long ss=00;
				//更新数据库中该序列的最大值 sequence=ss+sBean.getCasheSize()
				sequence=value+"";
				sBean.setCurrentValue(ss+sBean.getCasheSize());
				sBean.setNextValue(ss+1);
				
			}
		}
		return sequence;
	}
}
class sequenceBean{
	
	
	private long minValue;
	private long maxValue;
	private long currentValue;
	private long nextValue;
	private int incrementValue;
	private int casheSize;
	private String state;
	
	public int getCasheSize() {
		return casheSize;
	}
	public void setCasheSize(int casheSize) {
		this.casheSize = casheSize;
	}
	
	
	public long getMinValue() {
		return minValue;
	}
	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}
	public long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}
	public long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(long currentValue) {
		this.currentValue = currentValue;
	}
	public long getNextValue() {
		return nextValue;
	}
	public void setNextValue(long nextValue) {
		this.nextValue = nextValue;
	}
	public int getIncrementValue() {
		return incrementValue;
	}
	public void setIncrementValue(int incrementValue) {
		this.incrementValue = incrementValue;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}