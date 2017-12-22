<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'dbconfig.php';

 $con = mysqli_connect(HOST,USER,PASS,DB);

 $email = $_POST['email'];
 
 $password = $_POST['password'];
 
 $name = $_POST['name'];

 $CheckSQL = "SELECT * FROM Users WHERE email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'email Already Exist, Please Enter Another email.';

 }
else{ 
$Sql_Query = "INSERT INTO Users (name,email,password) values ('$name','$email','$password')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Success';
}
else
{
 echo 'Something went wrong';
 }
 }
}
 mysqli_close($con);
?>