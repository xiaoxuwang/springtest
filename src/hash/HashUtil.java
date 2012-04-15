package hash;

public class HashUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="你好流氓我是中华人们恐吓和过";
		String str2="度发的房间奥斯达覅地方哈师大";
		String str3="你好流氓我是中华人们恐吓和过度发的房间奥斯达覅地方哈师大你好流氓我是中华人们恐吓和过度发的房间奥斯达覅地方哈师大";
		System.out.println((str+str2).hashCode());
		System.out.println(str3.hashCode());
		System.out.println(Integer.parseInt("-24768"));
		System.out.println(Integer.MAX_VALUE);
		
	}

}
