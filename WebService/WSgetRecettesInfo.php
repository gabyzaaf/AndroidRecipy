<?php
require_once("class/RecetteAndroid.php");
if(empty($_POST["fid"]) || $_POST["fid"]==0 || empty($_POST["title"]) || $_POST["title"]==""){
	$arr = array(
	  "Err" => "the array user id not exist"	
	);
       echo json_encode($arr);
}else{
	$recetteObj = new Recette();
	error_log("the fid is ".$_POST["fid"]." and title is ".$_POST["title"]);
	$arrayValues = $recetteObj->getRecetteInfo($_POST["fid"], $_POST["title"]);	
	echo json_encode($arrayValues);
}




?>
