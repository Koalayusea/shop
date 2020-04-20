<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//for secure
defined('_MODE') or header('http/1.1 404 not found');
header('Content-Type:text/html; charset=utf-8');

$mysqli_h = 'localhost';
$mysqli_u = 'root';
$mysqli_p = 'miniserver';
$mysqli_db = 'poti';
$mysqli_con = new mysqli($mysqli_h, $mysqli_u, $mysqli_p, $mysqli_db);
$mysqli_con->set_charset('utf8');
if($mysqli_con->connect_errno){
    die("error with connection: {$mysqli_con->connect_error}");
}