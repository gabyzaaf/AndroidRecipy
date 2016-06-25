<?php
require_once("class/RecetteAndroid.php");

if(empty($_POST["title"]) || empty($_POST["content"]) || empty($_POST["adress"]) || empty($_POST["id"])){
$arr=array(
	"ERR" => "All the credential must be complet"
);	
echo json_encode($arr);
}else{

$recette = new Recette(NULL,$_POST["title"],$_POST["content"],NULL,NULL,NULL,NULL,$_POST["id"],$_POST["adress"]);
$arrayValue = $recette->creation($_POST["id"]);

if($arrayValue==NULL || $arrayValue==""){
	$tab = array(
	  "ERR" => "The array don't have result"
	);
	echo json_encode($tab);
}else{
	echo json_encode($arrayValue);
}



}


?>
