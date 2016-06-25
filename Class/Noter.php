<?php
require_once("pdo.php");
/**
 * Created by PhpStorm.
 * User: zaafranigabriel
 * Date: 01/03/2016
 * Time: 21:23
 */
class Noter
{

    private $id;
    private $score;
    private $fidUtilisateur;
    private $fidRecette;

    /**
     * Noter constructor.
     * @param $id
     * @param $score
     * @param $fidUtilisateur
     * @param $fidRecette
     */
    public function __construct($id=NULL, $score=NULL, $fidUtilisateur=NULL, $fidRecette=NULL)
    {
        $this->id = $id;
        $this->score = $score;
        $this->fidUtilisateur = $fidUtilisateur;
        $this->fidRecette = $fidRecette;
    }

    public function ajout(){
        if($this->score>=0 && $this->score<=10){
            $sql = "insert into noter (score,fidUtilisateur,fidRecette) values(:score,:fidUtilisateur,:fidRecette)";
            $array = array(
                ":score" => $this->score,
                ":fidUtilisateur"=>$this->fidUtilisateur,
                ":fidRecette"=>$this->fidRecette
            );
            return Spdo::getInstance()->query($sql,$array);
        }else{
            echo "votre score doit etre entre 0 et 10";
        }

    }

    /*
     * Faire le delete
     * */





}


?>