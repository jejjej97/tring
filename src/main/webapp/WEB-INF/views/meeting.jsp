<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset="utf-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  <body>
    <a href="/googleCalendarUrl">Calendar List</a>
    <a href="/excelHome">excel</a>
    <a href="/folderHome">folder</a>
    <br><br>
    
    <div id="meeting">
		<button onclick="agendaAdd()">논의 +</button>

		<div id="agendaList">
			
			<!-- 1 -->
			<div id="agenda">
			논의<input type="text" id="agenda_content"><button onclick="issueAdd()">쟁점 +</button>
			
			<div id="issueList">
	
				<div id="issue">
					&nbsp 쟁점<input type="text" id="issue_content"><button onclick="consultationAdd()">협의 +</button>
					
					<div id="consultationList">
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
						
	
					</div>
				</div>
	
			</div>
	
			결론<input type="text" id="agenda_result">
			</div>
			<br>
	
			<!-- 2 -->
			<div id="agenda">
			논의<input type="text" id="agenda_content"><button onclick="issueAdd()">쟁점 +</button>
			
			<div id="issueList">
			
				<div id="issue">
					&nbsp 쟁점<input type="text" id="issue_content"><button onclick="consultationAdd()">협의 +</button>
					
					<div id="consultationList">
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
					</div>
				</div>
				
				<div id="issue">
					&nbsp 쟁점<input type="text" id="issue_content"><button onclick="consultationAdd()">협의 +</button>
					
					<div id="consultationList">
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
	
					</div>
				</div>
	
			</div>
	
			결론<input type="text" id="agenda_result">
			</div>
			<br>
	
			<!-- 3 -->
			<div id="agenda">
			논의<input type="text" id="agenda_content"><button onclick="issueAdd()">쟁점 +</button>
			
			<div id="issueList">
	
				<div id="issue">
					&nbsp 쟁점<input type="text" id="issue_content"><button onclick="consultationAdd()">협의 +</button>
					
					<div id="consultationList">
						<div id="consultation">
							&nbsp &nbsp 협의<input type="text" id="consultation_content">
						</div>
						
	
					</div>
				</div>
	
			</div>
	
			결론<input type="text" id="agenda_result">
			</div>
			<br>
		</div>
	
	</div>
    <button onclick="submit()">저장</button>
  </body>
  
<script>
function submit(){
	let meetingMap = new Object();
	
	let agendaArray = new Array();
	var agendaList = $('#agendaList').children('#agenda');
	for(var a=0; a<agendaList.length; a++){
		let agendaMap = new Object();
		
		var agenda = $('#agendaList').children('#agenda:eq('+a+')')
		var agenda_content_val = agenda.children('#agenda_content').val();
		var agenda_result_val = agenda.children('#agenda_result').val();
		 
		//alert(agenda_content_val) //출력 확인
		//alert(agenda_result_val)  //출력 확인
 		
		agendaMap.agenda_content = agenda_content_val;
		agendaMap.agenda_result = agenda_result_val;
		agendaArray.push(agendaMap);
		
		let issueArray = new Array();
		var issueList = agenda.children('#issueList').children('#issue');
		for(var b=0; b<issueList.length; b++){
			let issueMap = new Object();
			
			var issue = agenda.children('#issueList').children('#issue:eq('+b+')');
			var issue_content_val = issue.children('#issue_content').val();
			 
			//alert(issue_content_val) //출력 확인
			
			issueMap.issue_content = issue_content_val;
			issueArray.push(issueMap);
			
			let consultationArray = new Array();
			var consultationList = issue.children('#consultationList').children('#consultation');
			for(var c=0; c<consultationList.length; c++){
				let consultationMap = new Object();
				
				var consultation = issue.children('#consultationList').children('#consultation:eq('+c+')');
				var consultation_content_val = consultation.children('#consultation_content').val();
				
				//alert(consultation_content_val) //출력 확인
				
				consultationMap.consultation_content = consultation_content_val;
				consultationArray.push(consultationMap);
			}
			issueMap.consultation = consultationArray;
		}
		agendaMap.issue = issueArray;
	}
	meetingMap.agenda = agendaArray;
	
	console.log(meetingMap);

	var jsonStr = JSON.stringify(meetingMap);
	
	//alert(jsonStr)
	
 	$.ajax({
	    url: "/meeting_content",
	    data : {"meeting_content":jsonStr},
        type : 'post',
        datatype: 'text',
        async: false,
        success : function(result) {
        	if(result=="success"){
				alert('success')
        	}
	    }
	});
}
</script>
  
  
  
<script>
function agendaAdd(){
	var add = '<div id="agenda">'
		add += '논의<input type="text" id="agenda_content">'
		add += '<button onclick="issueAdd()">쟁점 +</button>'
		add += '<div id="issueList"></div>'
		add += '결론<input type="text" id="agenda_result">'
		add += '</div><br>'
		
		$('#agendaList').append(add);
}
  
function consultationAdd(){
	  
}
</script>

<script>
function issueAdd(){
	var add = '<div id="issue">'
		add += '&nbsp 쟁점<input type="text" id="issue_content"><button onclick="consultationAdd()">협의 +</button>'
		add += '<div id="consultationList">'
		add += '</div>'
		add += '</div>'
		
		$('#issueList').append(add);
}
</script>

<script>
function consultationAdd(){
	var add = '<div id="consultation">'
		add += '&nbsp &nbsp 협의<input type="text" id="consultation_content">'
		add += '</div>'
		
		$('#consultationList').append(add);
}
</script>


  <script>
  
  /* let uuid = self.crypto.randomUUID();
  alert(uuid) */
  
  </script>
</html>