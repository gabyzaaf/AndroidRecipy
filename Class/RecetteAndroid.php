<?php

ini_set('display_errors', 1);
require_once("pdo.php");

class Recette{

private $id;
private $autreId;
private $titre;
private $contenu;
private $image;
private $adresseRecette;
private $visible;
private $partage;
private $type;
private $fid;

public function __construct($id=NULL,$titre=NULL,$contenu=NULL,$image=NULL,$adresseRecette=NULL,$visible=NULL,$partage=NULL,$type=NULL,$fid=NULL)
{
	$this->id = $id;
	$this->titre = $titre;
	$this->contenu = $contenu;
	$this->image = $image;
	$this->adresseRecette = $adresseRecette;
	$this->visible = $visible;
	$this->partage = $partage;
	$this->type = $type;
	$this->fid = $fid;

}


public function creation($idUtilisateur){
	$sql = "insert into recetteandroid(title,contenu,image_lien,visible,partage,fid) values (:title,:contenu,:image_lien,:adresseRecette,:visible,:partage,:fid)";
	$array = array(
			":title"=>$this->titre,
			":contenu"=>$this->contenu,
			":image_lien"=>$this->image,
			":adresseRecette"=>$this->adresseRecette,
			":visible"=>$this->visible,
			":partage"=>$this->partage,
			":fid"=>$idUtilisateur
		);
	return Spdo::getInstance()->query($sql,$array);
}

public function getRecette($idUtilisateur){
	$sql = "select * from recetteandroid where fid=:fid and visible=1";
	$tabRecette="";
	$array = array(
			":fid"=>$idUtilisateur
		);
	
	return Spdo::getInstance()->query($sql,$array);
}

public function getRecetteInfo($idUtilisateur, $title){
	$sql = "select * from recetteandroid where fid=:fid and visible=1 and title=:title";
	$tabRecette="";
	$array = array(
			":title"=>$title,
			":fid"=>$idUtilisateur
		);
	
	return Spdo::getInstance()->query($sql,$array);
}

public function getRecetteTitle($title){
	$sql = "select * from recetteandroid where title=:title and visible=1";
	$tabRecette="";
	$array = array(
			":title"=>$title
		);
	
	return Spdo::getInstance()->query($sql,$array);
}

public function getRecetteExist($title, $idUtilisateur){
	$sql = "select * from recetteandroid where title=:title and fid=:fid";
	$array = array(
			":title"=>$title,
			":fid"=>$idUtilisateur
		);
	
	return Spdo::getInstance()->query($sql,$array);
}

public function getRecetteExistImage($title, $idUtilisateur){
	$sql = "select image_lien from recetteandroid where title=:title and fid=:fid";
	$array = array(
			":title"=>$title,
			":fid"=>$idUtilisateur
		);
	return Spdo::getInstance()->query($sql,$array);
}

public function visible($idRecette){
	$sql = "update recetteandroid set visible=0 where id=:id";
	$array = array(
		":id"=>$idRecette
		);
	return Spdo::getInstance()->query($sql,$array);
}

public function insertRecette($title, $contenu, $fileToUpload, $idUtilisateur){
	$sql = "insert into recetteandroid(title,contenu,image_lien,adresseRecette,visible,partage,fid) values (:title,:contenu, :image_lien, :adresseRecette, 1, 0,:fid)";
	$array = array(
			":title"=>$title,
			":contenu"=>$contenu,
			":image_lien"=>$fileToUpload,
			":adresseRecette"=>$adresseRecette,
			":fid"=>$idUtilisateur
		);
	return Spdo::getInstance()->query($sql,$array);
}

public function updateRecette($idRecette, $idUtilisateur, $title, $contenu){
	$sql = "update recetteandroid set title = :title, contenu = :contenu, image_lien = '', visible = 1, partage = 0, fid = :fid where id = :id;";
	$array = array(
			":id"=>$idRecette,
			":title"=>$title,
			":contenu"=>$contenu,
			":fid"=>$idUtilisateur
		);
	return Spdo::getInstance()->query($sql,$array);
}


public function deleteRecette($idUtilisateur, $title){
	$sql = "delete from recetteandroid where title = :title and fid = :fid";
	$array = array(
			":title"=>$title,
			":fid"=>$idUtilisateur
		);
	return Spdo::getInstance()->query($sql,$array);
}
}


?>