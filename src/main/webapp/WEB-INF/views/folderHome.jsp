<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset="utf-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  <body>
    <a href="/">home</a>
    <a href="/folderHome">folder</a>
    <br><br>
    
	<button onclick="new_folder_add('beginIT',1)">새폴더 생성</button>
	<br><br>
	
	<div id="parentFolder">
	<c:forEach items="${folderList}" var="folderList" varStatus="status">
		<div id="fdivUp_${folderList.folder_uid}">
			<input type="button" id="${folderList.folder_uid}" onclick="folder_view('beginIT','${folderList.folder_uid}')" value="+"><a>${folderList.folder_name}</a> <button onclick="folder_add('beginIT', '${folderList.folder_uid}','${folderList.folder_level}')">하위폴더 생성</button>
			<input type="button" onclick="folder_delete('${folderList.folder_uid}')" value="삭제">
		</div>
		<div id="fdivDown_${folderList.folder_uid}">
		</div>
	</c:forEach>
	</div>
	
	<br><br>

  </body>
  
  <script>
  function folder_delete(folder_uid){
	  $.ajax({
	    url: "/folderDelete2",
	    data : {
	    	"folder_uid" : folder_uid		
	    },
        type : 'post',
        datatype: 'text',
        async: false,
        success : function(result) {
        	if(result=="success"){
        		$('#fdivUp_'+folder_uid).empty();
        		$('#fdivDown_'+folder_uid).empty();
        	}
	    }
	});
  }
  </script>
  
  <script>
  function new_folder_add(user_uid){
	  $.ajax({
	    url: "/folderInsert2",
	    data : {
	    	"folder_name" : "새폴더",
	    	"user_uid" : user_uid,
	    	'company_code' : "1",
	    	"first" : 1
	    },
        type : 'post',
        datatype: 'text',
        async: false,
        success : function(result) {

			var jsonStr = JSON.stringify(result);
			
			var text = "";
			text += '<div id="fdivUp_'+result[0].folder_uid+'">'
			text +=	'<input type="button" id="'
       		text +=	result[0].folder_uid
       		text +=	'" onclick="folder_view(\'beginIT\',\''
       		text +=	result[0].folder_uid
       		text +=	'\')" value="+">'
          	text +=	'<a>'+result[0].folder_name+'</a> <button onclick="folder_add(\'beginIT\',\''+result[0].folder_uid+'\',\''+result[0].folder_level+'\')">하위폴더 생성</button>'
       		
      		//삭제
      		text += '<input type="button" onclick="folder_delete(\''+result[0].folder_uid+'\')" value="삭제">'
      		
      		//하위 div
      		text +=	'</div><div id="fdivDown_'+result[0].folder_uid+'"></div>'
        	
       		$('#parentFolder').append(text);

	    }
	});
  }
  
  function folder_add(user_uid, folder_uid, folder_level){
	  
	  $.ajax({
	    url: "/folderInsert2",
	    data : {
	    	"folder_name" : "하위폴더",
	    	"user_uid" : user_uid,
	    	"upfolder_uid" : folder_uid,
	    	"folder_level" : folder_level,
	    	'company_code' : "1",
	    	"first" : 0
	    },
        type : 'post',
        datatype: 'text',
        async: false,
        success : function(result) {
	
        	if(result == ""){
        		alert('폴더 레벨 초과')
        	}else{
				var jsonStr = JSON.stringify(result);
				
				var spacebar = "&nbsp";
	
				
				var text = "";
				text += '<div id="fdivUp_'+result[0].folder_uid+'">'
				text += spacebar+"└"
				text +=	'<input type="button" id="'
	       		text +=	result[0].folder_uid
	       		text +=	'" onclick="folder_view(\'beginIT\',\''
	       		text +=	result[0].folder_uid
	       		text +=	'\')" value="+">'
	      		text +=	'<a>'+result[0].folder_name+'</a> <button onclick="folder_add(\'beginIT\',\''+result[0].folder_uid+'\',\''+result[0].folder_level+'\')">하위폴더 생성</button>'
	       		
	      		//삭제
	      		text += '<input type="button" onclick="folder_delete(\''+result[0].folder_uid+'\')" value="삭제">'
	      		
	      		//하위 div
	      		text +=	'</div><div id="fdivDown_'+result[0].folder_uid+'"></div>'
	        	
	       		$('#fdivDown_'+folder_uid).append(text);
	       		
				document.getElementById(folder_uid).value = '-';
        	}

	    }
	});
  }
  </script>
  
  <script>
  function folder_view(user_uid, folder_uid){
	var action = document.getElementById(folder_uid).value;

	if(action == '+'){
	
		document.getElementById(folder_uid).value = '-';

		$.ajax({
		    url: "/folderSelect2",
		    data : {
		    	'user_uid' : user_uid,
		    	'upfolder_uid' : folder_uid,
		    	'company_code' : "1"
		    },
	        type : "post",
	        success : function(result) {
	        	var jsonStr = JSON.stringify(result);
	        	
	        	//폴더
	        	for(var i=0; i<result[0].length; i++){
	        		
	        		var spacebar = "&nbsp";
        		
	        		var text = '<div id="fdivUp_'+result[0][i].folder_uid+'">'
	        		text += spacebar+"└"

	        		//폴더
	        		text +=	'<input type="button" id="'
	        		text +=	result[0][i].folder_uid
	        		text +=	'" onclick="folder_view(\'beginIT\',\''
	        		text +=	result[0][i].folder_uid
	        		text +=	'\')" value="+">'
	        		text +=	'<a>'+result[0][i].folder_name+'</a> <button onclick="folder_add(\'beginIT\',\''+result[0][i].folder_uid+'\',\''+result[0][i].folder_level+'\')">하위폴더 생성</button>'
	        		
	        		//삭제
	          		text += '<input type="button" onclick="folder_delete(\''+result[0][i].folder_uid+'\')" value="삭제">'
	          		
	          		//하위 div
	        		text +=	'</div><div id="fdivDown_'+result[0][i].folder_uid+'"></div>'
	        		
	        		$('#fdivDown_'+folder_uid).append(text);
	        	}
	        	
	        	//파일
				for(var i=0; i<result[1].length; i++){
	        		
	        		var spacebar = "&nbsp";
	        		
	        		var text = '<div id="fdivUp_'+result[1][i].folder_uid+'">'
	        		text += spacebar+"└"
	        		
	        		text +=	'<a>'+result[1][i].meeting_name+'</a>'
	        		
	        		$('#fdivDown_'+folder_uid).append(text);
	        	}
	        	
		    }
		});
		
	}else if(action == '-'){
		document.getElementById(folder_uid).value = '+';
		
		$('#fdivDown_'+folder_uid).empty();
	}

  }
  </script>
  
</html>