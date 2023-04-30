// CSV 파일에서 데이터를 검색하고 HTML 페이지에 출력하는 함수
function searchStudent() {
    var grade = document.getElementById("grade").value;
    var classNum = document.getElementById("classNum").value;
    var studentNum = document.getElementById("studentNum").value;
  
    // CSV 파일을 가져옴
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        // CSV 파일을 파싱하여 2차원 배열로 변환
        var lines = this.responseText.split("\n");
        var data = [];
        for (var i = 1; i < lines.length; i++) {
          var cells = lines[i].split(",");
          if (cells.length == 8) {
            data.push(cells);
          }
        }
  
        // 해당하는 학생 데이터를 검색
        var studentData = null;
        for (var i = 0; i < data.length; i++) {
          var rowData = data[i];
          if (rowData[0] == grade && rowData[1] == classNum && rowData[2] == studentNum) {
            studentData = rowData;
            break;
          }
        }
  
        // HTML 페이지에 결과 출력
        if (studentData != null) {
          document.getElementById("busan_ktx").innerHTML = studentData[4];
          document.getElementById("suwon_ktx").innerHTML = studentData[5];
          document.getElementById("first_hotel").innerHTML = studentData[6];
          document.getElementById("second_hotel").innerHTML = studentData[7];
        } else {
          alert("일치하는 학생 데이터가 없습니다.");
        }
      }
    };
    xhttp.open("GET", "please.csv", true);
    xhttp.send();
  }
  