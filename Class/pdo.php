<?php
ini_set('display_errors', 1);
class SPdo{

    
    private $PDOinstance = null;
    private static $instance = null;
    
    const SERVER="localhost";
    const DATABASE="recipy";
    const LOGIN="root";
    const PASSWORD="";
    
  
    private function __construct(){
        $dsn = 'mysql:dbname='.self::DATABASE.';host='.self::SERVER.':3306';
        $this->PDOinstance = new PDO($dsn,self::LOGIN,self::PASSWORD);
    }
    
    public static function getInstance(){
        if(is_null(self::$instance)){
            self::$instance = new SPDO();
        }
        return self::$instance;
    }
    
    /*
        Execution de requete sql
    */
    public function query($query,$array){
         try{

            $str = preg_replace('/\s+/', '', $query);
            $str = strtolower($str);
            if(substr($str,0,6)=="select"){
                $statement =  $this->PDOinstance->prepare($query);
                
                $statement->execute($array);
               
                return $statement->fetchAll(PDO::FETCH_ASSOC);
            }else{  
             $statement =  $this->PDOinstance->prepare($query);
             $var = $statement->execute($array);
             return $var;
            }
         }catch(PDOException $e){
            return $e->getMessage();
         }
         
    }
    

}

?>