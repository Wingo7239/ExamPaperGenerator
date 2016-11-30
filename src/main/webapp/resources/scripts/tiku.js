/**
 * 
 */


$(document).ready(function(){
	

	
$(window).unload(function(){
	document.cookie="cart="+cookie.toJSONString();
	});

function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}

$("#319").click(
function(){
	var cookie=[{
		count:3,
		id:1,
		name:"multi",
		quesList:[1,2,3]
	}];
//	alert(JSON.stringify(cookie));
	document.cookie="cart="+JSON.stringify(cookie);
	alert(getCookie("cart"));
}		
);
});