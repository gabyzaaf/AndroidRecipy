<?php
/**
 * Created by PhpStorm.
 * User: zaafranigabriel
 * Date: 30/04/2016
 * Time: 15:02
 */
require_once("class/utilisateur.php");

$user = new Utilisateur(NULL,NULL,NULL,$_POST["login"],NULL,true,NULL,$_POST["pass"],NULL);
$arr = $user->getConnexion();
echo json_encode($arr[0]);





?>
