package hash;

public class HashUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="�����å�����л����ǿ��ź͹�";
		String str2="�ȷ��ķ����˹��҅�ط���ʦ��";
		String str3="�����å�����л����ǿ��ź͹��ȷ��ķ����˹��҅�ط���ʦ�������å�����л����ǿ��ź͹��ȷ��ķ����˹��҅�ط���ʦ��";
		System.out.println((str+str2).hashCode());
		System.out.println(str3.hashCode());
		System.out.println(Integer.parseInt("-24768"));
		System.out.println(Integer.MAX_VALUE);
		
	}

}
