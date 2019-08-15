package com.kakao.pay.mapper;

import org.springframework.transaction.annotation.Transactional;

import com.kakao.pay.domain.UrlVO;

@Transactional
public interface UrlMapper {

	int getShortendUrlCnt(UrlVO urlVO) throws Exception;

	int getLastNo() throws Exception;

	int insertShortendUrl(UrlVO urlVO) throws Exception;

	int getCheckHasShortenedUrl(UrlVO urlVO) throws Exception;

	String getInsertedShortendUrl(UrlVO urlVO) throws Exception;

	UrlVO getShortendUrlInfo(String shortenedUrl) throws Exception;

}
