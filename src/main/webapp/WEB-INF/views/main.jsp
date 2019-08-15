<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>KakaoPay PreTest Page</title>
	
	<%@include file="/WEB-INF/views/_include/_head.jsp"%>
	
	<!-- Custom Javasciprt File -->
	<script type="text/javascript" src="/js/site/main.js" ></script>
	 
	<!-- Custom CSS -->
	<style>
		.content-wrapper {
			margin : 0 !important;
			min-height: 100%;
			padding: 50px 100px 0 100px !important;
		} 
	</style>
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">단축 URL 변환</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form">
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputEmail1">원본 URL</label>
									<input type="text" class="form-control" id="originUrl" placeholder="원본 URL 입력">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<input type="button" id="shorteningBtn" class="btn btn-primary"value="변환">&nbsp;
								단축된 주소(클릭시 이동) → <label id="shortedUrl"></label>
							</div>
						</form>
					</div>
					<!-- /.box -->
				</div>
			</div>
		</section>
		
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">원본 페이지 이동</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form">
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputEmail1">단축 URL</label>
									<input type="text" class="form-control" id="shortenedUrl" placeholder="단축 URL 입력">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<input type="button" id="moveBtn" class="btn btn-success" value="이동">
							</div>
						</form>
					</div>
					<!-- /.box -->
				</div>
			</div>
		</section>
		
	</div>
</body>
</html>
