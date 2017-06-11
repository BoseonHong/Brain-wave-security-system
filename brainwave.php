<?php

define('DB_DATABASE', 'brainwave');
define('DB_USERNAME', 'dbuser');
define('DB_PASSWORD', 'vagrant');
define('PDO_DSN', 'mysql:host=localhost;dbname=' . DB_DATABASE);


if(isset($_POST['ID'])) {

  try {
    $db = new PDO(PDO_DSN, DB_USERNAME, DB_PASSWORD);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    if ($_POST['DIST'] == "reguser") { // 유저 등록
      $sqldata = $db->prepare("insert into users (user_id, name, password) values (:user_id, :name, :password)");
      $sqldata->bindvalue(':user_id', $_POST['ID']);
      $sqldata->bindvalue(':name', $_POST['NAME']);
      $sqldata->bindvalue(':password', $_POST['PASSWORD']);
      $sqldata->execute();

    } if ($_POST['DIST'] == "regegg") { // 뇌파 등록
      $sqldata = $db->prepare("insert into egg (delta, theta, alpha, beta, gamma, user_id) values (:delta, :theta, :alpha, :beta, :gamma, :user_id)");
      $sqldata->bindvalue(':delta', $_POST['delta']);
      $sqldata->bindvalue(':theta', $_POST['theta']);
      $sqldata->bindvalue(':alpha', $_POST['alpha']);
      $sqldata->bindvalue(':beta', $_POST['beta']);
      $sqldata->bindvalue(':gamma', $_POST['gamma']);
      $sqldata->bindvalue(':user_id', $_POST['ID']);
      $sqldata->execute();

    }if ($_POST['DIST'] == "login") { // 아이디로 로그인
      $sqldata = $db->prepare("select user_id from users where user_id = :user_id and password = :password");
      $sqldata->bindvalue(':user_id', $_POST['ID']);
      $sqldata->bindvalue(':password', $_POST['PASSWORD']);
      $sqldata->execute();

      //JSON데이터 출력
      header("Content-type: application/json; charset=UTF-8");
      // echo json_encode($response);
      echo json_encode(array(
        "status" => 1
      ));
    } else {
      exit;
    }

    $db = null;

  } catch (Exception $e) {
      echo $e->getMessage();
      exit;
  }
}
 ?>
