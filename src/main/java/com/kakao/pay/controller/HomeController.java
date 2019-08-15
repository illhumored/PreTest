package com.kakao.pay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.pay.domain.UrlVO;
import com.kakao.pay.mapper.UrlMapper;
import com.kakao.pay.util.Base62Converter;

@Controller
public class HomeController {
	
	@Autowired
	UrlMapper urlMapper;

	/**
	 * <pre>
	 * com.kakao.pay.controller
	 * └ HomeController.java
	 * </pre>
	 * @date : 2019. 8. 13. 오후 10:29:05
	 * @author : hschoi
	 * @update : 
	 * @desc : 메인페이지
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String mainGET() {
		
		return "main";
	}
	
	/**
	 * <pre>
	 * com.kakao.pay.controller
	 * └ HomeController.java
	 * </pre>
	 * @date : 2019. 8. 13. 오후 10:30:21
	 * @author : hschoi
	 * @update : 
	 * @desc : 단축URL 처리 API
	 */
	@RequestMapping(value="/urlShortening", method = RequestMethod.POST)
	public ResponseEntity<UrlVO> urlShorteningPOST(HttpServletRequest request, Model model, @RequestBody UrlVO urlVO) throws Exception {
		
		ResponseEntity<UrlVO> entity = null;
		
		Base62Converter base62Converter = new Base62Converter();
		
		String shortedUrl;
		int result;
		int rowCnt;
		
		Integer lastId = urlMapper.getLastNo();
		
		// Case1. 처음 등록 시 
		if(lastId == 0) {
			
			shortedUrl = base62Converter.convertTo62Base(lastId);
			urlVO.setShortenedUrl(shortedUrl);
			
			result = urlMapper.insertShortendUrl(urlVO);
			
			if(result == 1)
				//entity = new ResponseEntity<String>(request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/" + shortedUrl, HttpStatus.OK);
				entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.OK);
			else 
				entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.BAD_REQUEST);
			
		} 
		// Case2. 처음 등록이 아닐 시,
		else {
			
			rowCnt = urlMapper.getCheckHasShortenedUrl(urlVO);
			
			// Case2-1. 이미 등록된 Url 인 경우
			if(rowCnt != 0) {
				
				shortedUrl = urlMapper.getInsertedShortendUrl(urlVO);
				
				urlVO.setResultMsg("SUCCES");
				urlVO.setShortenedUrl(shortedUrl);
				
				//entity = new ResponseEntity<String>(request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/" + shortedUrl, HttpStatus.OK);
				entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.OK);
				
			} 
			// Case2-2. Url 신규 등록인 경우
			else { 
				
				shortedUrl = base62Converter.convertTo62Base(lastId+1);
				urlVO.setShortenedUrl(shortedUrl);
												
				result = urlMapper.insertShortendUrl(urlVO);
				
				if(result == 1) {
					
					urlVO.setResultMsg("SUCCES");
					urlVO.setShortenedUrl(shortedUrl);
					
					//entity = new ResponseEntity<String>(request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/" + shortedUrl, HttpStatus.OK);
					entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.OK);
				}
				else {
					
					urlVO.setResultMsg("FAILE");
					
					//entity = new ResponseEntity<String>("등록 실패", HttpStatus.BAD_REQUEST);
					entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.BAD_REQUEST);
				}
					
			}
		}
		return entity;
	}
	
	/**
	 * <pre>
	 * com.kakao.pay.controller
	 * └ HomeController.java
	 * </pre>
	 * @date : 2019. 8. 15. 오전 12:30:26
	 * @author : hschoi
	 * @update : 
	 * @desc : 원본 URL 조회 API
	 */
	@RequestMapping(value="/getOriginUrl", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<UrlVO> originUrlGET(Model model, @RequestParam("shortenedUrl") String shortenedUrl) throws Exception {
	
		ResponseEntity<UrlVO> entity = null;
		UrlVO urlVO = new UrlVO();
		
		int result;

		urlVO = urlMapper.getShortendUrlInfo(shortenedUrl);
		
		if(urlVO != null) {
			
			urlVO.setResultMsg("SUCCES");
			
			//entity = new ResponseEntity<String>(request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/" + shortedUrl, HttpStatus.OK);
			entity = new ResponseEntity<UrlVO>(urlVO, HttpStatus.OK);
		}
		else {

			UrlVO tmpVO = new UrlVO();
			tmpVO.setResultMsg("EMPTY");
			
			//entity = new ResponseEntity<String>("등록 실패", HttpStatus.BAD_REQUEST);
			entity = new ResponseEntity<UrlVO>(tmpVO, HttpStatus.OK);
		}
		
		return entity;
		
	}
	
	
	
	
}
