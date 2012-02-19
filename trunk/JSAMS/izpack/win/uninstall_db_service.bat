@ECHO OFF
$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysqld.exe" --uninstall
net stop MySQL
sc delete MySQL