<?php
ini_set('display_errors', 1);
require_once("pdo.php");
class Utilisateur{

	private $id;
	private $nom;
	private $prenom;
	private $email;
	private $logins;
	private $admin;
	private $naissance;
	private $mdp;
	private $actif;

	public function __construct($id=NULL,$nom=NULL,$prenom=NULL,$logins=NULL,$email=NULL,$admin=NULL,$naissance=NULL,$mdp=NULL,$actif=NULL)
	{
		$this->id = $id;
		$this->nom = $nom;
		$this->prenom = $prenom;
		$this->logins = $logins;
		$this->email = $email;
		$this->admin = $admin;
		$this->naissance = $naissance;
		$this->mdp = $mdp;
		$this->actif = $actif;
	}

	public function ajoutStorage()
	{
		$sql = "select ajout(:nom,:prenom,:admin,:logins,:email,:naissance,:pwd)";
		$array = array(
			":nom"=>$this->nom,
			":prenom"=>$this->prenom,
			":admin"=>$this->admin,
			":logins"=>$this->logins,
			":email"=>$this->email,
			":naissance"=>$this->naissance,
			":pwd"=>$this->mdp
		);
		return Spdo::getInstance()->query($sql,$array);
	}


	public function exist(){
		$sql = "select count(*) as 'nb' from Utilisateur where logins=:logins and email=:email and pwd=MD5(:mdp)";
		$array=array(
			":email"=>$this->email,
			":logins"=>$this->logins,
			":mdp"=>$this->mdp
		);
		return Spdo::getInstance()->query($sql,$array);
	}


	public function getUtilisateur(){
		if($this->logins==NULL || $this->logins=="" || $this->email=="" || $this->email==NULL || $this->mdp=="" || $this->mdp==NULL){
			echo "IN logins verificaiton <br/>";
			return false;	
		}
		$tab = $this->exist();
		if($tab[0]['nb']<"1"){
			return false;
		}
		$sql = "select id,nom,prenom from Utilisateur where logins=:logins and email=:email and pwd=MD5(:mdp) and actif=0";
		$array = array(
			":logins"=>$this->logins,
			":email"=>$this->email,
			":mdp"=>$this->mdp
			);
		return Spdo::getInstance()->query($sql,$array);
	}

	public function getConnexion(){
		$sql="select * from Utilisateur where logins=:logins and pwd=MD5(:mdp)";
		$array = array(
			":logins"=>$this->logins,
			":mdp"=>$this->mdp
		);
		return Spdo::getInstance()->query($sql,$array);
	}



	public function modifier(){
		if($this->nom=="" || $this->nom == NULL || $this->prenom==="" || $this->prenom==NULL || $this->email=="" || $this->email==NULL || $this->logins=="" || $this->logins==NULL){
			return false;
		}
		$sql = "update Utilisateur set nom=:nom,prenom=:prenom,email=:email where logins=:logins";
		$array=array(
			":nom"=>$this->nom,
			":prenom"=>$this->prenom,
			":email"=>$this->email,
			":logins"=>$this->logins
			); 	
		return Spdo::getInstance()->query($sql,$array);
	}

	public function desactiver(){
		$val = $this->getUtilisateur();
		var_dump($val);
		if($val==false || $val ==NULL){
			echo "<br/>IN null desactiver function <br/>";
			return false;
		}
		$sql = "update Utilisateur set actif=1 where id=:id";
		$array = array(
				":id"=>$val[0]["id"]
			);
		return Spdo::getInstance()->query($sql,$array);
	}






}




?>