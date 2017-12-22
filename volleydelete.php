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
$Sql_Query = "DELETE FROM Users WHERE email='$email' and password='$password'";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Success';
}
else
{
 echo 'Something went wrong';
 }
 }
else{ 
echo 'account does not exist';
 }
}
 mysqli_close($con);
?>
