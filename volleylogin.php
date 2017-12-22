<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

include 'dbonfig.php';

$con = mysqli_connect(HOST,USER,PASS,DB);

$email = $_POST['email'];
$password = $_POST['password'];

$Sql_Query = "select * from Users where email = '$email' and password = '$password' ";

$check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));

if(isset($check)){

echo "Data Matched";
}
else{
echo "Invalid Username or Password Please Try Again !";
}

}else{
echo "Check Again";
}
mysqli_close($con);

?>