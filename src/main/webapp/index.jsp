<html>
<head>
<script type="text/javascript">
	document.getElementById('aaa').attachEvent('onpropertychange',function(o){alert('ok')});   
</script>
</head>

<body>
		<form name="myForm">
			aaa: <input type="text" id="aaa" name="aaa" value="0.00"
				 onclick="this.myprop='aaa'"><br>

		</form>

</body>
</html>