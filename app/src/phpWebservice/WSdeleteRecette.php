<?php
require_once("class/Recette.php");
if($_POST["fid"]=="" || $_POST["fid"]==NULL  || $_POST["fid"]==0 || $_POST["title"]==NULL || $_POST["title"]==""){
	$arr = array(
		"Err" => "the id is incorrect"
	);
	echo json_encode($arr);
}
else{
	$recetteObject = new Recette();
	$array = $recetteObject->delete($_POST["fid"],$_POST["title"]);
	if($array){
		$arr = array(
			"ETAT" => "OK Insert"
		);
		echo json_encode($arr);
	}else{
		$arr = array(
			"ETAT" => "Error Insert"
		);
		echo json_encode($arr);
	}
}


?>
