function restrictNumbers(event) 
{
	var keyCode=event.which||event.keycode;
	if (((keyCode>=65) && (keyCode<= 90)) || ((keyCode>= 97) && (keyCode<= 122)) || keyCode==32) 
	{
        return true;
	}
	else 
	{
        return false;
	}
}
function restrictSpace(event) 
{
	var keyCode=event.which||event.keycode;
	if (keyCode!= 32) 
	{
        return true;
	}
	else 
	{
		return false;
	}
}
function validateAmount() 
{
	var amount=document.getElementById("amount").value;
	if(amount>=10 && amount<=100000 ) 
	{
		document.getElementById("amount").style.borderColor="skyblue";
	}
	else 
	{
		document.getElementById("amount").focus();
		document.getElementById("amount").style.borderColor="red";
	}
}
function validateEmail() 
{
	var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById("emailId").value;
	if(!email.match(pattern)) 
	{
		document.getElementById("emailId").focus();
		document.getElementById("emailId").style.borderColor="red";
	}
	else 
	{
		document.getElementById("emailId").style.borderColor="skyblue";
	}
}
function validatePassword() 
{
	var password=document.getElementById("password").value;
	if (password.length < 4 ) 
	{
		document.getElementById("password").focus();
		document.getElementById("password").style.borderColor="red";
	}
	else 
	{
		document.getElementById("password").style.borderColor="skyblue";
	}
}
function restrictCharacters(event) 
{
	var keyCode=event.which||event.keycode;
	if (((keyCode>=48 && keyCode<= 57) || keyCode==8 ||(keyCode>=35 && keyCode<=40)|| keyCode==46)) 
	{
        return true;
	}
	else 
	{
        return false;
	}
}
function removeSpace(id)
{
	var name=document.getElementById(id).value;
	var newName=name.replace(/  +/g, ' ').trim();
	document.getElementById(id).value=newName;
	if(newName.startsWith(" "))
	{
		document.getElementById(id).style.borderColor="red";
		document.getElementById(id).focus();
	}
	else
	{
		document.getElementById(id).style.borderColor="skyblue";
	}
}