<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.kaige.unit.DB"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    
<%
request.setCharacterEncoding("GBK");

int pid = Integer.parseInt(request.getParameter("pid"));
int rootId = Integer.parseInt(request.getParameter("rootId"));
String title = request.getParameter("title");

String cont = request.getParameter("cont");


Connection c = DB.getConn();

boolean autoCommit = c.getAutoCommit();
c.setAutoCommit(false);

String sql = "insert into article values (null, ?, ?, ?, ?, now(), ?)";
PreparedStatement pstmt = DB.getPStmt(c, sql);
pstmt.setInt(1, pid);
pstmt.setInt(2, rootId);
pstmt.setString(3, title);
pstmt.setString(4, cont);
pstmt.setInt(5, 0);
pstmt.executeUpdate();

Statement stmt = DB.createStmt(c);
stmt.executeUpdate("update article set isleaf = 1 where id =" + pid);

c.commit();

c.setAutoCommit(autoCommit);
DB.closeStmt(pstmt);
DB.closeStmt(stmt);
DB.closeConn(c);


%>


<span id="time" style="background:red">5</span>秒钟后自动跳转，如果不跳转，请点击下面链接

<script language="JavaScript1.2" type="text/javascript">
<!--
//  Place this in the 'head' section of your page.  

function delayURL(url) {
	var delay = document.getElementById("time").innerHTML;
//alert(delay);
	if(delay > 0) {
		delay--;
		document.getElementById("time").innerHTML = delay;
	} else {
		window.top.location.href = url;
    }
    setTimeout("delayURL('" + url + "')", 1000); //delayURL(http://wwer)
}

//-->

</script>


<a href="article.jsp">主题列表</a>
<script type="text/javascript">
	delayURL("article.jsp");
</script>