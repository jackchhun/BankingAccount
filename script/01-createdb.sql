--
-- Launch the script as database admin user or root
--

create user 'codingadmin'@'%' identified by 'Pi';

create database coding;

grant all privileges on coding.* to 'codingadmin'@'%';

update Db set Grant_priv='Y' where User = 'codingadmin' and Db = 'coding';
