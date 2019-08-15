package com.kakao.pay.domain;

import lombok.Data;

@Data
public class UrlVO {

	private long id;
	private String shortenedUrl;
	private String originUrl;
	private String resultMsg;
}
