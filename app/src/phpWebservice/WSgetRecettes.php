<?php
require_once("class/Recette.php");
if(empty($_POST["fid"])|| $_POST["fid"]==0){
	$arr = array(
	  "Err" => "the array user id not exist"	
	);
       echo json_encode($arr);
}else{
	$recetteObj = new Recette();
	error_log("the fid is ".$_POST["fid"]);
	$arrayValues = $recetteObj->getRecette($_POST["fid"]);	
	echo json_encode($arrayValues);
}




?>
