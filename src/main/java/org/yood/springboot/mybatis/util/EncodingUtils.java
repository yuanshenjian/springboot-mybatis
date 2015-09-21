package org.yood.springboot.mybatis.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodingUtils {

	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String GB_2312 = "GB2312";

	private EncodingUtils() {
	}

	public static String urlEncodeUTF8(String value) {
		try {
			return URLEncoder.encode(value, UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String isoToUtf(String value) {
		try {
			return new String(value.getBytes(ISO_8859_1), UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String utfToIso(String value) {
		try {
			return new String(value.getBytes(UTF_8), ISO_8859_1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String utfToGbk(String value) {
		try {
			return new String(value.getBytes(UTF_8), GBK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String isoToGbk(String value) {
		try {
			return new String(value.getBytes(ISO_8859_1), GBK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String gbkToIso(String value) {
		try {
			return new String(value.getBytes(GBK), ISO_8859_1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static  String gbkToUtf(String value) {
		try {
			return new String(value.getBytes(GBK), UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}
}
