@ECHO OFF
$INSTALL_PATH\mysql-5.0.95-win32\bin\mysqld.exe" --uninstall
net stop MySQL
sc delete MySQL