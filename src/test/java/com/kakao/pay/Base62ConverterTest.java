package com.kakao.pay;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kakao.pay.util.Base62Converter;

public class Base62ConverterTest {

	@Test
	public void test() {
		
		Base62Converter base62 = new Base62Converter();

		// 10진수 100 -> 62진수 1 38, 변환값 -> bL
		//assertEquals("bL", base62.convertTo62Base(100));
		
		// 10진수  3845 -> 62진수 1 0 1, 변환값 -> bab
		assertEquals("bab", base62.convertTo62Base(3845));
	}
}
