<?php
require_once("pdo.php");
/**
 * Created by PhpStorm.
 * User: zaafranigabriel
 * Date: 01/03/2016
 * Time: 21:44
 */
class Commenter
{

    private $id;
    private $heure_saisie;
    private $fidUtilisateur;
    private $fidRecette;
    private $contenu;
    /**
     * Commenter constructor.
     * @param $id
     * @param $fidRecette
     * @param $fidUtilisateur
     * @param $heure_saisie
     * @param $contenu
     */
    public function __construct($id=NULL, $fidRecette=NULL, $fidUtilisateur=NULL, $heure_saisie=NULL,$contenu=NULL)
    {
        $this->id = $id;
        $this->fidRecette = $fidRecette;
        $this->fidUtilisateur = $fidUtilisateur;
        $this->heure_saisie = $heure_saisie;
        $this->contenu = $contenu;
    }


    public function ajout(){
        if($this->fidUtilisateur==0 ||$this->fidUtilisateur==NULL  || $this->fidRecette==0 || $this->fidRecette==NULL){
            return "Erreur vos idUtilisateur ou idRecette est faux";
        }
        $sql = "insert into Commenter (heure_saisie,fidUtilisateur,fidRecette,contenu) values (NOW(),:fidUtilisateur,:fidRecette,:contenu)";
        $array = array(
            ":contenu" => $this->contenu,
            ":fidUtilisateur" => $this->fidUtilisateur,
            ":fidRecette" => $this->fidRecette
        );
        return Spdo::getInstance()->query($sql,$array);
    }

    public function supprimerRecette(){
        if($this->fidRecette==0 ||$this->fidRecette==NULL || $this->fidRecette==""){
            return "L'identifiant de votre recette n'est pas conforme";
        }
        $sql = "delete from Commenter where fidRecette=:fidRecette";
        $array = array(
            ":fidRecette"=>$this->fidRecette
        );
        return Spdo::getInstance()->query($sql,$array);
    }

    public function supprimerUtilisateur(){
        if($this->fidUtilisateur==0 || $this->fidUtilisateur==NULL || $this->fidUtilisateur==""){
            return "L'identifiant de votre Utilisateur n'est pas conforme";
        }
        $sql="delete from Commenter where fidUtilisateur=:fidUtilisateur";
        $array = array(
            ":fidUtilisateur"=>$this->fidUtilisateur
        );
        return Spdo::getInstance()->query($sql,$array);
    }

}

?>