<!Document>
<html>
<head>
    <title>测试Multipart提交</title>
</head>
<body style="margin: auto">

<h1> 测试文件上传 </h1>
<br/><br/>

<h3>单个文件上传</h3>
<br/>

<form method="post" enctype="multipart/form-data" action="/upload/single">
    文件：<input name="file" type="file"> <br/>
    <input name="submit" type="submit" value="提交">
</form>
<br/><br/>

<h3>多文件上传</h3>
<br/>
<form method="post" enctype="multipart/form-data" action="/upload/multi_file">
    <input name="file" type="file"><br>
    <input name="file" type="file"><br/>
    <input name="submit" type="submit" value="提交">
</form>

<br/><br/>
<h3>文本+文件上传</h3>
<form method="post" enctype="multipart/form-data" action="/upload/multi ">
    文本：<input name="text" type="text"> <br/>
    文件：<input name="file" type="file"> <br>
    <input name="submit" type="submit" value="提交"><br>
</form>

</body>
</html>