@ECHO OFF
echo "$INSTALL_PATH"

"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqld.exe" --install
net start MySQL
